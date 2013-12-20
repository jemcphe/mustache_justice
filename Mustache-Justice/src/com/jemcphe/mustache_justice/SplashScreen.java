package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SplashScreen extends AbstractGameScreen{

	private Stage stage;
	private Skin skinSplash;
	// menu
	private Image background;
	
	private float splashScreenDelay;
	
	public SplashScreen(Game game) {
		super(game);
	}
	
	private void rebuildStage () {
		skinSplash = new Skin(
				Gdx.files.internal(Constants.SKIN_SPLASH),
				new TextureAtlas(Constants.TEXTURE_ATLAS_SPLASH));
		// build all layers
		Table layerBackground = buildBackgroundLayer();
		Table layerObjects = buildObjectsLayer();

		// assemble stage for help screen
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerObjects);
		
		splashScreenDelay = 3;
	}
	
	private Table buildBackgroundLayer () {
		Table layer = new Table();
		return layer;
	}
	
	private Table buildObjectsLayer () {
		Table layer = new Table();
		// + Background
		background = new Image(skinSplash, "splash");
		layer.add(background);
		return layer;
	}

	@Override
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(deltaTime);
		stage.draw();
		Table.drawDebug(stage);
		
		splashScreenDelay -= deltaTime;
		if (splashScreenDelay < 0){
			game.setScreen(new MenuScreen(game));
		}
	}

	@Override public void resize (int width, int height) { 
		stage.setViewport(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT, false);
	}

	@Override public void show () { 
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	@Override public void hide () { 
		stage.dispose();
		skinSplash.dispose();
	}

	@Override public void pause () { 

	}

}
