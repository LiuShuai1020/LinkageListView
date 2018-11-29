package com.liushiyu.linkagelistview.adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liushiyu.linkagelistview.R;

/**
 * created by liushuai on 2018/11/26
 */
public class LinkageListViewViewHolder {
    public LinearLayout linkageListViewRightItem;
    public TextView linkageListViewRightItemTextView;

    public LinkageListViewViewHolder(View itemView) {
        linkageListViewRightItem = itemView.findViewById(R.id.linkageListViewRightItem);
        linkageListViewRightItemTextView = itemView.findViewById(R.id.linkageListViewRightItemTextView);
    }
}
