package com.itheima.osc.adapter;

import java.util.ArrayList;
import java.util.List;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ListView的适配器都应该继承这个基类
 * @author dzl
 *
 * @param <T>
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

	protected List<T> datas;
	
	public MyBaseAdapter(List<T> datas) {
		this.datas = datas;
	}
	
	/** 获取适配器中的数据，相信我吧，这个方法的返回值绝对不会是null，最多是datas.isEmpty() */
	public List<T> getDatas() {
		if (datas == null) {
			datas = new ArrayList<T>();
		}
		return datas;
	}

	@Override
	public int getCount() {
		int count = datas == null ? 0 : datas.size();
		return count;
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object holder;
		if (convertView == null) {
			convertView = View.inflate(parent.getContext(), getItemLayoutId(position), null);
			holder = createViewHolderAndFindViewById(position, convertView);
			convertView.setTag(holder);
		} else {
			holder = convertView.getTag();
		}
		
		// 取出数据
		T data = datas.get(position);
		
		// 显示数据
		showData(position, holder, data);
		
		return convertView;
	}



	/** 返回一个布局id，用于创建item界面 */
	public abstract int getItemLayoutId(int position);
	
	/** 创建一个ViewHolder对象，并把converView中的子View引用保存到ViewHolder中，以便进行复用 */
	public abstract Object createViewHolderAndFindViewById(int position, View convertView);
	
	/** 把数据显示到ViewHolder里面的View上 */
	public abstract void showData(int position, Object viewHolder, T data);

}
