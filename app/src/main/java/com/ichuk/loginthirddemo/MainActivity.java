package com.ichuk.loginthirddemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ichuk.loginandsharelib.QQLoginUtil;
import com.ichuk.loginandsharelib.WBLoginUtil;
import com.ichuk.loginandsharelib.WXLoginUtil;
import com.ichuk.loginandsharelib.listener.QqLoginListener;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        findViewById(R.id.btn_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QQLoginUtil.initQQ(context, "222222");
                QQLoginUtil.loginToQQ(MainActivity.this);
                QQLoginUtil.setQqShareListener(new QqLoginListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.btn_wb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
                String SCOPE =
                        "email,direct_messages_read,direct_messages_write,"
                                + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                                + "follow_app_official_microblog," + "invitation_write";
                WBLoginUtil.initWebSDK(context, "2162999997", REDIRECT_URL, SCOPE);
                WBLoginUtil.loginToSina(MainActivity.this);
//                WBLoginUtil.setWbLoginListener(new WbLoginListener() {
//                    @Override
//                    public void onSuccess(String token) {
//                        Log.e("--->", "onSuccess: " + token );
//                    }
//
//                    @Override
//                    public void cancel() {
//
//                    }
//
//                    @Override
//                    public void onFailure(String errorMsg) {
//                        Log.e("--->", "onFailure: " + errorMsg );
//                    }
//                });
            }
        });
        findViewById(R.id.btn_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXLoginUtil.initWX(context, "");
                WXLoginUtil.loginToWX();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        QQLoginUtil.loginCallBack(requestCode, resultCode, data);
        Log.e("--->>", "onActivityResult: " + requestCode + "--" + resultCode );
    }
}
