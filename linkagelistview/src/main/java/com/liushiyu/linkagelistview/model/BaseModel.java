package com.liushiyu.linkagelistview.model;

/**
 * created by liushuai on 2018/11/26
 */
public class BaseModel {

    public static final String LINKAGE_LEFT = "left";
    public static final String LINKAGE_RIGHT = "right";

    public static final int TYPE_NO_CHOICE = 0;//白色背景+黑色文字
    public static final int TYPE_CHOICE_FOCUS = 1;//红色背景+白色文字
    public static final int TYPE_CHOICE_NO_FOCUS = 2;//白色背景+红色文字

    private int id;
    private int state;
    private int relationship;
    private String itemTitle;
    private String leftOrRight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getLeftOrRight() {
        return leftOrRight;
    }

    public void setLeftOrRight(String leftOrRight) {
        this.leftOrRight = leftOrRight;
    }
}
