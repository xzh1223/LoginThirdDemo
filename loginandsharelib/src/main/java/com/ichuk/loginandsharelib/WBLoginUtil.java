package com.ichuk.loginandsharelib;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ichuk.loginandsharelib.listener.WbLoginListener;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.BaseSsoHandler;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class WBLoginUtil {
    private String REDIRECT_URL = "";
    private String SCOPE = "";
    private String APP_KEY_SINA = "";
    private static BaseSsoHandler mSsoHandler;
    private static WbLoginListener mWbLoginListener;
    private static final String TAG = "WBLoginUtil";
    public static void setWbLoginListener(WbLoginListener wbLoginListener) {
        mWbLoginListener = wbLoginListener;
    }

    /**
     * initial WB
     *
     * @param context
     */
    public static void initWebSDK(Context context, String APP_KEY_SINA, String REDIRECT_URL, String SCOPE) {
        WbSdk.install(context, new AuthInfo(context, APP_KEY_SINA, REDIRECT_URL, SCOPE));
    }

    /**
     *  login to sina
     */
    public static void loginToSina(Activity activity) {
        mSsoHandler= new SsoHandler(activity);
        //授权方式有三种，第一种对客户端授权 第二种对Web短授权，第三种结合前两中方式
        mSsoHandler.authorize(new SelfWbAuthListener());
    }

    /**
     * auth listener
     */
    private static class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(Oauth2AccessToken accessToken) {
            if (accessToken.isSessionValid()) {
                Log.e("--->", "onSuccess: " + "授权成功" );
                mWbLoginListener.onSuccess(accessToken.getToken());
            }
        }

        @Override
        public void cancel() {
            Log.e("--->", "cancel: " + "取消授权" );
            mWbLoginListener.cancel();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Log.e("--->", "onFailure: " + "授权失败" );
            mWbLoginListener.onFailure(wbConnectErrorMessage.getErrorMessage());
        }
    }
}
