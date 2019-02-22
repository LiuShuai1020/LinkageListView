package com.liushiyu.linkagelistview.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liushiyu.linkagelistview.adapter.LinkageListViewAdapter;
import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.listener.LinkageListViewOnItemClickListener;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.ArrayList;
import java.util.List;

import static com.liushiyu.linkagelistview.model.LinkageModel.*;

/**
 * created by liushuai on 2018/8/15
 */
public class LinkageBaseListView extends ListView {

    private LinkageListViewOnItemClickListener linkageListViewListener;

    public void setLinkageListViewListener(LinkageListViewOnItemClickListener linkageListViewListener) {
        this.linkageListViewListener = linkageListViewListener;
    }

    private Context mContext;
    private List<LinkageModel> dataList = new ArrayList<>();
    private LinkageListViewBaseAdapter adapter;

    private boolean isNeedLoadLinkageColorUtil = false;
    private LinkageColorUtil linkageColorUtil;

    public LinkageBaseListView(Context context) {
        super(context);
        init();
    }

    public LinkageBaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkageBaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.mContext = getContext();
        setVerticalScrollBarEnabled(false);
        setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        initListener();
    }

    private void initListener() {
        setOnItemClickListener(this::onItemClickFunction);
    }

    private void onItemClickFunction(AdapterView<?> adapterView, View view, int i, long l) {
        if (linkageListViewListener != null) {
            linkageListViewListener.setOnLinkageItemClickListener(i);
        }
        setSelectItem(i);
    }

    public void setLinkageColorUtil(LinkageColorUtil linkageColorUtil) {
        if (adapter != null) {
            adapter.setLinkageColorUtil(linkageColorUtil);
            isNeedLoadLinkageColorUtil = false;
        } else {
            isNeedLoadLinkageColorUtil = true;
            this.linkageColorUtil = linkageColorUtil;
        }
    }

    private void setSelectItem(int index) {
        if (dataList.size() == 0) {
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).setState(index == i ? TYPE_CHOICE_FOCUS : TYPE_NO_CHOICE);
        }
        notifyDataSetChanged();
    }

    public void setDataList(List<LinkageModel> dataList) {
        setDataList(null, dataList);
    }

    public void setDataList(LinkageListViewBaseAdapter baseAdapter, List<LinkageModel> dataList) {
        if (dataList.size() == 0) {
            return;
        }
        this.dataList = dataList;

        if (adapter == null) {
            if (baseAdapter == null) {
                adapter = new LinkageListViewAdapter(mContext);
            } else {
                adapter = baseAdapter;
            }
        }
        adapter.setDataList(dataList);
        setAdapter(adapter);

        if (isNeedLoadLinkageColorUtil) {
            adapter.setLinkageColorUtil(linkageColorUtil);
        }
    }

    public void setDefaultSelectItem(int index) {
        if (dataList.size() == 0) {
            return;
        }
        if (dataList.get(index).getLeftOrRight().equals(LINKAGE_RIGHT)) {
            for (int i = 0; i < dataList.size(); i++) {
                dataList.get(i).setState(index == i ? TYPE_CHOICE_NO_FOCUS : TYPE_NO_CHOICE);
            }
        } else {
            setSelectItem(index);
        }
        notifyDataSetChanged();
        smoothScrollToPosition(index);
    }

    public void setLinkageDivider(Drawable divider) {
        setDivider(divider);
    }

    public void setLinkageDividerHeight(int height) {
        setDividerHeight(height);
    }

    public void hideLinkageDivider() {
        setDivider(null);
        setDividerHeight(0);
    }

    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void setOnScrollSelectItem(int index) {
        if (dataList.size() == 0) {
            return;
        }
        if (dataList.size() <= index) {
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).setState(index == i ? TYPE_CHOICE_NO_FOCUS : TYPE_NO_CHOICE);
        }
        notifyDataSetChanged();
    }
}
