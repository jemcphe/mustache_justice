package com.jemcphe.mustache_justice;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jemcphe.mustache_justice.Assets;

public class Ground extends AbstractGameObject{

	private TextureRegion regionEdge;
	private TextureRegion regionMiddle;
	private int length;

	public Ground() {
		init();
	}

	private void init () {
		dimension.set(1, 1.5f);
		regionEdge = Assets.instance.ground.edge;
		regionMiddle = Assets.instance.ground.middle;
		// Start length of this rock
		setLength(1);
	}

	public void setLength (int length) {
		this.length = length;
		// Update bounding box for collision detection
		bounds.set(0, 0, dimension.x * length, dimension.y);
	}

	public void increaseLength (int amount) {
		setLength(length + amount);
	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = null;

		float relX = 0;
		float relY = 0;
		// Draw left edge
		region = regionEdge;
		relX -= dimension.x / 4;
		batch.draw(region.getTexture(),
				position.x + relX, position.y + relY,
				origin.x, origin.y,
				dimension.x / 4, dimension.y,
				scale.x, scale.y,
				rotation,
				region.getRegionX(), region.getRegionY(),
				region.getRegionWidth(), region.getRegionHeight(),
				false, false);
		// Draw middle
		relX = 0;
		region = regionMiddle;
		for (int i = 0; i < length; i++) {
			batch.draw(region.getTexture(),
					position.x + relX, position.y + relY,
					origin.x, origin.y,
					dimension.x, dimension.y,
					scale.x, scale.y,
					rotation,
					region.getRegionX(), region.getRegionY(),
					region.getRegionWidth(), region.getRegionHeight(),
					false, false);
			relX += dimension.x;
		}
		// Draw right edge
		region = regionEdge;
		batch.draw(region.getTexture(),
				position.x + relX, position.y + relY,
				origin.x + dimension.x / 8, origin.y,
				dimension.x / 4, dimension.y,
				scale.x, scale.y,
				rotation,
				region.getRegionX(), region.getRegionY(),
				region.getRegionWidth(), region.getRegionHeight(),
				true, false);
	}

}
