package com.hyrt.childeduparent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GYH on 13-11-11.
 */
public class ChildparentBottommenu extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.childparent_bottom_menu,container, false);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
