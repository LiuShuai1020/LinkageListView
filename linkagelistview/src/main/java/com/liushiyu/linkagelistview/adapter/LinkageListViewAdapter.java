package com.liushiyu.linkagelistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liushiyu.linkagelistview.R;
import com.liushiyu.linkagelistview.adapter.viewholder.LinkageListViewViewHolder;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.ArrayList;
import java.util.List;

import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_LEFT;
import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_RIGHT;

/**
 * created by liushuai on 2018/11/26
 */
public class LinkageListViewAdapter extends LinkageListViewBaseAdapter<LinkageListViewViewHolder> {

    private Context mContext;
    private List<LinkageModel> dataList = new ArrayList<>();

    public LinkageListViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public View getView(ViewGroup viewGroup) {
        return LayoutInflater.from(mContext).inflate(R.layout.view_common_linkage_listview_right_item, viewGroup, false);
    }

    @Override
    public void getDataList(List<LinkageModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public LinkageListViewViewHolder getLinkageListViewViewHolder(View view) {
        return new LinkageListViewViewHolder(view);
    }

    @Override
    public void refreshLinkageViewData(int i, View view, ViewGroup viewGroup, LinkageListViewViewHolder viewHolder, String direction) {
        viewHolder.linkageListViewRightItemTextView.setText(dataList.get(i).getItemTitle());
    }

    @Override
    public void refreshLinkageViewState(int p, LinkageListViewViewHolder viewHolder, String direction, int state, LinkageColorUtil linkageColorUtil) {
        switch (state) {
            case LinkageModel.TYPE_NO_CHOICE:
                viewHolder.linkageListViewRightItem.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_LEFT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.linkageListViewRightItemTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorNoChoice()));
                break;
            case LinkageModel.TYPE_CHOICE_FOCUS:
                viewHolder.linkageListViewRightItem.setBackgroundColor(mContext.getResources().getColor(linkageColorUtil.getChoiceBackgroundColor()));
                viewHolder.linkageListViewRightItemTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceFocus()));
                break;
            case LinkageModel.TYPE_CHOICE_NO_FOCUS:
                viewHolder.linkageListViewRightItem.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_RIGHT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.linkageListViewRightItemTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceNoFocus()));
                break;
        }
    }
}
