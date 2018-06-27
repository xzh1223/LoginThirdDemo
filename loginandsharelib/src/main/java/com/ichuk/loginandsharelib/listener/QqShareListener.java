package com.ichuk.loginandsharelib.listener;

public interface QqShareListener {
    void onSuccess();
    void cancel();
    void onError(String errorMsg);
}
