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
public class DrawerLayoutAdapter  extends BaseAdapter{

    //创建listview的固定数据
    String[] mTextList = {"技术问答","开源软件","博客区","Git客户端"};
    int[] mDrawableList = {R.drawable.selector_drawerlayout_quest,R.drawable.selector_drawerlayout_opensoft,R.drawable.selector_drawerlayout_blog,R.drawable.selector_drawerlayout_gitapp};

    @Override
    public int getCount() {
        return mTextList.length;
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
        View view = View.inflate(parent.getContext(),R.layout.layou_drawerlayout_lv_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.drawerlayout_item_iv);
        TextView textView = (TextView) view.findViewById(R.id.drawerlayout_item_tv);
        imageView.setBackgroundResource(mDrawableList[position]);
        textView.setText(mTextList[position]);
        return view;
    }
}
