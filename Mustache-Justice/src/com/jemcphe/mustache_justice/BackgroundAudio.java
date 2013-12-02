package com.jemcphe.mustache_justice;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundAudio implements ApplicationListener {

	Music funkyTune;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		funkyTune = Gdx.audio.newMusic(Gdx.files.internal("data/vig_justice.mp3"));
		
		// loop through funkyTune
		funkyTune.setLooping(true);
		funkyTune.setVolume(0.4f);
		funkyTune.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
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
		funkyTune.dispose();
	}

}
