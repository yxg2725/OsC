package com.itheima.osc.Util;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by admin on 2016/9/19.
 */
public class TextFuWenBenUtil {

    //显示颜色字体
    public static void addTextColor(TextView tv, String color,String str){

    }

    public static  void addTextLink(TextView tv, String color, String str){
        String html = "<font color='blue'></font><br><br><br>";
        html += "<font color='#0000ff'><big><i></i></big></font><p>";
        html += "<big><a href='http://leicdn.duapp.com/Github/wipe/demo/auto_wipe.html'>http://leicdn.duapp.com/Github/wipe/demo/auto_wipe.html</a></big><br>";
        CharSequence charSequence= Html.fromHtml(html);
        tv.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
