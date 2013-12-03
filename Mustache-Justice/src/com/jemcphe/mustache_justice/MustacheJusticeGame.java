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

	WorldController controller;
	
	private BackgroundAudio bgAudio;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture player;
	private Texture background;
	private Texture leftButton;
	private Texture rightButton;
	private Texture jumpButton;
	private Texture hitButton;

	private Sprite bgSprite;
	private Sprite playerSprite;
	private Sprite leftSprite;
	private Sprite rightSprite;
	private Sprite jumpSprite;
	private Sprite hitSprite;

	Vector2 position;

	@Override
	public void create() {

		controller = new WorldController();
		
		bgAudio = new BackgroundAudio();
		bgAudio.create();

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();

//		float worldWidth = 1000.0f;
//		float worldHeight = worldWidth * (screenHeight/screenWidth);
		//		
		camera = new OrthographicCamera(1, screenHeight/screenWidth);
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("data/longstreet.gif"));
		player = new Texture(Gdx.files.internal("data/player.png"));
		leftButton = new Texture(Gdx.files.internal("data/left_arrow.png"));
		rightButton = new Texture(Gdx.files.internal("data/right_arrow.png"));
		jumpButton = new Texture(Gdx.files.internal("data/jump_button.png"));
		hitButton = new Texture(Gdx.files.internal("data/hit_button.png"));

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
		playerSprite.setSize(0.07f, 0.13f);
		//		playerSprite.setOrigin(0, 0);
		playerSprite.setPosition(-.25f, -.17f);

		// Left Button
		TextureRegion leftButtonRegion = new TextureRegion(leftButton);
		leftSprite = new Sprite (leftButtonRegion);
		leftSprite.setSize(0.11f, 0.08f);
		leftSprite.setPosition(-.45f, -.29f);

		// Right button
		TextureRegion rightButtonRegion = new TextureRegion(rightButton);
		rightSprite = new Sprite (rightButtonRegion);
		rightSprite.setSize(0.11f, 0.08f);
		rightSprite.setPosition(-.3f, -.29f);

		// Jump Button
		TextureRegion jumpButtonRegion = new TextureRegion(jumpButton);
		jumpSprite = new Sprite (jumpButtonRegion);
		jumpSprite.setSize(0.11f, 0.11f);
		jumpSprite.setPosition(.15f, -.3f);

		// Hit Button
		TextureRegion hitButtonRegion = new TextureRegion(hitButton);
		hitSprite = new Sprite (hitButtonRegion);
		hitSprite.setSize(0.11f, 0.11f);
		hitSprite.setPosition(.3f, -.3f);
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
		leftSprite.draw(batch);
		rightSprite.draw(batch);
		jumpSprite.draw(batch);
		hitSprite.draw(batch);
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
