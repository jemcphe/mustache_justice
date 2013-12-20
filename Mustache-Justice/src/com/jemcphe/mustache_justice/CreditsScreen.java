package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.jemcphe.mustache_justice.Constants;

public class CreditsScreen extends AbstractGameScreen{

	private static final String TAG = CreditsScreen.class.getName();

	private Stage stage;
	private Skin skinCredits;
	// menu
	private Image background;
	private Button btnBack;

	public CreditsScreen(Game game) {
		super(game);
	}
	
	private void rebuildStage () {
		skinCredits = new Skin(
				Gdx.files.internal(Constants.SKIN_CREDITS),
				new TextureAtlas(Constants.TEXTURE_ATLAS_CREDITS));
		// build all layers
		Table layerBackground = buildBackgroundLayer();
		Table layerObjects = buildObjectsLayer();
		Table layerControls = buildControlsLayer();

		// assemble stage for menu screen
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerObjects);
		stack.add(layerControls);
	}
	
	private Table buildBackgroundLayer () {
		Table layer = new Table();
		return layer;
	}
	
	private Table buildObjectsLayer () {
		Table layer = new Table();
		// + Background
		background = new Image(skinCredits, "credits");
		layer.add(background);
		return layer;
	}
	
	private Table buildControlsLayer () {
		Table layer = new Table();
		layer.center().bottom().padBottom(50);
		//  Add Play Button
		btnBack = new Button(skinCredits, "back");
		layer.add(btnBack);
		btnBack.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onBackClicked();
			}
		});
		
		return layer;
	}

	@Override
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(deltaTime);
		stage.draw();
		Table.drawDebug(stage);
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
		skinCredits.dispose();
	}

	@Override public void pause () { 

	}

	private void onBackClicked () {
		game.setScreen(new MenuScreen(game));
	}
	
	
}
