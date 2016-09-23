package com.itheima.osc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.itheima.osc.R;

import butterknife.ButterKnife;

/**
 * Created by bearabit on 2016/9/17 18:27.
 */
public class TweetDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtan_details);
        ButterKnife.bind(this);
    }
}
