package com.ichuk.loginandsharelib;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXShareUtil {
    private static IWXAPI msgApi;

    /**
     * initial WX
     * @param context 上下文
     * @param WX_APP_ID  APP_ID
     */
    public static void initWX(Context context, String WX_APP_ID) {
        msgApi = WXAPIFactory.createWXAPI(context, WX_APP_ID, false);
        msgApi.registerApp(WX_APP_ID);
    }

    /**
     *  share to WX
     * @param url
     * @param title
     * @param content
     * @param isTimelineCb - true 朋友圈 - false 微信
     */
    public static void shareToWX(String url, String title, String content, boolean isTimelineCb) {
        // initial WXTxtObject to write text content
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = url;

        WXMediaMessage message = new WXMediaMessage(wxWebpageObject);
        message.title = title;
        message.description = content;

        // constructor req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = title;
        req.message = message;
        req.scene = isTimelineCb ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        msgApi.sendReq(req);
    }
}
