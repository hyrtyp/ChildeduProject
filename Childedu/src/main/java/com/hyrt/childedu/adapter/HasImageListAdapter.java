package com.hyrt.childedu.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.hyrt.childedu.view.HasImageItemView;
import com.hyrt.datahelper.dome.model.HasImageModel;
import com.hyrt.datahelper.dome.model.ListHasImageModel;
import com.octo.android.robospice.request.simple.BitmapRequest;
import com.octo.android.robospice.spicelist.BitmapSpiceManager;
import com.octo.android.robospice.spicelist.SpiceArrayAdapter;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.io.File;
import java.util.logging.Handler;

/**
 * Created by GYH on 13-10-28.
 */
public class HasImageListAdapter extends SpiceArrayAdapter<HasImageModel> {

    public HasImageListAdapter(Context context, BitmapSpiceManager spiceManagerBitmap, ListHasImageModel users) {
        super(context, spiceManagerBitmap, users.getUsers());
    }



    @Override
    public BitmapRequest createRequest(HasImageModel schoolCaseModel, int imageIndex, int requestImageWidth, int requestImageHeight) {
        File tempFile = new File(getContext().getCacheDir(), "THUMB_IMAGE_TEMP_" + schoolCaseModel.getName());
        return new BitmapRequest("https://secure.gravatar.com/avatar/" + schoolCaseModel.getGravatar_id(), requestImageWidth,
                requestImageHeight, tempFile);
    }


    @Override
    public SpiceListItemView<HasImageModel> createView(Context context, ViewGroup viewGroup) {

        return new HasImageItemView(getContext());
    }
}