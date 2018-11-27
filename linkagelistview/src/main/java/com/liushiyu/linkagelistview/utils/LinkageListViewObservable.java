package com.liushiyu.linkagelistview.utils;

import java.util.Observable;

/**
 * created by liushuai on 2018/11/21
 */
public class LinkageListViewObservable extends Observable {

    private LinkageListViewObservable() {}

    private static LinkageListViewObservable linkageListViewObservable = null;

    public static LinkageListViewObservable getInstance() {
        if (linkageListViewObservable == null) {
            synchronized (LinkageListViewObservable.class) {
                if (linkageListViewObservable == null) {
                    linkageListViewObservable = new LinkageListViewObservable();
                }
            }
        }
        return linkageListViewObservable;
    }

    public void scrollToIndex(int index) {
        setChanged();
        notifyObservers(index);
    }
}