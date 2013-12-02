package com.jemcphe.mustache_justice;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MustacheJusticeGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture player;
	private Texture background;
	
	private Sprite bgSprite;
	private Sprite playerSprite;
	Vector2 position;
	
	@Override
	public void create() {
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		
		float worldWidth = 1000.0f;
		float worldHeight = worldWidth * (screenHeight/screenWidth);
//		
		camera = new OrthographicCamera(1, screenHeight/screenWidth);
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("data/longstreet.gif"));
		player = new Texture(Gdx.files.internal("data/mario.png"));
//		player.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
//		position = new Vector2(0,0);
		
		TextureRegion bgRegion = new TextureRegion(background, 0, 0, 512, 256);
//		TextureRegion bgRegion = new TextureRegion(background, 0, 0, 1024, 512);
//		
		bgSprite = new Sprite(bgRegion);
		bgSprite.setSize(1.5f, 1.5f * bgSprite.getHeight() / bgSprite.getWidth());
//		bgSprite.setOrigin(bgSprite.getWidth()/2, bgSprite.getHeight()/2);
		bgSprite.setPosition(-bgSprite.getWidth()/2, -bgSprite.getHeight()/2 + .1f);
		
		// Player Sprite 
		TextureRegion playerRegion = new TextureRegion(player);
		playerSprite = new Sprite(playerRegion);
		playerSprite.setSize(0.08f, 0.1f);
//		playerSprite.setOrigin(0, 0);
		playerSprite.setPosition(-.25f, -.17f);
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		bgSprite.draw(batch);
		playerSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}