package com.hyrt.childedu.view;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.hyrt.childedu.BaseActivity;
import com.hyrt.childedu.R;
import com.hyrt.childedu.adapter.HasImageListAdapter;
import com.hyrt.childedu.view.xlistview.XListView;
import com.hyrt.childeduteacher.SampleList;
import com.hyrt.datahelper.FlickrImageRequestFactory;
import com.hyrt.datahelper.FlickrPhotoAdapter;
import com.hyrt.datahelper.dome.model.HasImageModel;
import com.hyrt.datahelper.dome.model.ListHasImageModel;
import com.hyrt.datahelper.dome.request.HasImageRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import roboguice.inject.InjectView;

public class XlistviewActivity extends BaseActivity {

    @InjectView(R.id.xlistview) public XListView xListView;
    @InjectView(R.id.image_grid) public GridView gridView;
    @InjectView(R.id.progressbar) public ProgressBar progressBar;
    public String requestcachekeyword;
    public HasImageListAdapter hasImageListAdapter=null;
    public String chosedisposed[] = new String[] { "编辑", "删除" };
    public final static int DIALOG = 1;
    public final static int DIALOG2 = 2;
    public final static int DIALOG3 = 3;

    private static final int PHOTO_WIDTH = 150;
    private static final int MIN_COLUMNS = 2;
    private static final int MAX_COLUMNS = 4;

    private FlickrPhotoAdapter photoAdapter;

    public Handler handler = new Handler(){

        public void handleMessage(Message msg) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlistview);
        initLayoutView();
        bindViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;
        return true;
    }

    private void bindViews() {

//        GridView imageGrid = (GridView) findViewById(R.id.image_grid);

        int gridSize = sizeColumnsToFit(gridView, PHOTO_WIDTH, MIN_COLUMNS, MAX_COLUMNS);

        FlickrImageRequestFactory imageRequestFactory = new FlickrImageRequestFactory(this);
        imageRequestFactory.setPhotoFormat(FlickrImageRequestFactory.LARGE_THUMB_SQUARE).setSampleSize(gridSize, gridSize);

        photoAdapter = new FlickrPhotoAdapter(this,
                R.layout.grid_view_img_item,
                R.id.image,R.id.textview ,
                new ArrayList<HasImageModel>(),imageRequestFactory);
        gridView.setAdapter(photoAdapter);
        gridView.setOnScrollListener(photoAdapter);
//        imageGrid.setOnItemClickListener(new ItemClickListener());
    }

    @TargetApi(13)
    @SuppressWarnings("deprecation")
    private int sizeColumnsToFit(GridView grid, int minColumnWidth, int minColumns, int maxColumns) {

        Display display = getWindowManager().getDefaultDisplay();

        int screenWidth;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            screenWidth = display.getWidth();
        } else {
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        int numColumns = screenWidth / minColumnWidth;
        numColumns = Math.min(numColumns, maxColumns);
        numColumns = Math.max(numColumns, minColumns);
        int remainingSpace = screenWidth - numColumns * minColumnWidth;
        int columnWidth = minColumnWidth + remainingSpace / numColumns;

        grid.setNumColumns(numColumns);
        grid.setColumnWidth(columnWidth);

        return columnWidth;
    }


    public void initLayoutView(){
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                xListView.stopRefresh();
                Toast.makeText(XlistviewActivity.this, "onrefresh", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                xListView.stopLoadMore();
                Toast.makeText(XlistviewActivity.this, "onloadmore", Toast.LENGTH_SHORT).show();
            }
        });
        xListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(DIALOG);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Loadlistdata();
    }

    public void Loadlistdata(){
        HasImageRequest schoolCaseRequest = new HasImageRequest("android");
        requestcachekeyword=schoolCaseRequest.createCacheKey();
        getSpiceManager().execute(schoolCaseRequest,requestcachekeyword, DurationInMillis.ONE_SECOND * 10,new GitHubUserListListener());
    }

    private class GitHubUserListListener implements RequestListener<ListHasImageModel> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            setProgressBarIndeterminateVisibility(false);
            Toast.makeText(XlistviewActivity.this, "Impossible to get the list of users", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(ListHasImageModel result) {
            Toast.makeText(XlistviewActivity.this,result.getUsers().get(1).getName(),Toast.LENGTH_SHORT).show();
            hasImageListAdapter=new HasImageListAdapter(XlistviewActivity.this,getSpiceManagerBinary(),result);
            xListView.setAdapter(hasImageListAdapter);

            for(int i=0;i<result.getUsers().size();i++){
                photoAdapter.add(result.getUsers().get(i));
            }
            progressBar.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
//            xListView.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 创建列表对话框
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog=null;
        switch (id) {
            case DIALOG:
                AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
                //设置对话框的图标
                builder.setIcon(R.drawable.ic_launcher);
                //设置对话框的标题
                builder.setTitle("列表对话框");
                //添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
                builder.setItems(chosedisposed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0,int arg1) {
                        // TODO Auto-generated method stub
                        switch(arg1){
                            case 0:
                                break;
                            case 1:
                                showDialog(DIALOG2);
                                break;
                        }
                    }
                });
                //创建一个列表对话框
                dialog=builder.create();
                break;
            case DIALOG2:
                dialog=new AlertDialog.Builder(XlistviewActivity.this)
                        .setIcon(R.drawable.ic_launcher)
                        .setTitle("温馨提示")
                        .setMessage(
                                "是否删除")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface arg0, int arg1) {
                                    }

                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        // TODO Auto-generated method stub
                                    }
                                }).show();
                break;
        }
        return dialog;
    }
}
