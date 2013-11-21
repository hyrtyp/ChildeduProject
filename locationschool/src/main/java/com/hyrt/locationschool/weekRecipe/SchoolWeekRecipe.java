package com.hyrt.locationschool.weekRecipe;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.locationschool.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolWeekRecipe extends RoboSherlockFragment {

    private RoboSherlockFragment fragment;//显示内容碎片
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public SchoolWeekRecipe(FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schoolweekrecipe, container, false);

        initView(rootView);

        return rootView;
    }

    private void initView(View rootView){
        ListView listViewRecipe = (ListView)rootView.findViewById(R.id.layout_listview);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<6;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("AnnouncementName", "食谱时间-------");
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(getActivity(),listItem,//数据源
                R.layout.layout_textview_item,
                new String[] {"AnnouncementName"},
                new int[] {R.id.textview}
        );
        //添加并且显示
        listViewRecipe.setAdapter(listItemAdapter);
        listViewRecipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment = new SchoolWeekRecipeDescribsed();
                fragmentTransaction.replace(R.id.school_container, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
