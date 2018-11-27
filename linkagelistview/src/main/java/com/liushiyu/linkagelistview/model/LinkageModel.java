package com.liushiyu.linkagelistview.model;

/**
 * created by liushuai on 2018/11/21
 */
public class LinkageModel<T> extends BaseModel {
    private T linkageModelData;

    public T getLinkageModelData() {
        return linkageModelData;
    }

    public void setLinkageModelData(T linkageModelData) {
        this.linkageModelData = linkageModelData;
    }
}
