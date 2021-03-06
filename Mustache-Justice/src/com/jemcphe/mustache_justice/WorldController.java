package com.jemcphe.mustache_justice;

import com.jemcphe.mustache_justice.Constants;
import com.jemcphe.mustache_justice.MaxCassidy.JUMP_STATE;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.jemcphe.mustache_justice.MaxCassidy;
import com.jemcphe.mustache_justice.Ground;
import com.jemcphe.mustache_justice.Donut;
import com.badlogic.gdx.Game;
import com.jemcphe.mustache_justice.MenuScreen;
import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmAchievement;
import com.swarmconnect.SwarmLeaderboard;

public class WorldController implements InputProcessor {

	private Game game;

	private void backToMenu () {
		// switch to menu screen
		game.setScreen(new MenuScreen(game));
	}

	private static final String TAG = WorldController.class.getName();

	public Level level;
	public int lives;
	public int score;
	public int donutCount;
	public CameraHelper cameraHelper;
	float camMoveSpeed;
	float camMoveSpeedAccelerationFactor;
	boolean rightIsTouched = false;
	boolean leftIsTouched = false;
	private float timeLeftGameOverDelay;
	private float timeLeftGameWonDelay;

	// Rectangles for collision detection
	private Rectangle r1 = new Rectangle();
	private Rectangle r2 = new Rectangle();

	private void onCollisionMaxWithGround(Ground ground) {
		MaxCassidy max = level.max;
		float heightDifference = Math.abs(max.position.y
				- ( ground.position.y
						+ ground.bounds.height));
		if (heightDifference > 0.25f) {
			boolean hitLeftEdge = max.position.x
					> ( ground.position.x
							+ ground.bounds.width / 2.0f);
					if (hitLeftEdge) {
						max.position.x = ground.position.x
								+ ground.bounds.width;
					} else {
						max.position.x = ground.position.x
								- ground.bounds.width;
					}
					return;
		}
		switch (max.jumpState) {
		case GROUNDED:
			break;
		case FALLING:
		case JUMP_FALLING:
			max.position.y = ground.position.y
			+ max.bounds.height
			+ max.origin.y;
			max.jumpState = JUMP_STATE.GROUNDED;
			break;
		case JUMP_RISING:
			max.position.y = ground.position.y
			+ max.bounds.height
			+ max.origin.y;
			break;
		}
	};

	private void onCollisionEnemyWithGround(Enemy enemy){

		enemy.killed = true;
		score += enemy.getScore();
		Gdx.app.log(TAG, "Enemy Killed");

		//		Enemy enemy = level.enemy;
		//		float heightDifference = Math.abs(enemy.position.y
		//				- ( ground.position.y
		//						+ ground.bounds.height));
		//		if (heightDifference > 0.25f) {
		//			boolean hitLeftEdge = enemy.position.x
		//					> ( ground.position.x
		//							+ ground.bounds.width / 2.0f);
		//					if (hitLeftEdge) {
		//						enemy.position.x = ground.position.x
		//								+ ground.bounds.width;
		//					} else {
		//						enemy.position.x = ground.position.x
		//								- ground.bounds.width;
		//					}
		//					return;
		//		}
	}

	private void onCollisionMaxWithDonut(Donut donut) {
		donut.collected = true;
		score += donut.getScore();
		donutCount += donut.getCount();
		nomSound.play(.3f);
		
		if (donutCount == 5){
			SwarmAchievement.unlock(18106);
			score += 250;
		}
		
		if (donutCount == 20){
			SwarmAchievement.unlock(18108);
			score += 500;
		}
		
		if (donutCount == 70){
			SwarmAchievement.unlock(18110);
			score += 1000;
		}
		
		System.out.println("Donuts Collected: " + donutCount);
		
	};

	private void testCollisions () {
		r1.set(level.max.position.x,
				level.max.position.y,
				level.max.bounds.width,
				level.max.bounds.height);
		// Test collision: Max Cassidy <-> Ground
		for (Ground object : level.ground) {
			r2.set(object.position.x, object.position.y,
					object.bounds.width, object.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionMaxWithGround(object);
		}
		// Test collision: Max Cassidy <-> Donuts
		for (Donut donut : level.donuts) {
			if (donut.collected) continue;
			r2.set(donut.position.x, donut.position.y,
					donut.bounds.width, donut.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionMaxWithDonut(donut);
			break;
		}

		// Text collision: Max Cassidy <-> Enemy
		for (Enemy enemy : level.enemy) {
			if (enemy.killed) continue;
			r2.set(enemy.position.x, enemy.position.y,
					enemy.bounds.width, enemy.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionEnemyWithGround(enemy);
			break;
		}

		// Test collision: Max Cassidy <-> Gun
		for (Revolver revolver : level.revolver) {
			if (revolver.collected) continue;
			r2.set(revolver.position.x, revolver.position.y,
					revolver.bounds.width, revolver.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionMaxWithGun(revolver);
			break;
		}

	}

	private void onCollisionMaxWithGun(Revolver revolver) {
		revolver.collected = true;
		score += revolver.getScore();
		Gdx.app.log(TAG, "Revolver collected");
	};

	// Footsteps Sound
	Sound footSteps = Gdx.audio.newSound(Gdx.files.internal("data/running.mp3"));
	// Jump Sound
	Sound jumpSound = Gdx.audio.newSound(Gdx.files.internal("data/jump_sound.mp3"));
	// Swing Sound
	Sound swingSound = Gdx.audio.newSound(Gdx.files.internal("data/arm_swing.mp3"));
	// Hit Sound
	Sound hitSound = Gdx.audio.newSound(Gdx.files.internal("data/hit_sound.mp3"));
	// Nom Sound
	Sound nomSound = Gdx.audio.newSound(Gdx.files.internal("data/nom_sound.wav"));

	public WorldController(Game game) {
		this.game = game;
		init();

	}

	private void initLevel() {
		score = 0;
		donutCount = 0;
		level = new Level(Constants.LEVEL_01);
		cameraHelper.setTarget(level.max);
	}

	private void init() {
		Gdx.input.setInputProcessor(this);
		Swarm.setLeaderboardNotificationsEnabled(false);
		cameraHelper = new CameraHelper();
		lives = Constants.LIVES_START;
		timeLeftGameOverDelay = 0;
		timeLeftGameWonDelay = Constants.TIME_DELAY_GAME_WON;
		initLevel();
	}

	public void update (float deltaTime) {
		//		handleDebugInput(deltaTime);
		if (isGameOver()) {
			System.out.println("Game Over");
			SwarmAchievement.unlock(18114);
			timeLeftGameOverDelay -= deltaTime;
			if (timeLeftGameOverDelay < 0) backToMenu();
		} else {
			handleInputGame(deltaTime);
		}
		
		if (isGameWon()) {
			leftIsTouched = false;
			rightIsTouched = false;
			timeLeftGameWonDelay -= deltaTime;
			// Check for Mustache Manimal Achievement
			if(lives == Constants.LIVES_START && donutCount == 70){
				SwarmAchievement.unlock(18112);
			}
			SwarmLeaderboard.submitScore(13438, (float) score);
			SwarmAchievement.unlock(18104);
			if (timeLeftGameWonDelay < 0){
				backToMenu();
			}
		} else {
			handleInputGame(deltaTime);
		}

		level.update(deltaTime);
		testCollisions();

		if(Gdx.input.isTouched()){
			if(rightIsTouched == true){
				for (int i = 0; i<=105; i++){
					if (level.max.position.x <= i){
//						System.out.println("rightIsTouched");
						level.max.velocity.x = level.max.terminalVelocity.x;

					}
				}
			}
			if(leftIsTouched == true){
				for (int i = 0; i<=120; i++){
					if (level.max.position.x >= i){
//						System.out.println("leftIsTouched");
						level.max.velocity.x = -level.max.terminalVelocity.x;

					}
				}
			}

		}

		cameraHelper.update(deltaTime);

		if (!isGameOver() && isPlayerInAbyss()) {
			lives--;
			if (isGameOver())
				timeLeftGameOverDelay = Constants.TIME_DELAY_GAME_OVER;
			else
				initLevel();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		// Check for Android's Back Button pressed
		if (keycode == Keys.BACK){
			backToMenu();
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer <= 2){
//			System.out.println("Touch Down!");
			// Left Button
			if (screenX >= 65 && screenX <= 180 && screenY >= 600 && screenY <= 730){
//				System.out.println("Left Button Pressed");
				rightIsTouched = false;
				leftIsTouched = true;
				footSteps.setLooping(0, true);
				footSteps.play();
			}

			// Right Button
			if (screenX >= 240 && screenX <= 370 && screenY >= 600 && screenY <= 730){
//				System.out.println("Right Button Pressed");
				rightIsTouched = true;
				leftIsTouched = false;
				footSteps.setLooping(0, true);
				footSteps.play();
			}

			// Jump Button
			if (screenX >= 775 && screenX <= 910 && screenY >= 610 && screenY <= 745){
//				System.out.println("Jump Button Pressed");
				/*
				 * If in the air at all, disable jumping ability and jumping sound.
				 * ONLY when GROUNDED, can the player jump.
				 */
				if(level.max.jumpState == JUMP_STATE.JUMP_RISING || level.max.jumpState == JUMP_STATE.JUMP_FALLING){
					level.max.setJumping(false);
				} else {
					jumpSound.play(.2f);
					level.max.setJumping(true);
				}
			}

			// Punch Button
			if (screenX >= 955 && screenX <= 1090 && screenY >= 610 && screenY <= 745){
//				System.out.println("Punch Button Pressed");
				swingSound.play();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer < 1){
//			System.out.println("Touch Up! No Fingers On Screen");
			footSteps.stop();
			level.max.setJumping(false);
			rightIsTouched = false;
			leftIsTouched = false;
		} else {
			if(leftIsTouched == true){
//				System.out.println("Still one finger on screen");
				rightIsTouched = false;
				level.max.setJumping(false);
			}
			if(rightIsTouched == true){
//				System.out.println("Still one finger on screen");
				leftIsTouched = false;
				level.max.setJumping(false);

			}

		}

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private void handleInputGame (float deltaTime) {
		if (cameraHelper.hasTarget(level.max)) {
			// Player Movement
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				level.max.velocity.x =
						-level.max.terminalVelocity.x;
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				level.max.velocity.x =
						level.max.terminalVelocity.x;
			} else {
				/*
				 * Execute auto-forward movement on non-desktop platform.
				 * EXCLUDE FROM FINAL BUILD.
				 */
				if (Gdx.app.getType() != ApplicationType.Desktop) {
					//	level.max.velocity.x = level.max.terminalVelocity.x;
				}
			}
			// Player Jump
			if (Gdx.input.isKeyPressed(Keys.SPACE))
				level.max.setJumping(true);
		} else {
			level.max.setJumping(false);
		}
	}

	// Game Won
	public boolean isGameWon() {
		return level.max.position.x >= 105;
	}

	// Game Over
	public boolean isGameOver() {
		return lives < 0;
	}
	// Player Fell into the Abyss
	public boolean isPlayerInAbyss () {
		return level.max.position.y < -5;
	}




}