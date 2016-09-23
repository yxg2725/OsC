package com.itheima.osc.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.osc.R;

/**
 * Created by bearabit on 2016/9/17 19:27.
 */
public class TweetFragmentVPAdapter extends BasePagerAdapter {
    private String[] titles = {"最新动弹", "热门动弹", "我的动弹"};

    public TweetFragmentVPAdapter(Context context) {
        super(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = View.inflate(mContext, R.layout.page_fragment_dongtan_viewpager_content, null);
        initPage(page);
        return page;
    }

    private void initPage(View page) {
        SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) page.findViewById(R.id.page_fragment_dongtan_viewpager_content_srl_nested);
        ListView listview = (ListView) page.findViewById(R.id.page_fragment_dongtan_viewpager_content_lv_content);
        // TODO: 2016/9/17 21:27 创建viewpager的内容
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }
}
