package com.liushiyu.linkagelistview.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.liushiyu.linkagelistview.LinkageListView;
import com.liushiyu.linkagelistview.R;
import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.listener.LinkageListViewItemClick;
import com.liushiyu.linkagelistview.model.BaseModel;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;
import com.liushiyu.linkagelistview.view.LinkageLeftListView;
import com.liushiyu.linkagelistview.view.LinkageRightListView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_LEFT;
import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_RIGHT;

/**
 * created by liushuai on 2018/11/29
 */
public class LinkageListViewNoScrollPresenter extends LinkageListViewBasePresenter {
    private LinkageListViewItemClick linkageListViewListener;

    private int clickIndex = -1;
    private TextView rightNoDataView;
    private LinkageLeftListView leftListView;
    private LinkageRightListView rightListView;

    private List<LinkageModel> linkageDataModelList = new ArrayList<>();

    private LinkageListViewBaseAdapter rightBaseAdapter;

    public LinkageListViewNoScrollPresenter(Context context, LinkageListView linkageListView) {
        init(context, linkageListView);
    }

    private void init(Context context, LinkageListView linkageListView) {

        View root = View.inflate(context, R.layout.view_common_linkage_listview, linkageListView);

        leftListView = root.findViewById(R.id.leftListView);
        rightListView = root.findViewById(R.id.rightListView);
        rightNoDataView = root.findViewById(R.id.rightNoDataView);

        leftListView.setLinkageListViewListener(this::onLeftItemClick);
        rightListView.setLinkageListViewListener(this::onRightItemClick);
    }

    @Override
    public void setLinkageColorUtil(LinkageColorUtil linkageColorUtil) {
        leftListView.setLinkageColorUtil(linkageColorUtil);
        rightListView.setLinkageColorUtil(linkageColorUtil);
    }

    @Override
    public void setLinkageData(List<LinkageModel> modelList) {
        setLinkageData(null, null, modelList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setLinkageData(LinkageListViewBaseAdapter leftBaseAdapter, LinkageListViewBaseAdapter rightBaseAdapter, List<LinkageModel> modelList) {
        this.rightBaseAdapter = rightBaseAdapter;

        this.linkageDataModelList = linkageDataFilter(modelList);

        if (linkageDataModelList.size() == 0) {
            leftListView.setVisibility(GONE);
            rightListView.setVisibility(INVISIBLE);
            rightNoDataView.setVisibility(VISIBLE);
            return;
        }

        if (leftBaseAdapter == null) {
            leftListView.setDataList(linkageDataModelList);
        } else {
            leftListView.setDataList(leftBaseAdapter, linkageDataModelList);
        }
        leftListView.setVisibility(VISIBLE);

        if (linkageDataModelList.get(0).getLinkageRightModelList().size() == 0) {
            rightListView.setVisibility(INVISIBLE);
            rightNoDataView.setVisibility(VISIBLE);
        } else {
            if (rightBaseAdapter == null) {
                rightListView.setDataList(modelList.get(0).getLinkageRightModelList());
            } else {
                rightListView.setDataList(rightBaseAdapter, modelList.get(0).getLinkageRightModelList());
            }
            rightListView.setVisibility(VISIBLE);
            rightNoDataView.setVisibility(INVISIBLE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setDefaultItem(int leftIndex, int rightIndex) {

        if (linkageDataModelList.size() == 0) {
            return;
        }

        if (linkageDataModelList.get(leftIndex).getLinkageRightModelList().size() == 0) {
            return;
        }

        if (leftIndex >= linkageDataModelList.size()) {
            return;
        }

        if (rightIndex >= linkageDataModelList.get(leftIndex).getLinkageRightModelList().size()) {
            return;
        }

        if (linkageDataModelList.size() == 0) {
            leftListView.setVisibility(GONE);
            rightListView.setVisibility(INVISIBLE);
            rightNoDataView.setVisibility(VISIBLE);
            return;
        }
        onResetType();
        linkageDataModelList.get(leftIndex).setState(BaseModel.TYPE_CHOICE_NO_FOCUS);
        ((LinkageModel) linkageDataModelList.get(leftIndex).getLinkageRightModelList().get(rightIndex)).setState(BaseModel.TYPE_CHOICE_FOCUS);

        clickIndex = leftIndex;
        leftListView.setDefaultSelectItem(leftIndex);
        if (linkageDataModelList.get(leftIndex).getLinkageRightModelList().size() == 0) {
            rightNoDataView.setVisibility(VISIBLE);
            rightListView.setVisibility(INVISIBLE);
        } else {
            if (rightBaseAdapter == null) {
                rightListView.setDataList(linkageDataModelList.get(leftIndex).getLinkageRightModelList());
            } else {
                rightListView.setDataList(rightBaseAdapter, linkageDataModelList.get(leftIndex).getLinkageRightModelList());
            }

            rightListView.setDefaultSelectItem(rightIndex);
            rightNoDataView.setVisibility(INVISIBLE);
            rightListView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void setLinkageListener(LinkageListViewItemClick linkageListViewListener) {
        this.linkageListViewListener = linkageListViewListener;
    }

    private void onLeftItemClick(int position) {
        onLeftItemClickLogin(position);
    }

    @SuppressWarnings("unchecked")
    private void onLeftItemClickLogin(int position) {
        for (int i = 0; i < linkageDataModelList.size(); i++) {
            List linkageRightModelList = linkageDataModelList.get(i).getLinkageRightModelList();
            for (int j = 0; j < linkageRightModelList.size(); j++) {
                if (((LinkageModel) linkageRightModelList.get(j)).getState() == BaseModel.TYPE_CHOICE_FOCUS) {
                    ((LinkageModel) linkageDataModelList.get(i).getLinkageRightModelList().get(j)).setState(BaseModel.TYPE_CHOICE_NO_FOCUS);
                }
            }
        }

        if (linkageDataModelList.get(position).getLinkageRightModelList().size() == 0) {
            rightNoDataView.setVisibility(VISIBLE);
            rightListView.setVisibility(INVISIBLE);
        } else {
            rightNoDataView.setVisibility(INVISIBLE);
            if (rightBaseAdapter == null) {
                rightListView.setDataList(linkageDataModelList.get(position).getLinkageRightModelList());
            } else {
                rightListView.setDataList(rightBaseAdapter, linkageDataModelList.get(position).getLinkageRightModelList());
            }
            rightListView.setVisibility(VISIBLE);
            rightListView.setSelection(0);
            rightListView.clearChoices();
        }
        clickIndex = position;

        rightListView.notifyDataSetChanged();
    }

    private void onRightItemClick(int position) {
        onRightClickLogic(clickIndex, position);

        if (linkageListViewListener != null) {
            linkageListViewListener.onLinkageItemClick((LinkageModel) linkageDataModelList.get(clickIndex).getLinkageRightModelList().get(position));
        }
    }

    private void onRightClickLogic(int leftPosition, int rightPosition) {
        for (int i = 0; i < linkageDataModelList.size(); i++) {

            if (linkageDataModelList.get(i).getState() == BaseModel.TYPE_CHOICE_FOCUS) {
                linkageDataModelList.get(i).setState(BaseModel.TYPE_CHOICE_NO_FOCUS);
            }

            List linkageRightModelList = linkageDataModelList.get(i).getLinkageRightModelList();
            for (int j = 0; j < linkageRightModelList.size(); j++) {
                ((LinkageModel) linkageRightModelList.get(j)).setState((leftPosition == i && rightPosition == j) ? BaseModel.TYPE_CHOICE_FOCUS : BaseModel.TYPE_NO_CHOICE);
            }
        }
        leftListView.notifyDataSetChanged();
    }

    private void onResetType() {
        for (int i = 0; i < linkageDataModelList.size(); i++) {
            linkageDataModelList.get(i).setState(BaseModel.TYPE_NO_CHOICE);
            List rightModelList = linkageDataModelList.get(i).getLinkageRightModelList();
            for (int j = 0; j < rightModelList.size(); j++) {
                ((LinkageModel) linkageDataModelList.get(i).getLinkageRightModelList().get(j)).setState(BaseModel.TYPE_NO_CHOICE);
            }
        }
    }

    private List<LinkageModel> linkageDataFilter(List<LinkageModel> modelList) {
        List<LinkageModel> modelFilterDataList = new ArrayList<>();
        List<LinkageModel> rightModelList = new ArrayList<>();
        for (LinkageModel model : modelList) {
            switch (model.getLeftOrRight()) {
                case LINKAGE_LEFT:
                    modelFilterDataList.add(model);
                    break;
                case LINKAGE_RIGHT:
                    rightModelList.add(model);
                    break;
            }
        }
        for (int i = 0; i < modelFilterDataList.size(); i++) {
            int relationship = modelFilterDataList.get(i).getRelationship();
            List<LinkageModel> linkageRightModelList = new ArrayList<>();
            for (int j = 0; j < rightModelList.size(); j++) {
                if (relationship == rightModelList.get(j).getRelationship()) {
                    linkageRightModelList.add(rightModelList.get(j));
                }
            }
            modelFilterDataList.get(i).setLinkageRightModelList(linkageRightModelList);
        }
        return modelFilterDataList;
    }
}
