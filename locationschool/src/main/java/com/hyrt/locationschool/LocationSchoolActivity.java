package com.hyrt.locationschool;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationSchoolActivity extends BaseActivity {


    private String[] mPlanetTitles;//存储左侧隐藏菜单列表数据
    private DrawerLayout mDrawerLayout;//整体布局
    private ListView mDrawerList;//左侧隐藏菜单列表

    private ActionBarDrawerToggle mDrawerToggle;//actionbar控制左侧隐藏菜单

    private CharSequence mDrawerTitle;//应用标题
    private CharSequence mTitle;//标题



    private ListView listView;
    private Spinner spinnerquyu;
    private Spinner spinnerxingzhi;
    private Spinner spinnerbxgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_school);

//        mFragmentmenu=new MenuFragmentsearch();//实例化搜索菜单
//        ft.add(mFragmentmenu, "menu");//添加搜索菜单
//        ft.commit();//
        initData();
        initView();//初始化布局视图
    }


    /**
     * 初始化数据
     * */
    private void initData(){
        mTitle = mDrawerTitle = getTitle();//初始化acationbar标题
        mPlanetTitles=getResources().getStringArray(R.array.city_array);//获取左侧隐藏菜单列表数据
    }




    /**
     * 定义的标题栏搜索按钮
     * */
    private static class MenuFragmentsearch extends RoboSherlockFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu.add("搜索")
                    .setTitle("搜索")
                    .setShowAsAction(
                            MenuItem.SHOW_AS_ACTION_IF_ROOM
                                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

    }

    /**
     * 初始化布局视图
     * */

    private void initView(){
        listView=(ListView)findViewById(R.id.layout_listview);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<6;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("schoolName", "海淀福大幼儿园");
            map.put("schoolDistance", "200米");
            map.put("schoolNature", "性质：公立");
            map.put("schoolNumber", "职工人数：350人");
            map.put("schoolcreatetime","建院时间：2011年1月28日");
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.layout_location_school_listview_item,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"schoolName","schoolDistance", "schoolNature"
                        ,"schoolNumber", "schoolcreatetime"},
                new int[] {R.id.schoolName,R.id.schoolDistance,R.id.schoolNature
                        ,R.id.schoolNumber,R.id.schoolcreatetime}
        );

        //添加并且显示
        listView.setAdapter(listItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent().setClass(LocationSchoolActivity.this,SchoolInformation.class));
            }
        });

        //初始spinner

        spinnerquyu=(Spinner)findViewById(R.id.spinnerquyu);
        spinnerxingzhi=(Spinner)findViewById(R.id.spinnerxingzhi);
        spinnerbxgm=(Spinner)findViewById(R.id.spinnerbanxueguimo);

        // 建立数据源
        String[] mItemsquyu = getResources().getStringArray(R.array.spinnerquyu);
        ArrayAdapter<String> _Adapterquyu=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item, mItemsquyu);
        spinnerquyu.setAdapter(_Adapterquyu);

        // 建立数据源
        String[] mItemsxingzhi = getResources().getStringArray(R.array.spinnerxingzhi);
        ArrayAdapter<String> _Adapterxingzhi=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item, mItemsxingzhi);
        spinnerxingzhi.setAdapter(_Adapterxingzhi);

        // 建立数据源
        String[] mItemsbxgm = getResources().getStringArray(R.array.spinnerbxgm);
        ArrayAdapter<String> _Adapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item, mItemsbxgm);
        spinnerbxgm.setAdapter(_Adapter);


        //实现左侧隐藏菜单视图初始化

        mDrawerLayout = (DrawerLayout) findViewById(R.id.school_drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.school_left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        ArrayList<HashMap<String, Object>> listItemleft = new ArrayList<HashMap<String, Object>>();

        //初始数据
        for(int i=0;i<mPlanetTitles.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemTitle", mPlanetTitles[i]);
            listItemleft.add(map);
        }

        SimpleAdapter listItemAdapterleft = new SimpleAdapter(this,listItemleft,//数据源
                R.layout.drawer_list_item,//ListItem的XML实现
                new String[] {"itemTitle"},
                new int[] {R.id.text1}
        );

        mDrawerList.setAdapter(listItemAdapterleft);//listview添加adapter

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());//listview添加监听事件

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //左侧actionbar初始化
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.school_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen)
       .setActionView(R.layout.layout_edittext)
       .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);;
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (onOptionsItemSelectedCustom(item)) {
            return true;
        }
//        switch(item.getItemId()) {
//            case R.id.action_websearch:
//                return true;
//            default:
//                Toast.makeText(this, "successfully", Toast.LENGTH_LONG).show();
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
        }
        return false;
    }

    /**
     * 用于判断是否点击左侧隐藏菜单的actionbar
     * */
    public boolean onOptionsItemSelectedCustom(MenuItem item) {
        try {
            if (item != null && item.getItemId() == android.R.id.home
                    && mDrawerToggle.isDrawerIndicatorEnabled()
                    ) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 左侧隐藏菜单列表点击监听事件
     * */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * 左侧隐藏菜单点击事件
     * */
    private void selectItem(int position) {
        mDrawerList.setItemChecked(position, true);
//        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
 }
