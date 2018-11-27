package com.liushiyu.linkagelistview.utils;

import android.os.CountDownTimer;

/**
 * created by liushuai on 2018/9/4
 */
public class LinkageScrollTimeCount extends CountDownTimer {

    public interface OnTimeCountListener {
        void onTimeCountFinish();
    }

    private OnTimeCountListener onItemTimeCountListener;

    public void setOnTimeCountListener(OnTimeCountListener onItemTimeCountListener) {
        this.onItemTimeCountListener = onItemTimeCountListener;
    }

    private OnTimeCountListener getOnTimeCountListener() {
        return onItemTimeCountListener;
    }

    public LinkageScrollTimeCount(long millisInFuture) {
        super(millisInFuture, 1000);
    }

    @Override
    public void onFinish() {
        if (getOnTimeCountListener() != null) {
            getOnTimeCountListener().onTimeCountFinish();
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }
}
