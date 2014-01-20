package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jemcphe.mustache_justice.AbstractGameObject;
import com.jemcphe.mustache_justice.MaxCassidy;
import com.jemcphe.mustache_justice.Ground;
import com.jemcphe.mustache_justice.Donut;


public class Level {

	public static final String TAG = Level.class.getName();

	public MaxCassidy max;
	public Array<Enemy> enemy;
	public Array<Donut> donuts;
	public Array<Revolver> revolver;

	public enum BLOCK_TYPE {
		EMPTY(0, 0, 0), // black
		GROUND(0, 255, 0), // green
		PLAYER_SPAWNPOINT(255, 255, 255), // white
		ITEM_DONUT(255, 255, 0), // yellow
		ITEM_REVOLVER(255, 0, 255), // purple
		ENEMY_SPAWNPOINT(255, 0, 0); // red
		private int color;
		private BLOCK_TYPE (int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		public boolean sameColor (int color) {
			return this.color == color;
		}
		public int getColor () {
			return color;
		}
	}

	// objects
	public Array<Ground> ground;

	public Level (String filename) {
		init(filename);
	}
	private void init (String filename) {
		// player character
		max = null;
		// enemy character
		enemy = new Array<Enemy>();
		// objects
		ground = new Array<Ground>();
		donuts = new Array<Donut>();
		revolver = new Array<Revolver>();
		// load image file that represents the level data
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		// scan pixels from top-left to bottom-right
		int lastPixel = -1;
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				// height grows from bottom to top
				float baseHeight = pixmap.getHeight() - pixelY;
				// get color of current pixel as 32-bit RGBA value
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				/*
				 * find matching color value to identify block type at (x,y)
				 * point and create the corresponding game object if there is a match empty space
				 */
				if (BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
					// do nothing
				}
				// Ground
				else if (BLOCK_TYPE.GROUND.sameColor(currentPixel)) {
					if (lastPixel != currentPixel) {
						obj = new Ground();
						float heightIncreaseFactor = 0.25f;
						offsetHeight = -2.5f;
						obj.position.set(pixelX,
								baseHeight * obj.dimension.y
								* heightIncreaseFactor
								+ offsetHeight);
						ground.add((Ground)obj);
					} else {
						ground.get(ground.size - 1).increaseLength(1);
					}
				}
				// player spawn point
				else if
				(BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {
					obj = new MaxCassidy();
					//					offsetHeight = 0.0f;
					obj.position.set(pixelX, baseHeight);
					max = (MaxCassidy)obj;
				}
				// Revolver
				else if (BLOCK_TYPE.ITEM_REVOLVER.sameColor(currentPixel)) {
					obj = new Revolver();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y
							+ offsetHeight);
					revolver.add((Revolver)obj);
				}
				// donut
				else if (BLOCK_TYPE.ITEM_DONUT.sameColor(currentPixel)) {
					obj = new Donut();
					offsetHeight = -1.5f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y
							+ offsetHeight);
					donuts.add((Donut)obj);
				}
				// Enemy Spawnpoint
				else if
				(BLOCK_TYPE.ENEMY_SPAWNPOINT.sameColor(currentPixel)){

					obj = new Enemy();
					offsetHeight = -1.5f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y
							+ offsetHeight);
					enemy.add((Enemy)obj);

					//					obj = new Enemy();
					//					offsetHeight= .5f;
					//					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					//					enemy = (Enemy)obj;
					System.out.println("Enemy has been spawned");
				}
				// unknown object/pixel color
				else {
					int r = 0xff & (currentPixel >>> 24); //red color channel
					int g = 0xff & (currentPixel >>> 16); //green color channel
					int b = 0xff & (currentPixel >>> 8); //blue color channel
					int a = 0xff & currentPixel; //alpha channel
					Gdx.app.error(TAG, "Unknown object at x<" + pixelX
							+ "> y<" + pixelY
							+ ">: r<" + r
							+ "> g<" + g
							+ "> b<" + b
							+ "> a<" + a + ">");
				}
				lastPixel = currentPixel;
			}
		}
		// free memory
		pixmap.dispose();
		Gdx.app.debug(TAG, "level '" + filename + "' loaded");
	}
	public void render (SpriteBatch batch) {
		// Draw Ground
		for (Ground object : ground){
			object.render(batch);
		}
			
		// Draw Donuts
		for (Donut donut : donuts){
			donut.render(batch);
		}
		// Draw Revolver
		for (Revolver object : revolver){
			object.render(batch);
		}

		// Draw Max Cassidy
		max.render(batch);

		// Draw Enemy
		for (Enemy object: enemy){
			object.render(batch);
		}

	}

	public void update (float deltaTime) {
		max.update(deltaTime);
		for(Ground object : ground)
			object.update(deltaTime);
		for(Donut donut : donuts)
			donut.update(deltaTime);
		for(Revolver object : revolver)
			object.update(deltaTime);
		for(Enemy object : enemy){
			object.update(deltaTime);
		}
	}

}