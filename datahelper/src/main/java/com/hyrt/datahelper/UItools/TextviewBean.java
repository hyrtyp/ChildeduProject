package com.hyrt.datahelper.UItools;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GYH on 13-10-30.
 */
public class TextviewBean extends ItemBean {

    public TextView textView;
    private int textviewid;

    public TextviewBean(Context context,int textviewid) {
        super(context);
        this.textviewid=textviewid;
    }

    @Override
    public View CreateView(int viewid) {
        super.CreateView(viewid);
        textView=(TextView)view.findViewById(textviewid);
        return view;
    }
}
