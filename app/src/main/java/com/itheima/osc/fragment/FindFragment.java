package com.itheima.osc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.osc.activity.EventActivity;
import com.itheima.osc.activity.FriendResfreshActivity;
import com.itheima.osc.R;
import com.itheima.osc.Util.Intentutils;

/**
 * Created by admin on 2016/9/16.
 */
public class FindFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView tv_shake;
    private TextView tv_find_people;
    private TextView tv_activity;
    private TextView tv_scan;
    private TextView tv_friend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.find_fragment,null);

        tv_friend = (TextView) view.findViewById(R.id.find_friend);
        tv_find_people = (TextView) view.findViewById(R.id.find_find_people);
        tv_activity = (TextView) view.findViewById(R.id.find_activity);
        tv_scan = (TextView) view.findViewById(R.id.find_scan);
        tv_shake = (TextView) view.findViewById(R.id.find_shake);

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_friend.setOnClickListener(this);
        tv_find_people.setOnClickListener(this);
        tv_activity.setOnClickListener(this);
        tv_scan.setOnClickListener(this);
        tv_shake.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.find_friend:
                Intentutils.startIntent(v.getContext(),FriendResfreshActivity.class);//朋友圈
                break;
            case R.id.find_find_people://找人
                break;
            case R.id.find_activity:
                Intentutils.startIntent(v.getContext(),EventActivity.class);//发现
                break;
            case R.id.find_scan://扫一扫

                break;
            case R.id.find_shake:
                Intentutils.startIntent(v.getContext(),ShakeActivity.class);//摇一摇
                break;
        }
       // Toast.makeText(v.getContext(), "哈哈", Toast.LENGTH_SHORT).show();
    }
}
