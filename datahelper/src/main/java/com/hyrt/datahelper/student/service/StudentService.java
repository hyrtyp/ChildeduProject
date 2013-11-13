package com.hyrt.datahelper.student.service;

import com.hyrt.datahelper.student.model.LoginUser;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by GYH on 13-10-12.
 */
public class StudentService {
    public LoginUser.List login(SpringAndroidSpiceRequest request) {
        String url="https://api.github.com/repos/octo-online/robospice/contributors";
        return request.getRestTemplate().getForObject( url, LoginUser.List.class );
    }
}
