package com.jemcphe.mustache_justice;

import java.util.HashMap;
import java.util.Map;


public class WorldController {

	enum Keys {
		LEFT, RIGHT, JUMP, HIT
	}
	
	FootStepsAudio footSteps = new FootStepsAudio();
	
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
	  keys.get(keys.put(Keys.RIGHT, true));
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
	  keys.get(keys.put(Keys.RIGHT, false));
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
		 if (keys.get(Keys.LEFT)){
			 // Left is pressed
			 footSteps.create();
		 }
	 }

}
