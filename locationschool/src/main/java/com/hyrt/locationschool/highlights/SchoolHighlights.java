package com.hyrt.locationschool.highlights;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.locationschool.R;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolHighlights extends RoboSherlockFragment {

    public SchoolHighlights(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schoolhighlights, container, false);
        return rootView;
    }
}
