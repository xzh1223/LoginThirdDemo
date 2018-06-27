package com.ichuk.loginandsharelib;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXLoginUtil {
    private static IWXAPI api;

    public static void initWX(Context context, String WX_APP_ID) {
        api = WXAPIFactory.createWXAPI(context, WX_APP_ID, false);
        api.registerApp(WX_APP_ID);
    }

    public static void loginToWX() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        api.sendReq(req);
    }
}
