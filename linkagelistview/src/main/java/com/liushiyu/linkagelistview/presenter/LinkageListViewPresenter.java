package com.liushiyu.linkagelistview.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.TextView;

import com.liushiyu.linkagelistview.LinkageListView;
import com.liushiyu.linkagelistview.R;
import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.listener.LinkageListViewItemClick;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;
import com.liushiyu.linkagelistview.view.LinkageLeftListView;
import com.liushiyu.linkagelistview.view.LinkageRightListView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.liushiyu.linkagelistview.model.BaseModel.*;

/**
 * created by liushuai on 2018/11/26
 */
public class LinkageListViewPresenter extends LinkageListViewBasePresenter {

    private LinkageListViewItemClick linkageListViewListener;

    private ConstraintLayout mConstraintLayout;

    @Override
    public void setLinkageListener(LinkageListViewItemClick linkageListViewListener) {
        this.linkageListViewListener = linkageListViewListener;
    }

    private LinkageListViewItemClick getLinkageListViewListener() {
        return linkageListViewListener;
    }

    private LinkageLeftListView leftListView;
    private LinkageRightListView rightListView;
    private TextView rightNoDataView;

    private List<LinkageModel> leftModelList = new ArrayList<>();
    private List<LinkageModel> rightModelList = new ArrayList<>();
    private List<LinkageModel> modelList = new ArrayList<>();

    public LinkageListViewPresenter(Context context, LinkageListView linkageListView) {
        init(context, linkageListView);
    }

    private void init(Context context, LinkageListView linkageListView) {

        View root = View.inflate(context, R.layout.view_common_linkage_listview, linkageListView);

        mConstraintLayout = root.findViewById(R.id.mConstraintLayout);

        leftListView = root.findViewById(R.id.leftListView);
        rightListView = root.findViewById(R.id.rightListView);
        rightNoDataView = root.findViewById(R.id.rightNoDataView);

        leftListView.setLinkageListViewListener(this::onLeftItemClick);
        rightListView.setLinkageListViewListener(this::onRightItemClick);

        rightListView.startScrollListener();
    }

    // --------- 对外方法 begin ---------
    @Override
    public void setLinkageColorUtil(LinkageColorUtil linkageColorUtil) {
        leftListView.setLinkageColorUtil(linkageColorUtil);
        rightListView.setLinkageColorUtil(linkageColorUtil);
    }

    @Override
    public void setLinkageData(List<LinkageModel> modelList) {
        setLinkageData(null, null, modelList);
    }

    @Override
    public void setLinkageData(LinkageListViewBaseAdapter leftBaseAdapter, LinkageListViewBaseAdapter rightBaseAdapter, List<LinkageModel> modelList) {
        this.modelList = modelList;

        leftModelList.clear();
        rightModelList.clear();

        for (LinkageModel model : modelList) {
            switch (model.getLeftOrRight()) {
                case LINKAGE_LEFT:
                    leftModelList.add(model);
                    break;
                case LINKAGE_RIGHT:
                    rightModelList.add(model);
                    break;
            }
        }

        if (leftModelList.size() == 0) {
            leftListView.setVisibility(GONE);
            rightListView.setVisibility(GONE);
            rightNoDataView.setVisibility(VISIBLE);
        } else {
            if (leftBaseAdapter == null) {
                leftListView.setDataList(leftModelList);
            } else {
                leftListView.setDataList(leftBaseAdapter, leftModelList);
            }
            leftListView.setVisibility(VISIBLE);
            if (rightModelList.size() == 0) {
                rightListView.setVisibility(View.INVISIBLE);
                rightNoDataView.setVisibility(VISIBLE);
            } else {
                if (rightBaseAdapter == null) {
                    rightListView.setDataList(rightModelList);
                } else {
                    rightListView.setDataList(rightBaseAdapter, rightModelList);
                }
                rightListView.setVisibility(VISIBLE);
                rightNoDataView.setVisibility(GONE);
            }
        }
    }

    @Override
    public void setDefaultItem(int leftIndex, int rightIndex) {

        if (leftIndex < 0 || leftIndex > leftModelList.size()) {
            throw new IllegalStateException("LinkageListView -> setDefaultItem -> leftIndex invalid!");
        }

        if (rightIndex < 0 || rightIndex > rightModelList.size()) {
            throw new IllegalStateException("LinkageListView -> setDefaultItem -> rightIndex invalid!");
        }

        leftListView.setDefaultSelectItem(leftIndex);
        rightListView.setDefaultSelectItem(rightIndex);
    }

    @Override
    public void setLinkageLayoutWeight(float leftWeight, float rightWeight) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mConstraintLayout);
        constraintSet.setHorizontalWeight(leftListView.getId(), leftWeight);
        constraintSet.setHorizontalWeight(rightListView.getId(), rightWeight);
        constraintSet.applyTo(mConstraintLayout);
    }

    @Override
    public void setLinkageLeftDivider(Drawable divider) {
        leftListView.setLinkageDivider(divider);
    }

    @Override
    public void setLinkageLeftDividerHeight(int height) {
        leftListView.setLinkageDividerHeight(height);
    }

    @Override
    public void hideLinkageLeftDivider() {
        leftListView.hideLinkageDivider();
    }

    @Override
    public void setLinkageRightDivider(Drawable divider) {
        rightListView.setLinkageDivider(divider);
    }

    @Override
    public void setLinkageRightDividerHeight(int height) {
        rightListView.setLinkageDividerHeight(height);
    }

    @Override
    public void hideLinkageRightDivider() {
        rightListView.hideLinkageDivider();
    }

    // --------- 对外方法 end ---------

    private void onLeftItemClick(int position) {
        onLeftItemClickLogin();
        int relationship = leftModelList.get(position).getRelationship();
        for (int i = 0; i < rightModelList.size(); i++) {
            if (relationship == rightModelList.get(i).getRelationship()) {
                rightListViewScrollToIndex(i);
                break;
            }
        }
    }

    private void onLeftItemClickLogin() {
        refreshView(LINKAGE_RIGHT, 0);
    }

    private void onRightItemClick(int position) {
        onRightClickLogic(position);
    }

    private void onRightClickLogic(int rightPosition) {
        refreshView(LINKAGE_LEFT, rightPosition);
        if (getLinkageListViewListener() != null) {
            getLinkageListViewListener().onLinkageItemClick(rightModelList.get(rightPosition));
        }
    }

    private void refreshView(String type, int rightPosition) {

        for (int i = 0; i < modelList.size(); i++) {
            LinkageModel model = modelList.get(i);
            if (model.getLeftOrRight().equals(type)) {
                if (model.getState() == TYPE_CHOICE_FOCUS) {
                    model.setState(TYPE_CHOICE_NO_FOCUS);
                }
            }
            if (type.equals(LINKAGE_LEFT)) {
                if (model.getLeftOrRight().equals(type)) {
                    int relationship = modelList.get(rightPosition).getRelationship();
                    if (modelList.get(i).getRelationship() == relationship) {
                        modelList.get(i).setState(TYPE_CHOICE_NO_FOCUS);
                    } else {
                        modelList.get(i).setState(TYPE_NO_CHOICE);
                    }
                }
            }
        }
        rightListView.notifyDataSetChanged();
        leftListView.notifyDataSetChanged();
    }

    private void rightListViewScrollToIndex(int index) {
        rightListView.setOnSmoothScrollToPositionFromTop(index);
    }
}
