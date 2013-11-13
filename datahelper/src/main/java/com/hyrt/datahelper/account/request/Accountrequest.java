package com.hyrt.datahelper.account.request;

import com.hyrt.datahelper.account.model.Accountmodel;
import com.hyrt.datahelper.account.service.AccountService;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by GYH on 13-10-16.
 */
public class Accountrequest extends SpringAndroidSpiceRequest<Accountmodel> {
    private String username;
    private String password;

    public Accountrequest(String username,String password) {
        super(Accountmodel.class);
        this.username=username;
        this.password=password;
    }


    @Override
    public Accountmodel loadDataFromNetwork() throws Exception {
        return new AccountService().login(this,username,password);
    }

    /**
     * 创建缓存key
     * */
    public String createCacheKey() {
        return "xueqian." + username;
    }
}
