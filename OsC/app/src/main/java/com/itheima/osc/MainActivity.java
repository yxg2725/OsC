package com.itheima.osc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.osc.adapter.DrawerLayoutAdapter;
import com.itheima.osc.fragment.MeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mContentLayout;
    private RadioGroup mRadioGroup;
    private ImageButton mImageButton;
    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView mListView;
    private TextView mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
    }




    private void initView() {
        //中间布局
        mContentLayout = (LinearLayout) findViewById(R.id.main_ll);
        //下面的radiogroup
        mRadioGroup = (RadioGroup) findViewById(R.id.main_rg);
        //中间的imagebutton
        mImageButton = (ImageButton) findViewById(R.id.main_quickOption);
        //侧开控件
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        //drawerlayout的listview
        mListView = (ListView) findViewById(R.id.main_start_lv);
        //退出按钮
        mExit = (TextView) findViewById(R.id.drawerlayout_exit);
        //给listview设置数据
        initListView();
        //创建一个fragement管理器
        mFm = getSupportFragmentManager();
        mFt = mFm.beginTransaction();
        initActionBar();


    }
    //
    private void initListener() {
        mExit.setOnClickListener(this);
    }

    private void initListView() {
        //设置适配器
        DrawerLayoutAdapter mAdapter = new DrawerLayoutAdapter();
        mListView.setAdapter(mAdapter);
    }

    private void initActionBar() {
        //获取当前的actionbar
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //初始化toggle
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, 0, 0);
        mToggle.syncState();
        mDrawerLayout.setDrawerListener(mToggle);
    }

    //初始化菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);





        return true;
    }

    //当菜单被点击的时候
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        if(item.getItemId() == R.id.actionbar_search){
            //实现搜索按钮点击事件的逻辑
            Toast.makeText(this,"查询",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        //给中间的imageview
        //创建几个假数据
        final ArrayList<TextView> mFragments = new ArrayList<>();

        //获取radiogroup的个数
        //真实的 contentfragment
        final ArrayList<Fragment> mContentFragments = new ArrayList<>();
        mContentFragments.add(new MeFragment());
        for(int i=0 ; i < mRadioGroup.getChildCount() ; i++){
            TextView tv = new TextView(this);
            tv.setText("我是界面"+i);
            mFragments.add(tv);
        }
        //默认最开始显示第0个
        mRadioGroup.check(R.id.news_rb);
        mContentLayout.addView(mFragments.get(0));
        //给radiogroup设置监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mContentLayout.removeAllViews();
                //根据id来更新数据
                //使用switch进行判断
                switch (checkedId){
                    case R.id.news_rb:
                       //用第一个页面代替
                        //增加的时候先移除
                        mContentLayout.addView(mFragments.get(0));
                        break;
                    case R.id.tweet_rb:
                        mContentLayout.addView(mFragments.get(1));
                        break;
                    case R.id.explore_rb:
                        mContentLayout.addView(mFragments.get(3));
                        break;
                    case R.id.me_rb:
                        mFt.replace(R.id.main_ll,mContentFragments.get(0));

                        mFt.commit();
                        break;
                }
                    //其他的用视图替换
                    /*FragmentTransaction fragmentTransaction = mFm.beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout,mFragments.get(i));
                    fragmentTransaction.commit();*/


                }

        });
        //单独给中间的imageview设置点击事件 ，点击时弹出dialog
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示一个对话框
            }
        });

        //长按监听，改变IP、地址
        mImageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //退出按钮
            case R.id.drawerlayout_exit:
                exit();
                break;
        }
    }

    //退出应用程序
    private void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
