package com.jemcphe.mustache_justice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends AbstractGameObject {
//	public static final String TAG = Enemy.class.getName();
//	private final float JUMP_TIME_ENEMY = 0.3f;
//	private final float JUMP_TIME_MIN = 0.1f;
//	private final float JUMP_TIME_OFFSET_FLYING =
//			JUMP_TIME_ENEMY - 0.018f;
	
	public boolean killed = true;
//
//	public enum VIEW_DIRECTION { 
//		LEFT, 
//		RIGHT 
//	}
//
//	public enum JUMP_STATE {
//		GROUNDED, FALLING, JUMP_RISING, JUMP_FALLING
//	}
//	
//	public Animation animation;
	private Texture enemyTexture;
	private TextureRegion enemyRegion;
//	public TextureRegion currentFrame;
//	public VIEW_DIRECTION viewDirection;
//	public float timeJumping;
//	public JUMP_STATE jumpState;
//	private static final int col = 8;
//	private static final int row = 2;

	public Enemy () {
		init();
	}

	public void init () {
		dimension.set(.45f, 1f);
		
		enemyTexture = new Texture(Gdx.files.internal("images/enemy_test.png"));
//		TextureRegion[][] tmp = TextureRegion.split(enemyTexture, enemyTexture.getWidth() / col, enemyTexture.getHeight() / row);
		enemyRegion = new TextureRegion(enemyTexture);
//		
//		int index = 0;
//		for(int i = 0; i < row; i++){
//			for (int j = 0; j < col; j++){
//				enemyRegion[index++] = tmp[i][j];
//			}
//		}
//
//		animation = new Animation(1, enemyRegion);
//		currentFrame = animation.getKeyFrame(1);
		
//		// Center image on game object
//		origin.set(dimension.x / 2, dimension.y / 2);

		// Bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);

//		// Set physics values
//		terminalVelocity.set(3.0f, 4.0f);
//		friction.set(12.0f, 0.0f);
//		acceleration.set(0.0f, -25.0f);
//
//		// View direction
//		viewDirection = VIEW_DIRECTION.RIGHT;
//
//		// Jump state
//		jumpState = JUMP_STATE.FALLING;
//		timeJumping = 0;

	};

//	public void setJumping (boolean jumpKeyPressed) {
//		switch (jumpState) {
//		case GROUNDED: // Character is standing on a platform
//			if (jumpKeyPressed) {
//				// Start counting jump time from the beginning
//				timeJumping = 0;
//				jumpState = JUMP_STATE.JUMP_RISING;
//			}
//			break;
//		case JUMP_RISING: // Rising in the air
//			if (!jumpKeyPressed)
//				jumpState = JUMP_STATE.JUMP_FALLING;
//			break;
//		case FALLING:// Falling down
//		case JUMP_FALLING: // Falling down after jump
//			if (jumpKeyPressed) {
//				timeJumping = JUMP_TIME_OFFSET_FLYING;
//				jumpState = JUMP_STATE.JUMP_RISING;
//			}
//			break;
//		}
//	}

//	@Override
//	public void update (float deltaTime) {
//		super.update(deltaTime);
//		if (velocity.x != 0) {
//			viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT
//					: VIEW_DIRECTION.RIGHT;
//		}
//	}

//	@Override
//	protected void updateMotionY (float deltaTime) {
//		switch (jumpState) {
//		case GROUNDED:
//			jumpState = JUMP_STATE.FALLING;
//			break;
//		case JUMP_RISING:
//			// Keep track of jump time
//			timeJumping += deltaTime;
//			// Jump time left?
//			if (timeJumping <= JUMP_TIME_ENEMY) {
//				// Still jumping
//				velocity.y = terminalVelocity.y;
//			}
//			break;
//		case FALLING:
//			break;
//		case JUMP_FALLING:
//			// Add delta times to track jump time
//			timeJumping += deltaTime;
//			// Jump to minimal height if jump key was pressed too short
//			if (timeJumping > 0 && timeJumping <= JUMP_TIME_MIN) {
//				// Still jumping
//				velocity.y = terminalVelocity.y;
//			}
//		}
//		if (jumpState != JUMP_STATE.GROUNDED)
//			super.updateMotionY(deltaTime);
//	}

	@Override
	public void render(SpriteBatch batch) {
			if (killed) return;
			TextureRegion region = null;
			region = enemyRegion;
			batch.draw(region.getTexture(),
					position.x, position.y,
					origin.x, origin.y,
					dimension.x, dimension.y,
					scale.x, scale.y,
					rotation,
					region.getRegionX(), region.getRegionY(),
					region.getRegionWidth(), region.getRegionHeight(),
					false, false);
			
//			batch.draw(region.getTexture(),
//					position.x, position.y,
//					origin.x, origin.y,
//					dimension.x, dimension.y,
//					scale.x, scale.y,
//					rotation,
//					region.getRegionX(), region.getRegionY(),
//					region.getRegionWidth(), region.getRegionHeight(),
//					viewDirection == VIEW_DIRECTION.LEFT, false);
			// Reset color to white
			batch.setColor(0, 0, 0, 0);
	};
	
	public int getScore() {
		return 250;
	}
	
}
