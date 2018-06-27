package com.ichuk.loginandsharelib;

import android.app.Activity;
import android.content.Intent;

import com.ichuk.loginandsharelib.listener.WbShareListener;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

public class WBShareUtil {
    private static AuthInfo mAuthInfo;
    private static WbShareHandler wbShareHandler;
    private static WbShareListener mWbShareListener;

    public static void setWbShareListener(WbShareListener wbShareListener) {
        mWbShareListener = wbShareListener;
    }

    public static void initWB(Activity activity, String APP_KEY_SINA, String REDIRECT_URL, String SCOPE) {
        mAuthInfo = new AuthInfo(activity, APP_KEY_SINA, REDIRECT_URL, SCOPE);
        WbSdk.install(activity, mAuthInfo);
        wbShareHandler = new WbShareHandler(activity);
        wbShareHandler.registerApp();
    }

    public static void shareToWB(String url, String title, String content) {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        TextObject textObject = new TextObject();
        textObject.text = content;
        textObject.title = title;
        textObject.actionUrl = url;
        weiboMessage.textObject = textObject;
        wbShareHandler.shareMessage(weiboMessage, false);
    }

    public static void shareCallBack(Intent intent) {
        wbShareHandler.doResultIntent(intent, new WbShareCallback() {
            @Override
            public void onWbShareSuccess() {
                mWbShareListener.onSuccess();
            }

            @Override
            public void onWbShareCancel() {
                mWbShareListener.cancel();
            }

            @Override
            public void onWbShareFail() {
                mWbShareListener.onFailure("分享失败");
            }
        });
    }

}
