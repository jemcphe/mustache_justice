package com.jemcphe.mustache_justice;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jemcphe.mustache_justice.Assets;

public class Donut extends AbstractGameObject {
	private TextureRegion donutRegion;
	public boolean collected;

	public Donut () {
		init();
	}

	private void init () {
		dimension.set(0.5f, 0.5f);
		donutRegion = Assets.instance.donut.donut;
		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		collected = false;
	}
	public void render (SpriteBatch batch) {
		if (collected) return;
		TextureRegion region = null;
		region = donutRegion;
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
		return 100;
	}
	public int getCount() {
		return 1;
	}
}
