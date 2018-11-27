package com.linkagelistview.demo.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linkagelistview.demo.R;

/**
 * created by liushuai on 2018/11/26
 */
public class TestViewHolder {
    public LinearLayout layout;
    public TextView text1;
    public TextView text2;

    public TestViewHolder(View itemView) {
        text1 = itemView.findViewById(R.id.text1);
        text2 = itemView.findViewById(R.id.text2);
        layout = itemView.findViewById(R.id.layout);
    }
}
