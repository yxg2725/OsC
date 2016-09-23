package com.itheima.osc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.osc.R;


/**
 * 状态布局，封装了4种状态，分别是：正在加载、加载失败、加载为空、正常界面
 * @author dzl
 *
 */
public class StateLayout extends FrameLayout {

	private View loadingView;
	private View failView;
	private View emptyView;
	private View contentView;

	public StateLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public static StateLayout newInstance(Context context, Object viewOrLayoutId) {
		// 填充出来的状态布局已经拥有了3种状态：正在加载、加载失败、加载为空
		StateLayout stateLayout = (StateLayout) View.inflate(context, R.layout.state_layout, null);

		// 因为这里是Base类，Base类不知道子类要设置的正常界面是什么，所以这里把这个正常界面写了抽象方法
		stateLayout.setContentView(viewOrLayoutId);
		return stateLayout;
	}
	
	/**
	 * 不要在构造方法中去做findViewById的操作，因为在构造方法中是查找不到View的，应该在布局填充结束的时候再查找View<br/>
	 * 当布局填充结束的时候会调用这个方法
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		loadingView = findViewById(R.id.loadingView);
		failView = findViewById(R.id.failView);
		emptyView = findViewById(R.id.emptyView);
		showLoadingView();	// 默认显示LoadingView
	}
	
	/**
	 * 设置正常界面的View
	 * @param viewOrLayoutId 可以是一个View，也可以是一个布局id
	 */
	public void setContentView(Object viewOrLayoutId) {
		if (viewOrLayoutId == null) {
			throw new IllegalArgumentException("viewOrLayoutId参数不能为空，必须设置，可以是一个View，也可以是一个布局id");
		}
		
		if (viewOrLayoutId instanceof Integer) {	// 如果viewOrLayoutId参数是一个Integer对象
			int layoutId = (Integer) viewOrLayoutId;	
			contentView = View.inflate(getContext(), layoutId, null);
		} else {	// 如果viewOrLayoutId参数是一个View
			contentView = (View) viewOrLayoutId;
		}
		
		contentView.setVisibility(View.GONE);	// 默认显示的是LoadingView，所以这里先隐藏
		addView(contentView);	// 把正常界面装到状态布局中
		
	}
	
	/** 显示“正在加载”的View */
	public void showLoadingView() {
		showView(loadingView);
	}
	
	/** 显示“加载失败”的View */
	public void showFailView() {
		showView(failView);
	}
	
	/** 显示“加载为空”的View */
	public void showEmptyView() {
		showView(emptyView);
	}
	
	/** 显示“正常界面”的View */
	public void showContentView() {
		showView(contentView);
	}

	/**
	 * 显示指定的View，并且隐藏其它的View
	 * @param view 指定要显示的View
	 */
	private void showView(View view) {
		// 遍历所有的View
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.setVisibility(child == view ? View.VISIBLE : View.GONE);	// 显示指定的View，隐藏其它的View
		}
	}

}
