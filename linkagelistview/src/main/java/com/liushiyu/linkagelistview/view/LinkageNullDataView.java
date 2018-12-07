package com.liushiyu.linkagelistview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.liushiyu.linkagelistview.presenter.LinkageNullDataViewPresenter;

/**
 * created by liushuai on 2018/12/7
 */
public class LinkageNullDataView extends LinearLayout {

    private LinkageNullDataViewPresenter presenter;

    public LinkageNullDataView(Context context) {
        super(context);
        init();
    }

    public LinkageNullDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkageNullDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        presenter = new LinkageNullDataViewPresenter(this);
    }

    public void setLinkageNullDataView(View view) {
        presenter.setLinkageNullDataView(view);
    }
}
