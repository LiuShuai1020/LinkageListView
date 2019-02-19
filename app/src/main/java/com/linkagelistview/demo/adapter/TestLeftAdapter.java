package com.linkagelistview.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkagelistview.demo.R;
import com.linkagelistview.demo.viewholder.TestLeftAdapterViewHolder;
import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.ArrayList;
import java.util.List;

import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_LEFT;
import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_RIGHT;

/**
 * created by liushuai on 2018/11/27
 */
public class TestLeftAdapter extends LinkageListViewBaseAdapter<TestLeftAdapterViewHolder> {

    private Context mContext;
    private List<LinkageModel> dataList = new ArrayList<>();

    public TestLeftAdapter(Context context) {
        super(context);
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public void getDataList(List<LinkageModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TestLeftAdapterViewHolder getLinkageListViewViewHolder(View view) {
        return new TestLeftAdapterViewHolder(view);
    }

    @Override
    public View getView(ViewGroup viewGroup) {
        return LayoutInflater.from(mContext).inflate(R.layout.adapter_test_left,viewGroup,false);
    }

    @Override
    public void refreshLinkageViewData(int position, View view, ViewGroup viewGroup, TestLeftAdapterViewHolder viewHolder, String direction) {
        viewHolder.mTextView.setText(dataList.get(position).getItemTitle());
    }

    @Override
    public void refreshLinkageViewState(int p, TestLeftAdapterViewHolder viewHolder, String direction, int state, LinkageColorUtil linkageColorUtil) {
        switch (state) {
            case LinkageModel.TYPE_NO_CHOICE:
                viewHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_LEFT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.mTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorNoChoice()));
                break;
            case LinkageModel.TYPE_CHOICE_FOCUS:
                viewHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(linkageColorUtil.getChoiceBackgroundColor()));
                viewHolder.mTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceFocus()));
                break;
            case LinkageModel.TYPE_CHOICE_NO_FOCUS:
                viewHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_RIGHT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.mTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceNoFocus()));
                break;
        }
    }
}
