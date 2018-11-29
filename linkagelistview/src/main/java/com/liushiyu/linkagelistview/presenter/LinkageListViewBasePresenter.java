package com.liushiyu.linkagelistview.presenter;

import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.listener.LinkageListViewItemClick;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.List;

/**
 * created by liushuai on 2018/11/29
 */
public abstract class LinkageListViewBasePresenter {
    public abstract void setLinkageColorUtil(LinkageColorUtil linkageColorUtil);

    public abstract void setLinkageData(List<LinkageModel> modelList);

    public abstract void setLinkageData(LinkageListViewBaseAdapter leftBaseAdapter, LinkageListViewBaseAdapter rightBaseAdapter, List<LinkageModel> modelList);

    public abstract void setDefaultItem(int leftIndex, int rightIndex);

    public abstract void setLinkageListener(LinkageListViewItemClick linkageListViewListener);
}
