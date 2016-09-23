package com.itheima.osc.fragment;


import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.osc.App;
import com.itheima.osc.R;
import com.itheima.osc.Util.XmlUtils;
import com.itheima.osc.adapter.FindRecentEventAdapter;
import com.itheima.osc.base.BaseFragment;
import com.itheima.osc.bean.Event;
import com.itheima.osc.bean.EventList;

import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2016/9/17.
 */
public class MyEventFragment extends BaseFragment {

   private FindRecentEventAdapter adapter;

    @Override
    public CharSequence getTitle() {
        return "我的活动";
    }

    @Override
    public Object getContentView() {
        return R.layout.find_event_recent_event;
    }

    @Override
    protected void showListViewData(PullToRefreshListView lv) {

        adapter = new FindRecentEventAdapter(null);
        lv.setAdapter(adapter);
    }

    @Override
    protected int findRefreshLayoutId() {
        return R.id.event_swipe_refresh;
    }

    @Override
    protected int findlistVIewId() {
        return R.id.find_event_lv;
    }

    @Override
    protected void otherListener() {}

    @Override
    protected String getUrl(int index) {
        String url = "http://192.168.81.68:8080/oschina/list/my_event_list/page"+index+".xml";
        return url;
    }

    @Override
    protected void handleData(Exception e, InputStream result) {

        EventList eventList = XmlUtils.toBean(EventList.class, result);

        if (eventList != null) {
            List<Event> rectentEvents = eventList.getFriendlist();
            //获取数据完毕 关闭刷新显示
            if (currentMode == PULL_FROME_END) {
                lv.onRefreshComplete();

                if (checkDatas(rectentEvents)) {
                    //显示数据
                    adapter.getDatas().addAll(rectentEvents);
                    adapter.notifyDataSetChanged();

                }

            }else {
                //关闭下拉刷新
                swiperefresh.setRefreshing(false);
                System.out.println(true+"false");
                if (checkDatas(rectentEvents)) {
                    adapter.getDatas().clear();
                    adapter.getDatas().addAll(rectentEvents);
                    adapter.notifyDataSetChanged();
                }
            }
        }else{

            lv.onRefreshComplete();//关闭刷新底部条目
            Toast.makeText(App.getContext(),"没有更多数据了",Toast.LENGTH_SHORT).show();


        }

    }
}
