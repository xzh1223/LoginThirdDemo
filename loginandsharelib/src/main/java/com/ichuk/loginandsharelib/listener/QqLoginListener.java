package com.ichuk.loginandsharelib.listener;

public interface QqLoginListener {
    void onSuccess(Object o);
    void cancel();
    void onFailure(String errorMsg);
}
