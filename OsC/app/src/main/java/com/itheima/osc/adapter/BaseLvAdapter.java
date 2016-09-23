package com.itheima.osc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by bearabit on 2016/9/17 20:41.
 */
public abstract class BaseLvAdapter extends BaseAdapter {
    protected ArrayList<Object> mData;
    protected Context mContext;

    public BaseLvAdapter(Context context, ArrayList<Object> list) {
        super();
        mContext = context;
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, getLayoutResId(), null);
            holder = initHolder(position, convertView);
            convertView.setTag(holder);
        } else {
            holder = convertView.getTag();
        }
        updateHolderData(holder, position);
        return convertView;
    }

    /**
     * 为convertView提供布局文件的ResId
     *
     * @return 布局文件的ResId
     */
    protected abstract int getLayoutResId();

    /**
     * convertView为空时，需要为当前convertView创建holder
     *
     * @param position    当前条目的位置
     * @param convertView 当前条目对应的convertView
     * @return 初始化好的holder
     */
    protected abstract Object initHolder(int position, View convertView);

    /**
     * 更新ViewHolder中各控件的数据
     *
     * @param holder   当前条目的holder
     * @param position 当前条目的位置
     */
    protected abstract void updateHolderData(Object holder, int position);
}
