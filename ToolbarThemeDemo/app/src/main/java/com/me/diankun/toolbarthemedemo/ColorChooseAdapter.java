package com.me.diankun.toolbarthemedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by diank on 2015/9/16.
 */
public class ColorChooseAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mDatas;
    private LayoutInflater mInflater;

    private int checkedPosition = -1;

    public ColorChooseAdapter(Context mContext, List<Integer> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.colors_image_layout, parent, false);
            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            viewHolder.image2 = (ImageView) convertView.findViewById(R.id.image2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.image1.setImageResource(mDatas.get(position));
        //选中显示 勾号
        if(position == checkedPosition){
            viewHolder.image2.setVisibility(View.VISIBLE);
        }else{
            viewHolder.image2.setVisibility(View.GONE);
        }
        return convertView;
    }


    static class ViewHolder {
        ImageView image1, image2;
    }
}
