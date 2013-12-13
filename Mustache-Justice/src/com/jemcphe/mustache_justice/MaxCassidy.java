package com.jemcphe.mustache_justice;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jemcphe.mustache_justice.Assets;

public class MaxCassidy extends AbstractGameObject {
	public static final String TAG = MaxCassidy.class.getName();
	private final float JUMP_TIME_MAX = 0.3f;
	private final float JUMP_TIME_MIN = 0.1f;
	private final float JUMP_TIME_OFFSET_FLYING =
			JUMP_TIME_MAX - 0.018f;

	public enum VIEW_DIRECTION { 
		LEFT, 
		RIGHT 
	}

	public enum JUMP_STATE {
		GROUNDED, FALLING, JUMP_RISING, JUMP_FALLING
	}
	private TextureRegion maxRegion;
	public VIEW_DIRECTION viewDirection;
	public float timeJumping;
	public JUMP_STATE jumpState;

	public MaxCassidy () {
		init();
	}

	public void init () {
		dimension.set(.5f, 1f);
		maxRegion = Assets.instance.maxCassidy.player;

		// Center image on game object
		origin.set(dimension.x / 2, dimension.y / 2);

		// Bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);

		// Set physics values
		terminalVelocity.set(3.0f, 4.0f);
		friction.set(12.0f, 0.0f);
		acceleration.set(0.0f, -25.0f);

		// View direction
		viewDirection = VIEW_DIRECTION.RIGHT;

		// Jump state
		jumpState = JUMP_STATE.FALLING;
		timeJumping = 0;

	};

	public void setJumping (boolean jumpKeyPressed) {
		switch (jumpState) {
		case GROUNDED: // Character is standing on a platform
			if (jumpKeyPressed) {
				// Start counting jump time from the beginning
				timeJumping = 0;
				jumpState = JUMP_STATE.JUMP_RISING;
			}
			break;
		case JUMP_RISING: // Rising in the air
			if (!jumpKeyPressed)
				jumpState = JUMP_STATE.JUMP_FALLING;
			break;
		case FALLING:// Falling down
		case JUMP_FALLING: // Falling down after jump
			if (jumpKeyPressed) {
				timeJumping = JUMP_TIME_OFFSET_FLYING;
				jumpState = JUMP_STATE.JUMP_RISING;
			}
			break;
		}
	}

	@Override
	public void update (float deltaTime) {
		super.update(deltaTime);
		if (velocity.x != 0) {
			viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT
					: VIEW_DIRECTION.RIGHT;
		}
	}

	@Override
	protected void updateMotionY (float deltaTime) {
		switch (jumpState) {
		case GROUNDED:
			jumpState = JUMP_STATE.FALLING;
			break;
		case JUMP_RISING:
			// Keep track of jump time
			timeJumping += deltaTime;
			// Jump time left?
			if (timeJumping <= JUMP_TIME_MAX) {
				// Still jumping
				velocity.y = terminalVelocity.y;
			}
			break;
		case FALLING:
			break;
		case JUMP_FALLING:
			// Add delta times to track jump time
			timeJumping += deltaTime;
			// Jump to minimal height if jump key was pressed too short
			if (timeJumping > 0 && timeJumping <= JUMP_TIME_MIN) {
				// Still jumping
				velocity.y = terminalVelocity.y;
			}
		}
		if (jumpState != JUMP_STATE.GROUNDED)
			super.updateMotionY(deltaTime);
	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = null;
		// Draw image
		region = maxRegion;
		batch.draw(region.getTexture(),
				position.x, position.y,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				region.getRegionX(), region.getRegionY(),
				region.getRegionWidth(), region.getRegionHeight(),
				viewDirection == VIEW_DIRECTION.LEFT, false);
		// Reset color to white
		batch.setColor(0, 0, 0, 0);
	};
}
