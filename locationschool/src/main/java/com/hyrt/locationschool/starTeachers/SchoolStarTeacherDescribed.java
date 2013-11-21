package com.hyrt.locationschool.starTeachers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.locationschool.R;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolStarTeacherDescribed extends RoboSherlockFragment {

    public SchoolStarTeacherDescribed(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getSherlockActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.fragment_schoolstarteacher_described, container, false);
        return rootView;
    }
}
