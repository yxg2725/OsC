package com.itheima.osc.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.osc.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/17.
 */
public class MeAdapter extends BaseAdapter {

    //显示数组，固定的额
    String[] mText = {"消息","博客","便签","团队"};
    int[] mDrawable = {R.drawable.icon_my_blog,R.drawable.icon_my_message,R.drawable.icon_my_note,R.drawable.icon_my_team};


    @Override
    public int getCount() {
        return mText.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(),R.layout.layou_me_lv_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.me_lv_item_iv);
        TextView textView = (TextView) view.findViewById(R.id.me_lv_item_tv);
        imageView.setBackgroundResource(mDrawable[position]);
        textView.setText(mText[position]);
        return view;
    }
}
