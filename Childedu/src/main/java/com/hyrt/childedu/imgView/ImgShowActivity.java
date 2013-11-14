package com.hyrt.childedu.imgView;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.actionbarsherlock.view.Menu;
import com.hyrt.childedu.BaseActivity;
import com.hyrt.childedu.R;
import com.hyrt.childeduteacher.SampleList;
import com.hyrt.datahelper.loadBitmap.ImageLoader;
import java.util.ArrayList;
import java.util.List;
public class ImgShowActivity extends BaseActivity {


    private ViewPager viewPager;
    private List<ImageView> imageViews; // 滑动的图片集合
    private String[] titles;// 图片标题
    private int[] imageResId;// 图片ID
    private List<View> dots; // 图片标题正文的那些点集合
    private TextView tv_title;
    private int currentItem = 0;// 当前图片的索引号
    public ImageLoader imageLoader;
    public ZoomControls zoomControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgshow);
        initLayoutView();
        initImageFlow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;
        return true;
    }

    public void initLayoutView(){
        imageLoader=new ImageLoader(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 初始化图片滚动
     */
    private void initImageFlow() {
        // 放置图片资源
        imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
                R.drawable.a, R.drawable.b };
        titles = new String[imageResId.length];

        titles[0] = "第一个";
        titles[1] = "第二个";
        titles[2] = "第三个";
        titles[3] = "第四个";
        titles[4] = "第五个";

        imageViews = new ArrayList<ImageView>();
        dots = new ArrayList<View>();
        // 初始化图片资源
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(imageResId[i]);
//            imageView.setScaleType(ScaleType.CENTER_CROP);
//            setimageview(imageView);
            imageLoader.DisplayImage("http://www.zhiyeabc.com/images/201304/goods_img/55_G_1367002312402.jpg", imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageViews.add(imageView);

        }
        dots.add(findViewById(R.id.v_dot0));
        dots.add(findViewById(R.id.v_dot1));
        dots.add(findViewById(R.id.v_dot2));
        dots.add(findViewById(R.id.v_dot3));
        dots.add(findViewById(R.id.v_dot4));

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(titles[0]);

        // 填充viewPager页面的适配器
        viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(new MyAdapter());

        // 设置监听器，当ViewPager中的页面改变时调用
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    /**
     * 初始化图片
     * */
    private void setimageview(ImageView imageView) {

//        imageLoader.DisplayImage(productinfo.getImage1(), imageView);
        // Bitmap bitmap = asyncLoader.loadBitmap(imageView,
        // productinfo.getImage1(), new ImageCallBack() {
        // @Override
        // public void imageLoad(ImageView img_detail, Bitmap bitmap) {
        // // TODO Auto-generated method stub
        // img_detail.setImageBitmap(bitmap);
        // }
        // });
        // if (bitmap == null) {
        // imageView.setImageResource(R.drawable.ic_launcher); // 加载默认图片
        // } else {
        // imageView.setImageBitmap(bitmap);
        // }
    }
    /***
     * 当viewPager页面中的状态发生改变时调用
     *
     * @author user36
     *
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_foucsed);
            oldPosition = position;
        }

    }

    /**
     * 填充ViewPager页面的适配器
     *
     * @author user36
     *
     */
    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }
}
