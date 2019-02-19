package com.liushiyu.linkagelistview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liushuai on 2018/11/26
 */
public abstract class LinkageListViewBaseAdapter<T> extends BaseAdapter {

    public abstract void init(Context context);

    public abstract void getDataList(List<LinkageModel> dataList);

    public abstract T getLinkageListViewViewHolder(View view);

    public abstract View getView(ViewGroup viewGroup);

    public abstract void refreshLinkageViewData(int position, View view, ViewGroup viewGroup, T viewHolder, String direction);

    public abstract void refreshLinkageViewState(int position, T viewHolder, String direction, int state, LinkageColorUtil linkageColorUtil);

    private List<LinkageModel> dataList = new ArrayList<>();
    private LinkageColorUtil linkageColorUtil;

    public LinkageListViewBaseAdapter(Context context) {
        this.linkageColorUtil = new LinkageColorUtil();
        init(context);
    }

    public void setDataList(List<LinkageModel> dataList) {
        this.dataList = dataList;
        getDataList(dataList);
    }

    public void setLinkageColorUtil(LinkageColorUtil linkageColorUtil) {
        this.linkageColorUtil = linkageColorUtil;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dataList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        T viewHolder;
        if (view == null) {
            view = getView(viewGroup);
            viewHolder = getLinkageListViewViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (T) view.getTag();
        }
        refreshLinkageViewData(i, view, viewGroup, viewHolder, dataList.get(i).getLeftOrRight());
        refreshLinkageViewState(i, viewHolder, dataList.get(i).getLeftOrRight(), dataList.get(i).getState(), linkageColorUtil);
        return view;
    }
}
