package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.assets.AssetManager;
import com.jemcphe.mustache_justice.Assets;;

public class MustacheJusticeGame extends Game {
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;	
	private BackgroundAudio bgAudio;
	boolean isPlaying;
	public Sprite bgSprite;
	public Sprite playerSprite;
	public Sprite leftSprite;
	public Sprite rightSprite;
	public Sprite jumpSprite;
	public Sprite hitSprite;
		
	World world;
	@Override
	public void create() {

		// Set Log level to debug
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		// Load assets
		Assets.instance.init(new AssetManager());
		
		// Start Game At Menu Screen
		setScreen(new SplashScreen(this));
		
//		// Initialize controller and renderer
//		worldController = new WorldController();
//		worldRenderer = new WorldRenderer(worldController);
//		
//		// Play Background Music
//		bgAudio = new BackgroundAudio();
//        bgAudio.create();
//		
//		// Game world is active at start
//		paused = false;
	}
//
//	@Override
//	public void dispose() {
//		worldRenderer.dispose();
//		Assets.instance.dispose();
//		bgAudio.dispose();
//	}
//
//	@Override
//	public void render() {	
//		if (!paused){
//			/*
//			 * Update game world by the time that has passed
//			 * since the last rendered frame
//			 */
//			worldController.update(Gdx.graphics.getDeltaTime());
//		}
//		
//		// Set the clear screen color
//		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		
//		// Render the Game World
//		worldRenderer.render();
//	}
//
//	@Override
//	public void resize(int width, int height) {
//		worldRenderer.resize(width, height);
//	}
//
//	@Override
//	public void pause() {
//		paused = true;
//	}
//
//	@Override
//	public void resume() {
//		Assets.instance.init(new AssetManager());
//		paused = false;
//	}
	
	
}
