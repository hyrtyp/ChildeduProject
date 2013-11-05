package com.hyrt.datahelper.student.request;

import com.hyrt.datahelper.model.LoginUser;
import com.hyrt.datahelper.student.service.StudentService;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by GYH on 13-10-12.
 */
public class LoginRequest extends SpringAndroidSpiceRequest<LoginUser.List> {

    private String username;
    private String password;

    public LoginRequest(String username,String password) {
        super(LoginUser.List.class);
        this.username=username;
        this.password=password;
    }

    @Override
    public LoginUser.List loadDataFromNetwork() throws Exception {
        return new StudentService().login(this);
    }

    /**
     * 创建缓存key
     * */
    public String createCacheKey() {
        return "xueqian." + username;
    }
}
