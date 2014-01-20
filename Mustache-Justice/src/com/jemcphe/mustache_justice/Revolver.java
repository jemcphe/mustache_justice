package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Revolver extends AbstractGameObject {
	private TextureRegion revolverRegion;
	public boolean collected;

	public Revolver () {
		init();
	}

	private void init () {
		dimension.set(0.5f, 0.5f);
		revolverRegion = Assets.instance.gun.revolver;
		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		collected = false;
	}
	public void render (SpriteBatch batch) {
		if (collected) return;
		TextureRegion region = null;
		region = revolverRegion;
		batch.draw(region.getTexture(),
				position.x, position.y,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				region.getRegionX(), region.getRegionY(),
				region.getRegionWidth(), region.getRegionHeight(),
				false, false);
	}
	public int getScore() {
		return 250;
	}
}
