package com.hyrt.childedu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.spicelist.BitmapSpiceManager;

import roboguice.activity.RoboActivity;

/**
 * global base activity yepeng12
 */
public class BaseActivity extends RoboSherlockActivity{

    private SpiceManager contentManager = new SpiceManager( JacksonSpringAndroidSpiceService.class );
    private BitmapSpiceManager spiceManagerBinary = new BitmapSpiceManager();
    @Override
    protected void onStart() {
        contentManager.start(this);
        spiceManagerBinary.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        contentManager.shouldStop();
        spiceManagerBinary.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager(){
        return contentManager;
    }

    protected BitmapSpiceManager getSpiceManagerBinary(){
        return spiceManagerBinary;
    }
}
