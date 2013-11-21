package com.hyrt.locationschool;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.hyrt.base.BaseActivity;
import com.hyrt.locationschool.activitiesSnapshots.SchoolActivitiesSnapshots;
import com.hyrt.locationschool.announcement.SchoolAnnouncement;
import com.hyrt.locationschool.classSetup.SchoolClassSetup;
import com.hyrt.locationschool.highlights.SchoolHighlights;
import com.hyrt.locationschool.messageboards.SchoolMessageboards;
import com.hyrt.locationschool.panorama.SchoolPanorama;
import com.hyrt.locationschool.parkdescribed.SchoolParkdescribed;
import com.hyrt.locationschool.principalMessage.SchoolPrincipalMessage;
import com.hyrt.locationschool.starTeachers.SchoolStarTeacher;
import com.hyrt.locationschool.starbaby.SchoolStarbaby;
import com.hyrt.locationschool.weekRecipe.SchoolWeekRecipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GYH on 13-11-19.
 */
public class SchoolInformation extends BaseActivity{

    private String[] mPlanetTitles;//存储左侧隐藏菜单列表数据
    private DrawerLayout mDrawerLayout;//整体布局
    private ListView mDrawerList;//左侧隐藏菜单列表

    private ActionBarDrawerToggle mDrawerToggle;//actionbar控制左侧隐藏菜单

    private CharSequence mDrawerTitle;//应用标题
    private CharSequence mTitle;//标题

    private RoboSherlockFragment fragment;//显示内容碎片

    public FragmentTransaction fragmentTransaction;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinformation);
        initData();//初始化数据
        initView();//初始化布局视图

        //初始化左侧隐藏菜单的选中条目
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    /**
     * 初始化布局视图
     * */

    private void initView(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.school_drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.school_left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        //初始数据
        for(int i=0;i<mPlanetTitles.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemTitle", mPlanetTitles[i]);
            listItem.add(map);
        }

        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.drawer_list_item,//ListItem的XML实现
                new String[] {"itemTitle"},
                new int[] {R.id.text1}
        );

        mDrawerList.setAdapter(listItemAdapter);//listview添加adapter

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
//        fragment=new SchoolAnnouncement(fragmentManager);
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
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
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

        switch (position){
            case 0:
                if(fragment instanceof  SchoolAnnouncement){

                }else{
                    fragment = new SchoolAnnouncement(fragmentManager);
                }
                break;
            case 1:
                if(fragment instanceof SchoolPrincipalMessage){

                }else{
                    fragment = new SchoolPrincipalMessage();
                }
                break;
            case 2:
                if(fragment instanceof SchoolActivitiesSnapshots){

                }else{
                    fragment = new SchoolActivitiesSnapshots();
                }
                break;
            case 3:
                if(fragment instanceof SchoolStarTeacher){

                }else{
                    fragment = new SchoolStarTeacher(fragmentManager);
                }
                break;
            case 4:
                if(fragment instanceof SchoolHighlights){

                }else{
                    fragment = new SchoolHighlights();
                }
                break;
            case 5:
                if(fragment instanceof SchoolStarbaby){

                }else{
                    fragment = new SchoolStarbaby();
                }
                break;
            case 6:
                if(fragment instanceof SchoolClassSetup){

                }else{
                    fragment = new SchoolClassSetup();
                }
                break;
            case 7:
                if(fragment instanceof SchoolWeekRecipe){

                }else{
                    fragment = new SchoolWeekRecipe(fragmentManager);
                }
                break;
            case 8:
                if(fragment instanceof SchoolPanorama){

                }else{
                    fragment = new SchoolPanorama();
                }
                break;
            case 9:
                if(fragment instanceof SchoolParkdescribed){

                }else{
                    fragment = new SchoolParkdescribed();
                }
                break;
            case 10:
                if(fragment instanceof SchoolMessageboards){

                }else{
                    fragment = new SchoolMessageboards();
                }
                break;

        }
        fragmentTransaction = fragmentManager.beginTransaction();//时候transaction来管理集合
        fragmentTransaction.replace(R.id.school_container,fragment);
        fragmentTransaction.commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    /**
     * 初始化数据
     * */
    private void initData(){
        mTitle = mDrawerTitle = getTitle();//初始化acationbar标题
        mPlanetTitles=getResources().getStringArray(R.array.schoolinfor_array);//获取左侧隐藏菜单列表数据
        fragmentManager = getSupportFragmentManager();//实例化fragment管理
        fragmentTransaction = fragmentManager.beginTransaction();//时候transaction来管理集合
        fragment=new SchoolAnnouncement(fragmentManager);
        fragmentTransaction.add(R.id.school_container,fragment);//向布局中添加fragment
//        fragmentTransaction.addToBackStack(null);//设置backfragment弹出
        fragmentTransaction.commit();//提交到ui线程中去
    }

}
