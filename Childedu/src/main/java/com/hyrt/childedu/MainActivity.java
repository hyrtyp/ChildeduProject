package com.hyrt.childedu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.hyrt.datahelper.model.LoginUser;
import com.hyrt.datahelper.student.request.LoginRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.activity.event.OnActivityResultEvent;
import roboguice.activity.event.OnContentChangedEvent;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnNewIntentEvent;
import roboguice.activity.event.OnPauseEvent;
import roboguice.activity.event.OnRestartEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.activity.event.OnStartEvent;
import roboguice.activity.event.OnStopEvent;
import roboguice.event.EventManager;
import roboguice.inject.ContentViewListener;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;
import roboguice.service.event.OnConfigurationChangedEvent;
import roboguice.service.event.OnDestroyEvent;

/**
 * main activity
 */
public class MainActivity extends BaseActivity{

    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>,Object>();
    protected EventManager eventManager;
    @InjectView(R.id.text) private TextView text;
    @Inject
    ContentViewListener ignored; // BUG find a better place to put this


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final RoboInjector injector = RoboGuice.getInjector(this);
        eventManager = injector.getInstance(EventManager.class);
        injector.injectMembersWithoutViews(this);
        super.onCreate(savedInstanceState);
        eventManager.fire(new OnCreateEvent(savedInstanceState));
        setContentView(R.layout.activity_main);
        text.setText("successfully");
//        performRequest();
//        startActivity(new Intent().setClass(MainActivity.this, TeacherActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        eventManager.fire(new OnRestartEvent());
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventManager.fire(new OnStartEvent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventManager.fire(new OnResumeEvent());
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventManager.fire(new OnPauseEvent());
    }

    @Override
    protected void onNewIntent( Intent intent ) {
        super.onNewIntent(intent);
        eventManager.fire(new OnNewIntentEvent());
    }

    @Override
    protected void onStop() {
        try {
            eventManager.fire(new OnStopEvent());
        } finally {
            super.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            eventManager.fire(new OnDestroyEvent());
        } finally {
            try {
                RoboGuice.destroyInjector(this);
            } finally {
                super.onDestroy();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        final Configuration currentConfig = getResources().getConfiguration();
        super.onConfigurationChanged(newConfig);
        eventManager.fire(new OnConfigurationChangedEvent(currentConfig, newConfig));
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        RoboGuice.getInjector(this).injectViewMembers(this);
        eventManager.fire(new OnContentChangedEvent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        eventManager.fire(new OnActivityResultEvent(requestCode, resultCode, data));
    }

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }


    private void performRequest() {
        LoginRequest request = new LoginRequest( "","");
        getSpiceManager().execute(request, request.createCacheKey(), DurationInMillis.ONE_MINUTE, new ListLoginRequestListener());
    }

    private class ListLoginRequestListener implements RequestListener<LoginUser.List> {
        @Override
        public void onRequestFailure( SpiceException e ) {
            Toast.makeText(MainActivity.this, "loginfail", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onRequestSuccess( LoginUser.List listTweets ) {
            Toast.makeText(MainActivity.this, "loginsucess"+listTweets.get(0).getLogin(), Toast.LENGTH_LONG).show();
        }
    }


}
