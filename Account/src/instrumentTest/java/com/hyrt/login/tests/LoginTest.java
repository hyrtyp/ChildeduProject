package com.hyrt.login.tests;

import android.app.Instrumentation;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.view.Menu;
import com.hyrt.login.LoginActivity;
import com.hyrt.login.R;
import com.squareup.spoon.Spoon;


/**
 * Tests for LoginActivity.
 */
public class LoginTest extends ActivityTest<LoginActivity> {


    public LoginTest() {
        super(LoginActivity.class);
    }

    private Instrumentation instrumentation;
    private LoginActivity activity;

    private EditText username;
    private EditText password;

    private Menu login;

    @Override protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();

        username = editText(R.id.usernameid);
        password = editText(R.id.passwordid);
        login = (Menu)view(R.id.action_websearch);
    }

    public void testPreconditions() {
        assertNotNull("activity is not null",activity);
        assertNotNull("username_edittext is not null",username);
        assertNotNull("password_edittext is not null",password);
    }

    public void testEditText_layout() throws Throwable {
        final View decorview=activity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorview,username);
        Spoon.screenshot(activity,"step1");
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                username.setText("successfully");
            }
        });

        instrumentation.waitForIdleSync();
        Spoon.screenshot(activity, "step2");
        focus(password);

    }

}
