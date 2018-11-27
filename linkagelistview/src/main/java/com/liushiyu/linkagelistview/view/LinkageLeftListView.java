package com.liushiyu.linkagelistview.view;

import android.content.Context;
import android.util.AttributeSet;

import com.liushiyu.linkagelistview.utils.LinkageListViewObservable;
import com.liushiyu.linkagelistview.utils.LinkageListViewObserver;

import java.util.Observable;

/**
 * created by liushuai on 2018/8/13
 */
public class LinkageLeftListView extends LinkageBaseListView implements LinkageListViewObserver {

    public LinkageLeftListView(Context context) {
        super(context);
        init();
    }

    public LinkageLeftListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkageLeftListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LinkageListViewObservable.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Integer) {
            setOnScrollSelectItem((int) o);
        }
    }
}
