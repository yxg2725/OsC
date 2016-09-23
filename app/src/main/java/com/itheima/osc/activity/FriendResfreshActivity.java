package com.itheima.osc.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.osc.App;
import com.itheima.osc.R;
import com.itheima.osc.Util.XmlUtils;
import com.itheima.osc.adapter.FriendAdapter;
import com.itheima.osc.base.BaseResfreshActivity;
import com.itheima.osc.bean.Active;
import com.itheima.osc.bean.ActiveList;

import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2016/9/16.
 */
public class FriendResfreshActivity extends BaseResfreshActivity {

    private FriendAdapter adapter;

    @Override
    public int getViewId() {                //布局 layout id
        return R.layout.friend_activity;
    }

    @Override
    public int findlistVIewId() {           //  findlistView
        return R.id.find_friend_lv;
    }

    @Override
    public int findRefreshLayoutId() {      //  findRefreshLayout
        return R.id.swiperefresh;
    }

    @Override
    public void showListViewData(PullToRefreshListView lv) {  //ListView展示数据
        adapter = new FriendAdapter(context, null);
        lv.setAdapter(adapter);
    }

    @Override
    public String getUrl(int index) {                         //获取网址
        String url = "http://192.168.81.68:8080/oschina/list/active_list1/page" + index + ".xml";
        return url;
    }

    @Override
    protected void otherListener() {            //监听事件
        //item点击监听事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转界面  如果有标题  则跳转到的界面中是个webview  否则是个布局
                //获取这个item数据
                Active item = (Active) parent.getItemAtPosition(position);
                int catalog = item.getCatalog();

                //博客  代码 回答了额问题
                //跟新了动态  发表动态  回复了动态
                if (catalog == 0 || catalog == 2 || catalog == 4) {//跳转到webview布局
                    System.out.println(true + "========");
                    //获取webUrl
                    Intent intent = new Intent(context, FirendItemActivity.class);
                    //http://192.168.81.68:8080/oschina/detail/blog_detail/id.xml

                    String webUrl = "http://192.168.81.68:8080/oschina/detail/blog_detail/" + item.getObjectId() + ".xml";
                    intent.putExtra("webUrl", webUrl);
                    context.startActivity(intent);
                } else {//跳转到自定义的布局的界面
                    Intent intent = new Intent(context, FirendItemResfreshActivity2.class);
                    intent.putExtra("itemInfo", item);
                    context.startActivity(intent);

                }
            }
        });

        //长按监听
        lv.getRefreshableView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(App.getContext(),"复制文本",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    protected void handleData(Exception e, InputStream result) {        //处理网络返回的数据

        ActiveList active = XmlUtils.toBean(ActiveList.class, result);
        //获取页数
        //  pageSize =   active.getPageSize();

        if (active != null) {
            List<Active> activelist = active.getActivelist();
            if (!activelist.isEmpty()) {
                //获取数据完毕 关闭刷新显示
                if (currentMode == PULL_FROME_END) {
                    lv.onRefreshComplete();
                    //显示数据
                    adapter.getListData().addAll(activelist);
                    adapter.notifyDataSetChanged();
                    //   lv_friend.getRefreshableView().setSelection(activelist.size()-1);

                } else {
                    //关闭下拉刷新
                    swiperefresh.setRefreshing(false);
                    adapter.getListData().clear();
                    adapter.getListData().addAll(activelist);
                    adapter.notifyDataSetChanged();
                }
            }
        } else {
            Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show();
            lv.onRefreshComplete();
        }

    }

}
