package com.itheima.osc.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.osc.App;
import com.itheima.osc.R;
import com.itheima.osc.Util.AdapterSetDataUtil;
import com.itheima.osc.Util.NetUtil;
import com.itheima.osc.Util.XmlUtils;
import com.itheima.osc.bean.Active;
import com.itheima.osc.bean.Comment;
import com.itheima.osc.bean.TweetDetail;
import com.koushikdutta.async.future.FutureCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/19.
 */
public class MyAdapter extends BaseAdapter {
    private static final int STYLE_HEAD = 0;
    private static final int STYLE_OTHER = 1;
    private List<Object> datas;
    private TextView other_people;


    public MyAdapter(List<Object> datas) {
        this.datas = datas;
    }

    public List<Object> getListData(){
        if (datas == null){
            datas = new ArrayList<>();
        }
        return datas;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? STYLE_HEAD : STYLE_OTHER;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {


        if (position == 0) {
            Active active = (Active) datas.get(0);
            //  ViewHolder holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.friend_firs_item, null);
            final ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView time = (TextView) convertView.findViewById(R.id.time);
            TextView phone = (TextView) convertView.findViewById(R.id.phone);
            TextView content = (TextView) convertView.findViewById(R.id.content);
            ImageButton commentCount = (ImageButton) convertView.findViewById(R.id.commentCount);
            other_people = (TextView) convertView.findViewById(R.id.other_people);

            //展示数据
            name.setText(active.getAuthor());//昵称

            time.setText(active.getPubDate());//日期

            AdapterSetDataUtil.getAndSetPhone(phone,active.getAppClient());       //手机

            AdapterSetDataUtil.setContent(content,active.getMessage());          //内容

            AdapterSetDataUtil.setCircleIcon(icon,active.getPortrait());        // 头像

            //点赞链接
            // http://192.168.81.68:8080/oschina/detail/tweet_detail+id.xml
            String urlLike = "http://192.168.81.68:8080/oschina/detail/tweet_detail/" + active.getId() + ".xml";
            NetUtil.getNetData(urlLike,callback);

        } else {
            Comment comment = (Comment) datas.get(position);

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.friend_detail_item, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.time = (TextView) convertView.findViewById(R.id.date);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                holder.phone = (TextView) convertView.findViewById(R.id.phone);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //展示数据

            holder.name.setText(comment.getAuthor());//昵称

            holder.time.setText(comment.getPubDate());//日期

            AdapterSetDataUtil.setContent(holder.content,comment.getContent());     //内容

            AdapterSetDataUtil.getAndSetPhone(holder.phone,comment.getAppClient());   //手机

            AdapterSetDataUtil.setCircleIcon(holder.icon,comment.getPortrait());    //头像
        }

        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
        TextView time;
        TextView content;
        TextView phone;

    }

    //点赞 访问的回调函数
    FutureCallback<InputStream> callback = new FutureCallback<InputStream>() {
        @Override
        public void onCompleted(Exception e, InputStream result) {

            TweetDetail tweet = XmlUtils.toBean(TweetDetail.class, result);
            if(tweet!= null){
                tweet.getTweet().setLikeUsers(App.getContext(),other_people,false);
            }

        }
    };

}


