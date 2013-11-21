package com.hyrt.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hyrt.base.BaseActivity;
import com.hyrt.locationschool.LocationSchoolActivity;

/**
 * Created by GYH on 13-11-18.
 */
public class LogoActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        findViewById(R.id.sbggbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivity(intent.setClass(LogoActivity.this,LocationSchoolActivity.class));
            }
        });
    }
}
