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
import com.jemcphe.mustache_justice.Constants;
import com.swarmconnect.Swarm;

public class MenuScreen extends AbstractGameScreen{

	private Stage stage;
	private Skin skinMustacheJustice;
	// menu
	private Image background;
	private Image logo;
	private Button help;
	private Button btnMenuPlay;
	private Button btnMenuCredits;
	private Button btnLeaderboard;


	// debug
	private final float DEBUG_REBUILD_INTERVAL = 5.0f;
	private boolean debugEnabled = false;
	private float debugRebuildStage;

	public MenuScreen (Game game) {
		super(game);
	}

	private void rebuildStage () {
		skinMustacheJustice = new Skin(
				Gdx.files.internal(Constants.SKIN_MUSTACHEJUSTICE_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		
		
		// build all layers
		Table layerBackground = buildBackgroundLayer();
		Table layerObjects = buildObjectsLayer();
		Table layerLogos = buildLogosLayer();
		Table layerControls = buildControlsLayer();
		Table layerCreditsWindow = buildCreditsWindowLayer();
		Table layerHelpButton = buildHelpLayer();
		// assemble stage for menu screen
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerObjects);
		stack.add(layerLogos);
		stack.add(layerControls);
		stack.add(layerHelpButton);
		stage.addActor(layerCreditsWindow);
	}

	// Build Parameters for Layouts

	private Table buildBackgroundLayer () {
		Table layer = new Table();
		return layer;
	}

	private Table buildObjectsLayer () {
		Table layer = new Table();
		// + Background
		background = new Image(skinMustacheJustice, "background_main_menu");
		layer.add(background);
		return layer;
	}

	private Table buildLogosLayer () {
		Table layer = new Table();
		layer.center().top();
		// + Game Logo
		logo = new Image(skinMustacheJustice, "logo");
		layer.add(logo);
		if (debugEnabled) layer.debug();
		return layer;
	}

	private Table buildControlsLayer () {
		Table layer = new Table();
		layer.left().center().padTop(100);
		//  Add Play Button
		btnMenuPlay = new Button(skinMustacheJustice, "play");
		layer.add(btnMenuPlay);
		btnMenuPlay.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onPlayClicked();
			}
		});
		//		layer.row();
		// + Options Button
		btnMenuCredits = new Button(skinMustacheJustice, "credits");
		layer.add(btnMenuCredits).right();
		btnMenuCredits.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onCreditsClicked();
			}
		});

		if (debugEnabled) layer.debug();

		return layer;
	}

	private Table buildHelpLayer () {
		Table layer = new Table();
		layer.center().bottom().padBottom(50);
		// Add Help Button
		btnLeaderboard = new Button(skinMustacheJustice, "leaderboard");
		help = new Button(skinMustacheJustice, "help");
		layer.add(btnLeaderboard);
		layer.add(help);
		help.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onHelpClicked();
			}
		});
		btnLeaderboard.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onLeaderboardClicked();
			}
		});
		if (debugEnabled) layer.debug();

		return layer;
	}
	private Table buildCreditsWindowLayer () {
		Table layer = new Table();
		return layer;
	}

	@Override
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (debugEnabled) {
			debugRebuildStage -= deltaTime;
			if (debugRebuildStage <= 0) {
				debugRebuildStage = DEBUG_REBUILD_INTERVAL;
				rebuildStage();
			}
		}
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
		skinMustacheJustice.dispose();
	}

	@Override public void pause () { 

	}

	private void onPlayClicked () {
		game.setScreen(new GameScreen(game));
	}
	
	private void onCreditsClicked(){
		game.setScreen(new CreditsScreen(game));
	}

	private void onHelpClicked() { 
		game.setScreen(new HelpScreen(game));
	}
	
	private void onLeaderboardClicked() { 
//		game.setScreen(new HelpScreen(game));
		Swarm.showLeaderboards();
	}
}
