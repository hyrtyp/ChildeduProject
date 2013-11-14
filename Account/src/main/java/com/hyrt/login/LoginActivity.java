package com.hyrt.login;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.hyrt.base.BaseActivity;
import com.hyrt.datahelper.account.model.Accountmodel;
import com.hyrt.datahelper.account.request.Accountrequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class LoginActivity extends BaseActivity {

    private String username;
    private String password;
    private Accountrequest accountrequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add("Login")
//                .setChecked(false)
//                .setTitle("登录")
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.findItem(R.id.action_websearch);
        menu.add("Login")
                .setChecked(false)
                .setTitle("登录")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"login",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void checkLogindata(){

    }


    private void performRequest() {
        accountrequest=new Accountrequest(username,password);
        getSpiceManager().execute(accountrequest, accountrequest.createCacheKey(), DurationInMillis.ONE_MINUTE, new LoginRequestListener());
    }

    /*
    *数据请求监听器
    * */

    private class LoginRequestListener implements RequestListener<Accountmodel> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(Accountmodel accountmodel) {

        }
    }
}
