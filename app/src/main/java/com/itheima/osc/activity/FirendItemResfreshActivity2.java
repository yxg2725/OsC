package com.itheima.osc.activity;

import android.util.Log;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.osc.App;
import com.itheima.osc.R;
import com.itheima.osc.Util.XmlUtils;
import com.itheima.osc.adapter.MyAdapter;
import com.itheima.osc.base.BaseResfreshActivity;
import com.itheima.osc.bean.Active;
import com.itheima.osc.bean.Comment;
import com.itheima.osc.bean.CommentList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/18.
 */
public class FirendItemResfreshActivity2 extends BaseResfreshActivity {

    private Active itemInfo;

    @Override
    public int getViewId() {
        return R.layout.friend_item_2;
    }        //布局 layout id

    @Override
    public int findlistVIewId() {
        return R.id.friend_detail_lv;
    }    //  findlistView

    @Override
    public int findRefreshLayoutId() {
        return R.id.swipe;
    }          //  findRefreshLayout

    @Override
    public String getUrl(int index) {                                //获取网址

        itemInfo = (Active) getIntent().getSerializableExtra("itemInfo");
        int id = itemInfo.getId();
        int catalog = itemInfo.getCatalog();
        String url = "http://192.168.81.68:8080/oschina/commentlist/catalog" + catalog + "id" + id + "page" + index + ".xml";
        return url;
    }

    @Override
    public void showListViewData(PullToRefreshListView lv) {  //展示listView数据
    }

    @Override
    protected void otherListener() {
    }                                   //其他监听事件

    @Override
    protected void handleData(Exception e, InputStream result) {            //处理网络返回的而数据
        List<Object> datas = new ArrayList<>();

        CommentList comments = XmlUtils.toBean(CommentList.class, result);
        Log.i("====", "handleData: " + comments);
        if (comments != null) {
            List<Comment> commentlist = comments.getCommentlist();


            datas.add(itemInfo);
            if (commentlist.size() != 0) {
                System.out.println(commentlist);
                datas.addAll(commentlist);
            }

            //展示数据
            MyAdapter adapter = new MyAdapter(datas);
            lv.setAdapter(adapter);

            lv.onRefreshComplete();
            swiperefresh.setRefreshing(false);
        } else {
            Toast.makeText(App.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
        }

    }

}
