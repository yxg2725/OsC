package com.itheima.osc.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import com.itheima.osc.R;
import com.itheima.osc.Util.AdapterSetDataUtil;
import com.itheima.osc.bean.Active;
import com.itheima.osc.widget.TextViewFixTouchConsume;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/16.
 */
public class FriendAdapter extends BaseAdapter{
    private List<Active> activelist;
    private Context context;


    public FriendAdapter(Context context, List<Active> activelist) {
        this.activelist = activelist;
        this.context = context;
    }

    public List<Active> getListData(){
        if (activelist == null){
            activelist = new ArrayList<>();
        }
        return activelist;
    }

    @Override
    public int getCount() {
        return activelist == null?0:activelist.size();
    }

    @Override
    public Object getItem(int position) {
        return activelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.find_friend_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.find_friend_tv_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.find_friend_iv_icon);
            holder.tweetimage = (ImageView) convertView.findViewById(R.id.tweetimage);
            holder.desc = (TextView) convertView.findViewById(R.id.find_friend_tv_desc);
            holder.content = (TextView) convertView.findViewById(R.id.find_friend_tv_content);
            holder.phone = (TextView) convertView.findViewById(R.id.find_friend_tv_phone);
            holder.comment = (TextView) convertView.findViewById(R.id.find_friend_tv_comment);
            holder.time = (TextView) convertView.findViewById(R.id.find_friend_tv_time);
            holder.objectname_body = (TextView) convertView.findViewById(R.id.objectname_body);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //获取数据
        Active activeInfo = activelist.get(position);

        //显示昵称
        String name = activeInfo.getAuthor();
        AdapterSetDataUtil.setName(holder.name, name);

       // 日期
        holder.time.setText(activeInfo.getPubDate());

        //内容
        String content = activeInfo.getMessage();
        AdapterSetDataUtil.setContent(holder.content, content);

        //回复的昵称和内容
        Active.ObjectReply objectReply = activeInfo.getObjectReply();
        AdapterSetDataUtil.setReply(holder.objectname_body, objectReply);

        //评论个数
        int commentCount = activeInfo.getCommentCount();
        AdapterSetDataUtil.setCommentCount(holder.comment, commentCount);

        //手机
        AdapterSetDataUtil.getAndSetPhone(holder.phone, activeInfo.getAppClient());

        //状态信息
        int catalog = activeInfo.getCatalog();
        //获取标题数据
        String title = activeInfo.getObjectTitle();
        AdapterSetDataUtil.setDescAndTitle(holder.desc, catalog, title);

        //获取动弹图片
        String tweetimage = activeInfo.getTweetimage();
        AdapterSetDataUtil.setTweetImagView(holder.tweetimage, tweetimage);

        //展示头像圆形图片
        String iconUrl = activeInfo.getPortrait();//获取图片路径
        AdapterSetDataUtil.setCircleIcon(holder.icon, iconUrl);

        return convertView;
    }

    class ViewHolder{
        public TextView content;
        public TextView name;
        public TextView desc;
        public TextView phone;
        public int img;
        public TextView time;
        public TextView comment;
        public TextView objectname_body;
        public ImageView icon;
        public ImageView tweetimage;
    }
}
