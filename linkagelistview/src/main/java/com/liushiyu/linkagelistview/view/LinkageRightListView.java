package com.liushiyu.linkagelistview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;

import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageScrollTimeCount;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liushuai on 2018/8/13
 */
public class LinkageRightListView extends LinkageBaseListView {

    private List<LinkageModel> dataList;

    private int index = -1;

    private LinkageScrollTimeCount scrollTimeCount;
    private boolean isNeedRefresh = true;

    private LinkageLeftListView linkageLeftListView;

    public LinkageRightListView(Context context) {
        super(context);
        init();
    }

    public LinkageRightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkageRightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.dataList = new ArrayList<>();
    }

    @Override
    public void setDataList(List<LinkageModel> dataList) {
        this.dataList = dataList;
        super.setDataList(dataList);
    }

    @Override
    public void setDataList(LinkageListViewBaseAdapter baseAdapter, List<LinkageModel> dataList) {
        this.dataList = dataList;
        super.setDataList(baseAdapter, dataList);
    }

    public void startScrollListener() {
        initScrollListener();
    }

    public void stopScrollListener() {
        setOnScrollListener(null);
    }

    private void initScrollListener() {
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isNeedRefresh) {
                    if (index != firstVisibleItem) {
                        index = firstVisibleItem;
                        if (dataList.size() <= index) {
                            return;
                        }
                        if (getLinkageLeftListView() != null) {
                            getLinkageLeftListView().fromLinkageRightListViewMessage(dataList.get(index).getRelationship());
                        }
                    }
                }
            }
        });
    }

    public void setOnSmoothScrollToPositionFromTop(int index) {
        isNeedRefresh = false;
        setSelection(index);
        if (scrollTimeCount == null) {
            scrollTimeCount = new LinkageScrollTimeCount(200);
            scrollTimeCount.setOnTimeCountListener(() -> {
                isNeedRefresh = true;
                scrollTimeCount = null;
            });
            scrollTimeCount.start();
        }
    }

    public LinkageLeftListView getLinkageLeftListView() {
        return linkageLeftListView;
    }

    public void setLinkageLeftListView(LinkageLeftListView linkageLeftListView) {
        this.linkageLeftListView = linkageLeftListView;
    }

}
