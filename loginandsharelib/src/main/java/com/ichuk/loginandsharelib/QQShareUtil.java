package com.ichuk.loginandsharelib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ichuk.loginandsharelib.listener.QqShareListener;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

public class QQShareUtil {
    private static Context mContext;
    private static Tencent mTencent;
    private static QqShareListener mQqShareListener;

    public static void setQqShareListener(QqShareListener qqShareListener) {
        mQqShareListener = qqShareListener;
    }

    /**
     * initial QQ
     *
     * @param context
     * @param QQ_APP_ID
     */
    public static void initQQ(Context context, String QQ_APP_ID) {
        mContext = context;
        mTencent = Tencent.createInstance(QQ_APP_ID, context.getApplicationContext());
    }

    /**
     * share to QQ
     *
     * @param activity
     * @param url
     * @param title
     * @param content
     * @param image    - image's url
     */
    public static void shareToQQ(Activity activity, String url, String title, String content, String image) {
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, image);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, title);
        mTencent.shareToQQ(activity, bundle, new BaseUiListener());
    }

    /**
     * share to QQzone
     *
     * @param activity
     * @param url
     * @param title
     * @param content
     * @param image    - image's url
     */
    public static void shareToQzone(Activity activity, String url, String title, String content, String image) {
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);// 标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, content);// 摘要 　　
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);// 内容地址 　　
        ArrayList<String> imgUrlList = new ArrayList<>();
        imgUrlList.add(image);
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);// 图片地址
        mTencent.shareToQzone(activity, params, new BaseUiListener());
    }

    /**
     *  result listener
     */
    private static class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            mQqShareListener.onSuccess();
        }

        @Override
        public void onError(UiError e) {
            mQqShareListener.onError(e.errorMessage);
        }

        @Override

        public void onCancel() {
            mQqShareListener.cancel();
        }

    }

    /**
     * callback
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void ShareCallBack(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResult(requestCode, resultCode, data);
    }
}
