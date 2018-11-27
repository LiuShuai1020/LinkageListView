package com.liushiyu.linkagelistview.utils;

import com.liushiyu.linkagelistview.R;

/**
 * created by liushuai on 2018/11/26
 */
public class LinkageColorUtil {

    // 左侧背景色
    private int leftBackgroundColor = R.color.color_linkage_list_view_background_left;
    // 右侧背景色
    private int rightBackgroundColor = R.color.color_linkage_list_view_background_right;
    // 选中的背景色
    private int choiceBackgroundColor = R.color.color_linkage_list_view_choice_background;
    // 没有选中状态下的文字颜色
    private int textColorNoChoice = R.color.color_linkage_list_view_text_color_no_choice;
    // 选中状态下并且有焦点时的文字颜色
    private int textColorChoiceFocus = R.color.color_linkage_list_view_text_color_choice_focus;
    // 选中状态下没有焦点时的文字颜色
    private int textColorChoiceNoFocus = R.color.color_linkage_list_view_text_color_choice_no_focus;

    public int getLeftBackgroundColor() {
        return leftBackgroundColor;
    }

    public void setLeftBackgroundColor(int leftBackgroundColor) {
        this.leftBackgroundColor = leftBackgroundColor;
    }

    public int getRightBackgroundColor() {
        return rightBackgroundColor;
    }

    public void setRightBackgroundColor(int rightBackgroundColor) {
        this.rightBackgroundColor = rightBackgroundColor;
    }

    public int getChoiceBackgroundColor() {
        return choiceBackgroundColor;
    }

    public void setChoiceBackgroundColor(int choiceBackgroundColor) {
        this.choiceBackgroundColor = choiceBackgroundColor;
    }

    public int getTextColorNoChoice() {
        return textColorNoChoice;
    }

    public void setTextColorNoChoice(int textColorNoChoice) {
        this.textColorNoChoice = textColorNoChoice;
    }

    public int getTextColorChoiceFocus() {
        return textColorChoiceFocus;
    }

    public void setTextColorChoiceFocus(int textColorChoiceFocus) {
        this.textColorChoiceFocus = textColorChoiceFocus;
    }

    public int getTextColorChoiceNoFocus() {
        return textColorChoiceNoFocus;
    }

    public void setTextColorChoiceNoFocus(int textColorChoiceNoFocus) {
        this.textColorChoiceNoFocus = textColorChoiceNoFocus;
    }
}
