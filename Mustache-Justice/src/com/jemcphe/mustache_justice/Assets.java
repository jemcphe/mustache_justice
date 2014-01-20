package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.jemcphe.mustache_justice.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets implements Disposable, AssetErrorListener{

	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;

	// singleton: prevent instantiation from other classes
	private Assets () {

	}

	public AssetMaxCassidy maxCassidy;
	public AssetLives lives;
	public AssetGround ground;
	public AssetButtonOverlay buttonOverlay;
	public AssetDonut donut;
	public AssetGun gun;
	public AssetEnemy enemy;

	public void init (AssetManager assetManager) {
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS_1, TextureAtlas.class);
		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()){
			Gdx.app.debug(TAG, "asset: " + a);
		}
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS_1);

		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures())t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// create game resource objects
		fonts = new AssetFonts();
		maxCassidy = new AssetMaxCassidy(atlas);
		lives = new AssetLives(atlas);
		ground = new AssetGround(atlas);
		donut = new AssetDonut(atlas);
		gun = new AssetGun(atlas);
		buttonOverlay = new AssetButtonOverlay(atlas);
		enemy = new AssetEnemy(atlas);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'", (Exception)throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		fonts.defaultSmall.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultBig.dispose();
	}

	/********************* Asset Classes **********************/

	public AssetFonts fonts;
	public class AssetFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;
		public AssetFonts () {
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(
					Gdx.files.internal("images/arial-15.fnt"), true);
			defaultNormal = new BitmapFont(
					Gdx.files.internal("images/arial-15.fnt"), true);
			defaultBig = new BitmapFont(
					Gdx.files.internal("images/arial-15.fnt"), true);
			// set font sizes
			defaultSmall.setScale(0.75f);
			defaultNormal.setScale(1.0f);
			defaultBig.setScale(2.0f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}

	public class AssetMaxCassidy {
		public AtlasRegion playerPos1;
		public AtlasRegion playerPos2;
		public AtlasRegion playerPos3;
		public AtlasRegion playerPos4;
		public AtlasRegion playerPos5;
		public AssetMaxCassidy (TextureAtlas atlas) {
			playerPos1 = atlas.findRegion("max_0");	
			playerPos1 = atlas.findRegion("max_1");
			playerPos1 = atlas.findRegion("max_2");
			playerPos1 = atlas.findRegion("max_3");
			playerPos1 = atlas.findRegion("max_4");
		}
	}

	public class AssetLives {
		public AtlasRegion lives;
		public AssetLives (TextureAtlas atlas){
			lives = atlas.findRegion("player");
		}
	}
	
	public class AssetEnemy {
		public AtlasRegion enemy;
		public AssetEnemy (TextureAtlas atlas){
			enemy = atlas.findRegion("mario");
		}
	}
	
	public class AssetGround {
		public final AtlasRegion middle;
		public final AtlasRegion edge;
		
		public AssetGround (TextureAtlas atlas){
			middle = atlas.findRegion("ground_middle");
			edge = atlas.findRegion("ground_edge");
		}
	}

	public class AssetDonut {
		public final AtlasRegion donut;

		public AssetDonut (TextureAtlas atlas){
			donut = atlas.findRegion("donut");
		}
	}

	public class AssetGun {
		public final AtlasRegion revolver;
		
		public AssetGun (TextureAtlas atlas){
			revolver = atlas.findRegion("revolver_large");
		}
	}
	
	public class AssetButtonOverlay {
		public final AtlasRegion buttonOverlay;

		public AssetButtonOverlay (TextureAtlas atlas){
			buttonOverlay = atlas.findRegion("buttonoverlay");
		}
	}
}
