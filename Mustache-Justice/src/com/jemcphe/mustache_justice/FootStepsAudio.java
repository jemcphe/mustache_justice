package com.jemcphe.mustache_justice;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class FootStepsAudio implements ApplicationListener {

	Sound footSteps;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		footSteps = Gdx.audio.newSound(Gdx.files.internal("data/running.mp3"));
		
		// loop through funkyTune
		footSteps.setLooping(0, true);
		footSteps.setVolume(0, 0.8f);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		footSteps.play();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		footSteps.dispose();
	}

}
