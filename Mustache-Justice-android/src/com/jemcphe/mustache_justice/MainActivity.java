package com.jemcphe.mustache_justice;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.swarmconnect.Swarm;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Swarm.setActive(this);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        initialize(new MustacheJusticeGame(), cfg);
    }
    
    public void onResume() {
        super.onResume();
        Swarm.setActive(this);
        
        // Replace MY_APP_ID with your App ID from the Swarm Admin Panel
        // Replace MY_APP_KEY with your string App Key from the Swarm Admin Panel
        Swarm.init(this, 9172, "090176c63bad76344fac1cfcb4b5bf98");
    }

    public void onPause() {
        super.onPause();
        Swarm.setInactive(this);
    }
}