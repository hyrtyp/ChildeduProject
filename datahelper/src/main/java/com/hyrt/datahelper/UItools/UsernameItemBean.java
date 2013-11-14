package com.hyrt.datahelper.UItools;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by GYH on 13-10-30.
 */
public class UsernameItemBean extends ItemBean{

    public TextView textView;
    public EditText editText;
    private int textviewid;
    private int edittextid;
    public UsernameItemBean(Context context,int textviewid, int edittextid) {
        super(context);
        this.textviewid=textviewid;
        this.edittextid=edittextid;
    }

    @Override
    public View CreateView(int viewid) {
        super.CreateView(viewid);
        textView=(TextView)view.findViewById(textviewid);
        editText=(EditText)view.findViewById(edittextid);
        return view;
    }
}
