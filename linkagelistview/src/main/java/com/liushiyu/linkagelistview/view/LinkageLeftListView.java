package com.liushiyu.linkagelistview.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * created by liushuai on 2018/8/13
 */
public class LinkageLeftListView extends LinkageBaseListView {

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

    }

    public void fromLinkageRightListViewMessage(int index) {
        setOnScrollSelectItem(index);
    }
}
