package com.linkagelistview.demo.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linkagelistview.demo.R;

/**
 * created by liushuai on 2018/11/27
 */
public class TestLeftAdapterViewHolder {
    public LinearLayout mLinearLayout;
    public TextView mTextView;

    public TestLeftAdapterViewHolder(View view) {
        mLinearLayout = view.findViewById(R.id.mLinearLayout);
        mTextView = view.findViewById(R.id.mTextView);
    }
}
