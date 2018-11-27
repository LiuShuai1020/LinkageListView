package com.liushiyu.linkagelistview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.*;

import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageListViewObservable;
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
        initScrollListener();
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

    private void log(String s) {
        Log.e("LinkageRightListView", s);
    }

    private void initScrollListener() {
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                log("firstVisibleItem = " + firstVisibleItem);
                log("visibleItemCount = " + visibleItemCount);
                log("totalItemCount = " + totalItemCount);
                log("======================");
                if (isNeedRefresh) {
                    if (index != firstVisibleItem) {
                        index = firstVisibleItem;
                        if (dataList.size() <= index) {
                            return;
                        }
                        log("onScrollStateChanged onScroll index = " + index);
                        LinkageListViewObservable.getInstance().scrollToIndex(dataList.get(index).getRelationship());
                    }
                }
            }
        });
    }

    public void setOnSmoothScrollToPositionFromTop(int index) {
        log("setOnSmoothScrollToPositionFromTop index = " + index);
        isNeedRefresh = false;
        setSelection(index);
        if (scrollTimeCount == null) {
            scrollTimeCount = new LinkageScrollTimeCount(200);
            scrollTimeCount.setOnTimeCountListener(() -> {
                log("setOnSmoothScrollToPositionFromTop finish");
                isNeedRefresh = true;
                scrollTimeCount = null;
            });
            scrollTimeCount.start();
        }
    }
}
