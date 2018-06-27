package com.ichuk.loginandsharelib.listener;

public interface WbLoginListener {
    void onSuccess(String token);
    void cancel();
    void onFailure(String errorMsg);
}
