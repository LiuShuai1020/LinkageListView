package com.liushiyu.linkagelistview.presenter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liushiyu.linkagelistview.R;
import com.liushiyu.linkagelistview.view.LinkageNullDataView;

/**
 * created by liushuai on 2018/12/7
 */
public class LinkageNullDataViewPresenter {

    private Context context;
    private LinkageNullDataView linkageNullDataView;

    public LinkageNullDataViewPresenter(LinkageNullDataView linkageNullDataView) {
        this.linkageNullDataView = linkageNullDataView;
        context = linkageNullDataView.getContext();
        init();
    }

    private void init() {
        linkageNullDataView.addView(getNullTextView());
    }

    private TextView getNullTextView() {
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(0, 0);
        textViewParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        textViewParams.height = LinearLayout.LayoutParams.MATCH_PARENT;

        TextView textView = new TextView(linkageNullDataView.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(context.getResources().getText(R.string.no_data));
        textView.setLayoutParams(textViewParams);
        return textView;
    }

    public void setLinkageNullDataView(View view) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,0);
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);

        linkageNullDataView.removeAllViews();
        linkageNullDataView.addView(view);
    }
}
