package com.liushiyu.linkagelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.listener.LinkageListViewItemClick;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.presenter.LinkageListViewPresenter;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.List;

/**
 * 左右联动ListView
 * created by liushuai on 2018/8/13
 */
public class LinkageListView extends LinearLayout {

    private LinkageListViewPresenter presenter;

    public LinkageListView(Context context) {
        super(context);
        init();
    }

    public LinkageListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkageListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        presenter = new LinkageListViewPresenter(getContext(), this);
    }

    public void setLinkageListener(LinkageListViewItemClick linkageListViewListener) {
        presenter.setLinkageListener(linkageListViewListener);
    }

    public void setLinkageColorUtil(LinkageColorUtil linkageColorUtil) {
        presenter.setLinkageColorUtil(linkageColorUtil);
    }

    public void setLinkageData(List<LinkageModel> modelList) {
        presenter.setLinkageData(modelList);
    }

    public void setLinkageData(LinkageListViewBaseAdapter leftBaseAdapter, LinkageListViewBaseAdapter rightBaseAdapter, List<LinkageModel> modelList) {
        presenter.setLinkageData(leftBaseAdapter, rightBaseAdapter, modelList);
    }

    public void setDefaultItem(int leftIndex, int rightIndex) {
        presenter.setDefaultItem(leftIndex, rightIndex);
    }
}
