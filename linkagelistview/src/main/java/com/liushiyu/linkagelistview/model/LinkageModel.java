package com.liushiyu.linkagelistview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liushuai on 2018/11/21
 */
public class LinkageModel<T> extends BaseModel {
    private T linkageModelData;
    private List<LinkageRightModel> linkageRightModelList = new ArrayList<>();

    public T getLinkageModelData() {
        return linkageModelData;
    }

    public void setLinkageModelData(T linkageModelData) {
        this.linkageModelData = linkageModelData;
    }

    public List<LinkageRightModel> getLinkageRightModelList() {
        return linkageRightModelList;
    }

    public void setLinkageRightModelList(List<LinkageRightModel> linkageRightModelList) {
        this.linkageRightModelList = linkageRightModelList;
    }
}
