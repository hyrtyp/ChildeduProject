package com.hyrt.datahelper.student.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUser implements Serializable{

    /**
     * 测试数据
     * */
    public String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static class List extends ArrayList<LoginUser> {
    }
}
