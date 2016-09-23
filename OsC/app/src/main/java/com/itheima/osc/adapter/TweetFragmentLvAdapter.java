package com.itheima.osc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.itheima.osc.R;
import com.meg7.widget.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bearabit on 2016/9/17 20:40.
 */
public class TweetFragmentLvAdapter extends BaseLvAdapter {
    public TweetFragmentLvAdapter(Context context, ArrayList<Object> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_dongtan_listview;
    }

    @Override
    protected Object initHolder(int position, View convertView) {
        ViewHolder holder = new ViewHolder(convertView);
        return holder;
    }

    @Override
    protected void updateHolderData(Object holder, int position) {
        ViewHolder viewholder = (ViewHolder) holder;

        // TODO: 2016/9/17 21:33 加载holder的数据。在这之前先定义好数据格式
    }

    class ViewHolder {
        @BindView(R.id.item_dongtan_listview_iv_icon)
        CircleImageView mIvIcon;

        @BindView(R.id.item_dongtan_listview_tv_username)
        TextView mTvUsername;

        @BindView(R.id.item_dongtan_listview_tv_content)
        TextView mTvContent;

        @BindView(R.id.item_dongtan_listview_gl_images)
        GridLayout mGlImages;

        @BindView(R.id.item_dongtan_listview_tv_commit_time)
        TextView mTvCommitTime;

        @BindView(R.id.item_dongtan_listview_tv_commit_phone_type)
        TextView mTvCommitPhoneType;

        @BindView(R.id.item_dongtan_listview_tv_praise)
        TextView mTvPraise;

        @BindView(R.id.item_dongtan_listview_tv_comment)
        TextView mTvComment;

        @BindView(R.id.item_dongtan_listview_tv_praise_names)
        TextView TvPraiseNames;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
