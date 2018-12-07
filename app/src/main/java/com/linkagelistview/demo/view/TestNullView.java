package com.linkagelistview.demo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.linkagelistview.demo.R;

/**
 * created by liushuai on 2018/12/7
 */
public class TestNullView extends LinearLayout {

    public TestNullView(Context context) {
        super(context);
        init(context);
    }

    public TestNullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestNullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        View.inflate(context, R.layout.test_null_view, this);
    }
}
