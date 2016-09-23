package com.itheima.osc.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.osc.R;
import com.itheima.osc.util.ToastUtil;

/**
 * Created by Mrs.Yan on 2016/9/18.
 */
public class AddStatusActivity extends Activity {
    private EditText et_editStatus;
    private TextView tv_count;
    private ImageButton ib_count;
    private ImageButton titlebar_btn_menu;
    private ImageView tanyitan_sendbutton1;
    private ImageView tanyitan_sendbutton2;
    private RelativeLayout tanyitan_backbutton;
    private RelativeLayout tanyitan_send;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus_message);
        //文本框相关控件
        et_editStatus = (EditText) findViewById(R.id.et_editStatus);//文本框
        tv_count = (TextView) findViewById(R.id.tv_count);
        ib_count = (ImageButton) findViewById(R.id.ib_count);

        //titlebar控件
        tanyitan_backbutton = (RelativeLayout) findViewById(R.id.tanyitan_backbutton);
        tanyitan_send = (RelativeLayout) findViewById(R.id.tanyitan_send);

        tanyitan_sendbutton1 = (ImageView) findViewById(R.id.tanyitan_sendbutton1);
        tanyitan_sendbutton2 = (ImageView) findViewById(R.id.tanyitan_sendbutton2);


        //返回按钮的点击监听
        tanyitan_backbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.shortToast(getApplicationContext(), "返回主页面");
            }
        });
        /**注册输入框内容监听器*/
        et_editStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //当输入框内容变化的时候执行
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean flag = false;
                String mText = et_editStatus.getText().toString();
                int len = mText.length();
                tv_count.setText(String.valueOf(160 - len));


                if (TextUtils.isEmpty(et_editStatus.getText())) {
                    ib_count.setClickable(false);
                    tanyitan_sendbutton1.setVisibility(View.GONE);
                    tanyitan_sendbutton2.setVisibility(View.VISIBLE);
                } else {
                    tanyitan_sendbutton1.setVisibility(View.VISIBLE);
                    tanyitan_sendbutton2.setVisibility(View.GONE);
                    ib_count.setClickable(true);
                    //添加触摸事件，点击之后，清空editText的效果
                    ib_count.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            DialogInterface.OnClickListener listener;
                            listener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            };
                            final AlertDialog builder = new AlertDialog.Builder(AddStatusActivity.this)
                                    .create();
                            builder.show();
                            builder.getWindow().setContentView(R.layout.clear_message_login);//设置弹出框加载的布局
                            builder.getWindow()
                                    .findViewById(R.id.myedit)
                                    .setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            builder.dismiss();
                                        }
                                    });
                            builder.getWindow()
                                    .findViewById(R.id.myok)
                                    .setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            et_editStatus.getText().clear();
                                            builder.dismiss();
                                        }
                                    });
                        }
                    });
                    //发送按钮的监听
                    tanyitan_send.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.shortToast(getApplicationContext(), "发布话题");
                        }
                    });
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}