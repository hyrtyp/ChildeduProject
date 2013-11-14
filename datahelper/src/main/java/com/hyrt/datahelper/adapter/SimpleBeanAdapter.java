package com.hyrt.datahelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by GYH on 13-10-30.
 */
public class SimpleBeanAdapter extends BaseAdapter{

    protected Context context;
    protected List data;
    protected int layoutId;
    protected String reskeys[];
    protected int reses[];
    protected LayoutInflater layoutInflater;

    public SimpleBeanAdapter(Context context,List data,int layoutId, String reskeys[],int reses[]){
        this.context=context;
        this.data = data;
        this.layoutId=layoutId;
        this.reses=reses;
        this.reskeys=reskeys;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null){
            view = layoutInflater.inflate(layoutId,null);
        }
        if(view!=null&&getItem(position)!=null){
            for(int i=0;i<reses.length;i++){
                View view1 = (View)view.findViewById(reses[i]);
                try {
                    Method method = data.get(position).getClass().getMethod(reskeys[i],(Class[])null);
                    Object object = method.invoke(data.get(position),(Object)null);
                    if(object!=null){
                        if(view1 instanceof TextView){
                            ((TextView) view1).setText(object.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return view
                ;
    }
}
