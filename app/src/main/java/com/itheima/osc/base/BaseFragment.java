package com.itheima.osc.base;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.osc.R;
import com.itheima.osc.Util.XmlUtils;
import com.itheima.osc.bean.Event;
import com.itheima.osc.bean.EventList;
import com.itheima.osc.view.StateLayout;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * 基类的功能：专门给别人继承的
 * @author dzl
 *
 */
public abstract class BaseFragment extends Fragment  {

	public PullToRefreshListView  lv;
	public SwipeRefreshLayout  swiperefresh;


	public static final int PULL_FROME_START = 0;
	public static final int PULL_FROME_END = 1;
	public int currentMode = -1;
	public   int index =0;
	
	protected Context context;
	/** 状态布局，封装了4种状态，分别是：正在加载、加载失败、加载为空、正常界面 */
	protected StateLayout stateLayout; 

	/** 返回Fragment的功能标题叫什么 */
	public abstract CharSequence getTitle();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getActivity();
		
		// 填充出来的状态布局已经拥有了3种状态：正在加载、加载失败、加载为空
		stateLayout = (StateLayout) inflater.inflate(R.layout.state_layout, null);
		
		// 因为这里是Base类，Base类不知道子类要设置的正常界面是什么，所以这里把这个正常界面写了抽象方法
		stateLayout.setContentView(getContentView());

		return stateLayout;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initView();
		initListener();
		initData();	// 为了预防7个界面的加载网络的方法在界面第一次选择的时候再执行，这个方法在ViewPager的监听器中去调用

	}

	/** 查找View，增加这个方法是为了省略强转操作 */
	public <T> T findView(int id) {
		@SuppressWarnings("unchecked")
		T view = (T) stateLayout.findViewById(id);
		return view;
	}
	
	/**
	 * 检查数据是否OK，如果数据OK则返回true。这个方法内部会根据数据决定要显示哪种状态的View（failView、emptyView、contentView)
	 * @param datas
	 * @return
	 */
	public boolean checkDatas(Collection<?> datas) {
		boolean result = false;
		if (datas == null) {
			stateLayout.showFailView();
		} else if (datas.isEmpty()) {
			stateLayout.showEmptyView();
		} else {
			stateLayout.showContentView();	// 显示“正常界面”的View 
			result = true;
		}
		return result;
	}
	
	/**
	 * 检查数据是否OK，如果数据OK则返回true。这个方法内部会根据数据决定要显示哪种状态的View（failView、emptyView、contentView)
	 * @param datas
	 * @return
	 */
	public boolean checkDatas(Map<?, ?> datas) {
		boolean result = false;
		if (datas == null) {
			stateLayout.showFailView();
		} else if (datas.isEmpty()) {
			stateLayout.showEmptyView();
		} else {
			stateLayout.showContentView();	// 显示“正常界面”的View 
			result = true;
		}
		return result;
	}

	/** 初始化View的代码写在这个方法中 */
	public  void initView(){
		//找出listivew 和refreshlayout
		int ListViewId = findlistVIewId();
		lv = (PullToRefreshListView)stateLayout.findViewById(ListViewId);
		swiperefresh = (SwipeRefreshLayout) stateLayout.findViewById(findRefreshLayoutId());

		//显示数据
		showListViewData(lv);

		//设置刷新方式为  上啦加载
		lv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
	}

	protected abstract void showListViewData(PullToRefreshListView lv);

	protected abstract int findRefreshLayoutId();

	protected abstract int findlistVIewId();

	/** 返回一个正常界面的View或者布局id */
	public abstract Object getContentView();

	/** 初始化监听器的代码写在这个方法中 */
	public  void initListener(){
		//下拉刷新监听
		swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				currentMode = PULL_FROME_START;
				// System.out.println("aaa");
				initData();
			}
		});

		//上啦加载监听
		lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				currentMode = PULL_FROME_END;
				initData();
			}
		});

		//listView滚动监听
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (lv.getRefreshableView().getFirstVisiblePosition() == 0) {
					swiperefresh.setEnabled(true);
				} else {
					swiperefresh.setEnabled(false);
				}
			}
		});

		otherListener();
	}

	protected abstract void otherListener();
	protected abstract String getUrl(int index);

	/** 初始化数据代码写在这个方法中 */
	public  void initData(){
		//判断是刷新模式还是加载模式
		if (currentMode == PULL_FROME_END) {//上拉
			index = index + 1;
		} else if (currentMode == PULL_FROME_START) {//下拉
			index = 0;
		}

		//从网络获取数据
		Ion.with(context)
				.load(getUrl(index))
				.asInputStream()
				.setCallback(callback);

	}

	FutureCallback<InputStream> callback = new FutureCallback<InputStream>() {
		@Override
		public void onCompleted(Exception e, InputStream result) {

			handleData(e, result);
		}
	};

	protected abstract void handleData(Exception e, InputStream result);

}
