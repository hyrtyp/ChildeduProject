package com.hyrt.login.tests;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

/**
 * Created by GYH on 13-11-14.
 */
public abstract class ActivityTest  <T extends Activity> extends ActivityInstrumentationTestCase2<T>{

    public ActivityTest(Class<T> activityClass) {
        super(activityClass);
    }

    /**
     * Verify activity was created successfully
     */
    public void testActivityIsCreated() {
        assertNotNull(getActivity());
    }

    /**
     * Get edit text with id
     *
     * @param id
     * @return edit text
     */
    protected EditText editText(final int id) {
        return (EditText) view(id);
    }

    /**
     * Get view with id
     *
     * @param id
     * @return edit text
     */
    protected View view(final int id) {
        assertNotNull(getActivity());
        View view = getActivity().findViewById(id);
        assertNotNull(view);
        return view;
    }

    /**
     * Send focus to view
     *
     * @param view
     * @throws Throwable
     */
    protected void focus(final View view) throws Throwable {
        ui(new Runnable() {

            public void run() {
                view.requestFocus();
            }
        });
    }

    /**
     * Run runnable on ui thread
     *
     * @param runnable
     * @throws Throwable
     */
    protected void ui(Runnable runnable) throws Throwable {
        runTestOnUiThread(runnable);
    }

    /**
     * Send text
     *
     * @param text
     */
    protected void send(final String text) {
        getInstrumentation().sendStringSync(text);
    }
}
