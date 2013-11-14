package com.hyrt.datahelper.account.service;

import com.hyrt.datahelper.account.model.Accountmodel;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by GYH on 13-10-16.
 */
public class AccountService {

    public Accountmodel login(SpringAndroidSpiceRequest request,String username,String password) {
        String url="https://api.github.com/repos/octo-online/robospice/contributors";
        return request.getRestTemplate().getForObject( url,Accountmodel.class );
    }
}
