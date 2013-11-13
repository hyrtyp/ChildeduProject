package com.hyrt.login.test;

import android.app.Instrumentation;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.hyrt.login.LoginActivity;
import com.hyrt.login.R;
import com.squareup.spoon.Spoon;

import java.util.Random;

import static org.fest.assertions.api.ANDROID.assertThat;

/**
 * Tests for MainActivity.
 */
public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {


    public LoginTest() {
        super(LoginActivity.class);
    }

    private Instrumentation instrumentation;
    private LoginActivity activity;

    private EditText username;
    private EditText password;
//    private Button login;

    @Override protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();

        username = (EditText) activity.findViewById(R.id.usernameid);
        password = (EditText) activity.findViewById(R.id.passwordid);
//        login = (Button) activity.findViewById(R.id.login);
    }

    public void testEmptyForm_ShowsBothErrors() {
        Spoon.screenshot(activity, "initial_state");

        // Make sure the initial state does not show any errors.
        assertThat(username).hasNoError();
        assertThat(password).hasNoError();

        // Click the "login" button.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
//                login.performClick();
            }
        });
        instrumentation.waitForIdleSync();

        Spoon.screenshot(activity, "login_clicked");

        // Verify errors were shown for both input fields.
        assertThat(username).hasError(R.string.required);
        assertThat(password).hasError(R.string.required);
    }

    public void testBlankPassword_ShowsError() {
        Spoon.screenshot(activity, "initial_state");

        // Make sure the initial state does not show any errors.
        assertThat(username).hasNoError();
        assertThat(password).hasNoError();

        // Type a value into the username field.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                username.setText("jake");
            }
        });
        instrumentation.waitForIdleSync();
        Spoon.screenshot(activity, "username_entered");

        // Click the "login" button.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
//                login.performClick();
            }
        });
        Spoon.screenshot(activity, "login_clicked");

        // Verify error was shown only for password field.
        assertThat(username).hasNoError();
        assertThat(password).hasError(R.string.required);
    }

    public void testBlankUsername_ShowsError() {
        Spoon.screenshot(activity, "initial_state");

        // Make sure the initial state does not show any errors.
        assertThat(username).hasNoError();
        assertThat(password).hasNoError();

        // Type a value into the password field.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                password.setText("secretpassword");
            }
        });
        Spoon.screenshot(activity, "password_entered");

        // Click the "login" button.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
//                login.performClick();
            }
        });
        instrumentation.waitForIdleSync();
        Spoon.screenshot(activity, "login_clicked");

        // Verify error was shown only for username field.
        assertThat(username).hasError(R.string.required);
        assertThat(password).hasNoError();
    }

    public void testPasswordTooShort_ShowsError() {
        Spoon.screenshot(activity, "initial_state");

        // Make sure the initial state does not show any errors.
        assertThat(username).hasNoError();
        assertThat(password).hasNoError();

        // Type a value into the username and password field.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                username.setText("jake");
                password.setText("secret");
            }
        });
        Spoon.screenshot(activity, "values_entered");

        // Click the "login" button.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
//                login.performClick();
            }
        });
        instrumentation.waitForIdleSync();
        Spoon.screenshot(activity, "login_clicked");

        // Verify error was shown only for username field.
        assertThat(username).hasNoError();
        assertThat(password).hasError(R.string.password_length);
    }

    public void testValidValues_StartsNewActivity() {
        IntentFilter filter = new IntentFilter();
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(filter, null, false);

        Spoon.screenshot(activity, "initial_state");

        // Make sure the initial state does not show any errors.
        assertThat(username).hasNoError();
        assertThat(password).hasNoError();

        // Type a value into the username and password field.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                username.setText("jake");
                password.setText("secretpassword");
            }
        });
        Spoon.screenshot(activity, "values_entered");

        // Click the "login" button.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
//                login.performClick();
            }
        });
        instrumentation.waitForIdleSync();

        // Verify new activity was shown.
        assertThat(monitor).hasHits(1);
        Spoon.screenshot(monitor.getLastActivity(), "next_activity_shown");

        // For fun (and to make the output more interesting), randomly fail!
        if (new Random().nextInt(4) == 0) {
            throw new AssertionError("Somebody set up us the bomb.");
        }
    }

}
