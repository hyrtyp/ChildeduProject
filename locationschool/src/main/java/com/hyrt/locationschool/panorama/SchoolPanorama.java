package com.hyrt.locationschool.panorama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.locationschool.R;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolPanorama extends RoboSherlockFragment {

    public SchoolPanorama(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schoolpanorama, container, false);


        return rootView;
    }
}
