package com.hyrt.datahelper.UItools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by GYH on 13-10-30.
 */
public class ItemBean {

    public Context context;
    public View view;
    public int ViewID;
    public LayoutInflater layoutInflater;
    private String className = "";
    private String properties = "";

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public int getViewID() {
        return ViewID;
    }

    public void setViewID(int viewID) {
        ViewID = viewID;
    }

    public ItemBean(Context context){
        this.context=context;
    }

    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }

    public View CreateView(int viewid){
        this.ViewID=viewid;
        layoutInflater= LayoutInflater.from(context);
        view = layoutInflater.inflate(viewid,null);
        return view;
    }
}
