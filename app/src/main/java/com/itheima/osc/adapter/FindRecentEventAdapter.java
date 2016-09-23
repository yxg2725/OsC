package com.itheima.osc.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.osc.R;
import com.itheima.osc.Util.AdapterSetDataUtil;
import com.itheima.osc.bean.Event;

import java.util.List;

/**
 * Created by admin on 2016/9/17.
 */
public class FindRecentEventAdapter extends MyBaseAdapter<Event>{

    public FindRecentEventAdapter(List<Event> datas) {
        super(datas);
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.find_event_recent_event_item;
    }

    @Override
    public Object createViewHolderAndFindViewById(int position, View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.tv_title = (TextView) convertView.findViewById(R.id.find_recent_event_tv_title);
        holder.tv_time = (TextView) convertView.findViewById(R.id.find_recent_event_tv_time);
        holder.tv_content = (TextView) convertView.findViewById(R.id.find_recent_event_tv_content);
        holder.iv_icon = (ImageView) convertView.findViewById(R.id.find_recent_event_iv_icon);
        return holder;
    }

    @Override
    public void showData(int position, Object holder, Event data) {

        //展示数据
        ViewHolder myholder = (ViewHolder) holder;

        myholder.tv_title.setText(data.getTitle());
        myholder.tv_time.setText(data.getStartTime());
        myholder.tv_content.setText(data.getSpot());

        //获取图片路径
        AdapterSetDataUtil.setTweetImagView(myholder.iv_icon,data.getCover());
       /* String imagViewUrl = data.getCover();
        Glide.with(App.getContext())
                .load(imagViewUrl)
                .centerCrop()
                .into(myholder.iv_icon);*/
    }


    class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;
        TextView tv_content;

    }
}
