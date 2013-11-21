package com.hyrt.locationschool.messageboards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.locationschool.R;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolMessageboards extends RoboSherlockFragment {

    public SchoolMessageboards(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schoolmessageboards, container, false);


        return rootView;
    }
}
