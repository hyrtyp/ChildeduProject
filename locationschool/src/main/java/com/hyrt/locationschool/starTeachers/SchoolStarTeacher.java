package com.hyrt.locationschool.starTeachers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.locationschool.R;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolStarTeacher extends RoboSherlockFragment {

    private RoboSherlockFragment fragment;//显示内容碎片
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    public SchoolStarTeacher(FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schoolstarteacher, container, false);
        LinearLayout linearLayout=(LinearLayout)rootView.findViewById(R.id.layout_starteacher);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new SchoolStarTeacherDescribed();
                fragmentTransaction.replace(R.id.school_container,fragment);
                fragmentTransaction.commit();

            }
        });
        return rootView;
    }
}
