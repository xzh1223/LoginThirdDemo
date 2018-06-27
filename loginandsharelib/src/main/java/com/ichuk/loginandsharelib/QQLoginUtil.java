package com.ichuk.loginandsharelib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ichuk.loginandsharelib.listener.QqLoginListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class QQLoginUtil {
    private static Tencent mTencent;
    private static QqLoginListener mQqLoginListener;

    public static void setQqShareListener(QqLoginListener qqLoginListener) {
        mQqLoginListener = qqLoginListener;
    }

    public static void initQQ(Context context, String QQ_APP_ID) {
        mTencent = Tencent.createInstance(QQ_APP_ID, context.getApplicationContext());
    }

    public static void loginToQQ(Activity activity) {
//        mTencent.login(activity, "all", new BaseUiListener(activity));
        mTencent.login(activity, "all", new BaseUiListener(activity));
    }

    /**
     * result listener
     */
    private static class BaseUiListener implements IUiListener {

        private Context context;

        public BaseUiListener(Context context) {
            this.context = context;
        }

        @Override
        public void onComplete(Object o) {
            Log.e("--->", "onComplete: " + "success" );
            mQqLoginListener.onSuccess(o);
        }

        @Override
        public void onError(UiError e) {
            Log.e("--->", "onError: " + "failed" );
            mQqLoginListener.onFailure(e.errorMessage);
        }

        @Override
        public void onCancel() {
            Log.e("--->", "onCancel: " + "cancel" );
            mQqLoginListener.cancel();
        }

    }

    /**
     * callback
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void loginCallBack(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResult(requestCode, resultCode, data);
    }


}
