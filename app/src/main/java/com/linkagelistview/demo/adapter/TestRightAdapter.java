package com.linkagelistview.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkagelistview.demo.R;
import com.linkagelistview.demo.model.YouDataModel;
import com.linkagelistview.demo.viewholder.TestViewHolder;
import com.liushiyu.linkagelistview.adapter.LinkageListViewBaseAdapter;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.ArrayList;
import java.util.List;

import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_LEFT;
import static com.liushiyu.linkagelistview.model.BaseModel.LINKAGE_RIGHT;

/**
 * created by liushuai on 2018/11/26
 */
public class TestRightAdapter extends LinkageListViewBaseAdapter<TestViewHolder> {

    private Context mContext;
    private List<LinkageModel> dataList = new ArrayList<>();
    public TestRightAdapter(Context context) {
        super(context);
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public View getView(ViewGroup viewGroup) {
        return LayoutInflater.from(mContext).inflate(R.layout.adapter_test_right, viewGroup, false);
    }

    @Override
    public void getDataList(List<LinkageModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TestViewHolder getLinkageListViewViewHolder(View view) {
        return new TestViewHolder(view);
    }

    @Override
    public void refreshLinkageViewData(int i, View view, ViewGroup viewGroup, TestViewHolder viewHolder, String direction) {
        viewHolder.text1.setText(dataList.get(i).getItemTitle());
        YouDataModel youDataModel = (YouDataModel) dataList.get(i).getLinkageModelData();
        viewHolder.text2.setText(youDataModel.getUserName());
        viewHolder.text2.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshLinkageViewState(TestViewHolder viewHolder, String direction, int state, LinkageColorUtil linkageColorUtil) {
        switch (state) {
            case LinkageModel.TYPE_NO_CHOICE:
                viewHolder.layout.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_LEFT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.text1.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorNoChoice()));
                viewHolder.text2.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorNoChoice()));
                break;
            case LinkageModel.TYPE_CHOICE_FOCUS:
                viewHolder.layout.setBackgroundColor(mContext.getResources().getColor(linkageColorUtil.getChoiceBackgroundColor()));
                viewHolder.text1.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceFocus()));
                viewHolder.text2.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceFocus()));
                break;
            case LinkageModel.TYPE_CHOICE_NO_FOCUS:
                viewHolder.layout.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_RIGHT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.text1.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceNoFocus()));
                viewHolder.text2.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceNoFocus()));
                break;
        }
    }
}
