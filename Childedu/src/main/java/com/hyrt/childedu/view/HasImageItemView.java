package com.hyrt.childedu.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyrt.childedu.R;
import com.hyrt.datahelper.dome.model.HasImageModel;
import com.octo.android.robospice.spicelist.SpiceListItemView;

/**
 * Created by GYH on 13-10-28.
 */
public class HasImageItemView extends RelativeLayout implements SpiceListItemView<HasImageModel> {

    private TextView textView;
    private TextView textView1;
    private ImageView imageView;
    private HasImageModel CaseModel;

    public HasImageItemView(Context context){
        super(context);
        inflateView(context);
    }

    private void inflateView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_xlistview_item, this);
//        this.textView = (TextView) this.findViewById(R.id.textView);
//        this.textView1 = (TextView) this.findViewById(R.id.textview2);
        this.imageView = (ImageView) this.findViewById(R.id.item_img);
    }

    @Override
    public HasImageModel getData() {
        return CaseModel;
    }

    @Override
    public ImageView getImageView(int i) {
        return imageView;
    }

    @Override
    public int getImageViewCount() {
        return 1;
    }

    @Override
    public void update(HasImageModel schoolCaseModel) {
        this.CaseModel=schoolCaseModel;
//        textView.setText(CaseModel.getName());
//        textView1.setText(String.valueOf(CaseModel.getScore()));
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "imageview-"+CaseModel.getGravatar_id(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
