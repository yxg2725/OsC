package com.itheima.osc.Util;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.itheima.osc.App;
import com.itheima.osc.bean.Active;
import com.itheima.osc.widget.TextViewFixTouchConsume;

/**
 * Created by admin on 2016/9/21.
 */
public class AdapterSetDataUtil {

    //设置圆形头像
    public static void setCircleIcon(final ImageView ivIcon, String iconUrl) {
        Glide.with(com.itheima.osc.App.getContext()).load(iconUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivIcon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(App.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivIcon.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    //设置评论个数
    public static void setCommentCount(TextView tv, int count) {
        if(count != 0){
            tv.setText(count+"");
        }else{
            tv.setVisibility(View.INVISIBLE);
        }
    }

    //设置回复的昵称和内容
    public static void setReply(TextView tv, Active.ObjectReply objectReply) {
        if (objectReply != null){
            tv.setVisibility(View.VISIBLE);
            String objectName = objectReply.getObjectName();
            String objectBody = objectReply.getObjectBody();
            tv.setText(objectName.trim() + ":" + objectBody.trim());
        }else{
            tv.setVisibility(View.GONE);
        }
    }

    //设置标题 和 状态
    public static void setDescAndTitle(TextView tv, int catalog, String title) {
        String catalogDesc = null;
        switch(catalog){
            case 0:
            case 1:
                catalogDesc = "分享了一段代码";
                break;
            case 2:
                catalogDesc = "回答了问题";
                break;
            case 3:
                catalogDesc = "跟新了动态";
                break;
            case 4:
                catalogDesc = "发表了博客";
                break;

        }
        if (catalogDesc != null) {

            //显示标题
            String htmlTitle="<font color='gray'>"+catalogDesc+"</font> ";
            htmlTitle+="<font color='blue'>"+title+"</font> ";
            tv.setText(Html.fromHtml(htmlTitle));
        }
    }

    //设置昵称
    public static void setName(TextView tv, String name) {
        String html="<font color='blue'>"+name+"</font> ";
        CharSequence charSequence = Html.fromHtml(html);
        tv.setText(charSequence);
    }

    //设置内容
    public static void setContent(TextView tv, String content) {
        Spanned spannedContent = Html.fromHtml(content);
        tv.setText(spannedContent);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
       // tv.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
        // holder.content.setMovementMethod(new LinkMovementMethod());
       // tv.setFocusable(false);
    }

    //设置动弹图片
    public static void setTweetImagView(ImageView iv, String url) {
        if (!TextUtils.isEmpty(url)){
            iv.setVisibility(View.VISIBLE);
            Glide.with(com.itheima.osc.App.getContext()).load(url).centerCrop().into(iv);
        }else{
            iv.setVisibility(View.GONE);
        }
    }

    //设置手机信息
    public static void getAndSetPhone(TextView tv, int num) {
        String phone = null;
        switch(num){
            case 0:
                phone = "iphone";
                break;
            case 1:
                phone = "nuojiya";
                break;

            case 2:
                phone = "windows";
                break;
            case 3:
                phone = "iphone";
                break;
            case 4:
                phone = "xiaomi";
                break;

        }
        if (phone != null) {
            tv.setText(phone);
        }else{
            tv.setVisibility(View.GONE);
        }
    }
}
