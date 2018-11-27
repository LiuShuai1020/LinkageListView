package com.linkagelistview.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.linkagelistview.demo.adapter.TestLeftAdapter;
import com.linkagelistview.demo.adapter.TestRightAdapter;
import com.linkagelistview.demo.model.YouDataModel;
import com.liushiyu.linkagelistview.LinkageListView;
import com.liushiyu.linkagelistview.model.BaseModel;
import com.liushiyu.linkagelistview.model.LinkageModel;
import com.liushiyu.linkagelistview.utils.LinkageColorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by liushuai on 2018/11/22
 */
public class MainActivity extends Activity {
    @BindView(R.id.mLinkageListView)
    LinkageListView mLinkageListView;

    private List<LinkageModel> linkageRightModelList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        // 使用默认适配器
        mLinkageListView.setLinkageData(addLinkageListViewTestData());

        // 设置监听器
        mLinkageListView.setLinkageListener(this::onLinkageItemClick);

        // 设置默认项
        mLinkageListView.setDefaultItem(0, 0);

        // 扩展样式：设置样式工具类
//        mLinkageListView.setLinkageColorUtil(getLinkageColorUtil());

        // 扩展自定义风格：设置联动数据
//        mLinkageListView.setLinkageData(new TestLeftAdapter(this),new TestRightAdapter(this), addLinkageListViewTestData());
    }

    private List<LinkageModel> addLinkageListViewTestData() {
        linkageRightModelList.clear();

        List<LinkageModel> linkageBaseModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            LinkageModel linkageModel = new LinkageModel();
            linkageModel.setRelationship(i);
            linkageModel.setItemTitle("左边" + i);
            linkageModel.setLeftOrRight(BaseModel.LINKAGE_LEFT);
            linkageBaseModelList.add(linkageModel);

            for (int j = 0; j < 10; j++) {

                LinkageModel<YouDataModel> rightLinkageModel = new LinkageModel<>();
                rightLinkageModel.setRelationship(i);
                rightLinkageModel.setItemTitle(i + "的数据右边是：" + j);
                rightLinkageModel.setLeftOrRight(BaseModel.LINKAGE_RIGHT);

                YouDataModel youDataModel = new YouDataModel();
                youDataModel.setUserName("我是小明,你点击了：" + "左边" + i + "的数据且右边是：" + j);
                rightLinkageModel.setLinkageModelData(youDataModel);

                linkageBaseModelList.add(rightLinkageModel);
                linkageRightModelList.add(rightLinkageModel);
            }
        }
        return linkageBaseModelList;
    }

    private LinkageColorUtil getLinkageColorUtil() {
        // 样式工具类
        LinkageColorUtil linkageColorUtil = new LinkageColorUtil();
        // 左侧背景色
        linkageColorUtil.setLeftBackgroundColor(R.color.test_color_linkage_list_view_background_left);
        // 右侧背景色
        linkageColorUtil.setRightBackgroundColor(R.color.test_color_linkage_list_view_background_right);
        // 选中的背景色
        linkageColorUtil.setChoiceBackgroundColor(R.color.test_color_linkage_list_view_choice_background);

        // 没有选中状态下的文字颜色
        linkageColorUtil.setTextColorNoChoice(R.color.test_color_linkage_list_view_text_color_no_choice);
        // 选中状态下并且有焦点时的文字颜色
        linkageColorUtil.setTextColorChoiceFocus(R.color.test_color_linkage_list_view_text_color_choice_focus);
        // 选中状态下没有焦点时的文字颜色
        linkageColorUtil.setTextColorChoiceNoFocus(R.color.test_color_linkage_list_view_text_color_choice_no_focus);
        return linkageColorUtil;
    }

    public void onLinkageItemClick(int position) {
        YouDataModel youDataModel = (YouDataModel) linkageRightModelList.get(position).getLinkageModelData();
        Toast.makeText(getApplication(), youDataModel.getUserName(), Toast.LENGTH_LONG).show();
    }
}
