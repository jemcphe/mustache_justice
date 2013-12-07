package com.jemcphe.mustache_justice;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;


public class WorldController implements InputProcessor{

	Sound footSteps = Gdx.audio.newSound(Gdx.files.internal("data/running.mp3"));
	
	public class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }
	
	public Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
	
	enum Keys {
		LEFT, RIGHT, JUMP, HIT
	}
		
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.HIT, false);
	}
	
	// ** Key presses and touches **************** //	 
	 public void leftPressed() {
	  keys.get(keys.put(Keys.LEFT, true));
	 }
	 
	 public void rightPressed() {
		 footSteps.play();
	 }
	 
	 public void jumpPressed() {
	  keys.get(keys.put(Keys.JUMP, true));
	 }
	 
	 public void hitPressed() {
	  keys.get(keys.put(Keys.HIT, false));
	 }
	 
	 public void leftReleased() {
	  keys.get(keys.put(Keys.LEFT, false));
	 }
	 
	 public void rightReleased() {
	  footSteps.stop();
	 }
	 
	 public void jumpReleased() {
	  keys.get(keys.put(Keys.JUMP, false));
	 }
	 
	 public void hitReleased() {
	  keys.get(keys.put(Keys.HIT, false));
	 }
	 
	 public void update(){
		 processInput();
	 }
	 
	 private void processInput(){
		 
	 }

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(pointer < 5){
			System.out.println("Touch Down!");
			if (screenX >= 65 && screenX <= 180 && screenY >= 600 && screenY <= 700){
				System.out.println("Left Button Pressed");
			}
			
//            touches.get(pointer).touchX = screenX;
//            touches.get(pointer).touchY = screenX;
//            touches.get(pointer).touched = true;
        }
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5){
			System.out.println("Touch Up!");
//            touches.get(pointer).touchX = 0;
//            touches.get(pointer).touchY = 0;
//            touches.get(pointer).touched = false;
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

}
