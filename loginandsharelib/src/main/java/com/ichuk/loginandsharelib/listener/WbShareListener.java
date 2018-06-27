package com.ichuk.loginandsharelib.listener;

public interface WbShareListener {
    void onSuccess();
    void cancel();
    void onFailure(String errorMsg);
}
