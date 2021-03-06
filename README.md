# LinkageListView
[![](https://jitpack.io/v/LiuShuai1020/LinkageListView.svg)](https://jitpack.io/#LiuShuai1020/LinkageListView)
###### LinkageListView 是由左右两个ListView组成的一个组件，你可以点击左边的item改变右边ListView的数据，也可以直接滑动右边的数据来改变左边数据的状态，当然，你也可以禁止这个功能。同时你也可以根据需求来改变布局的样式。
### Add Libraries
###### Step 1. Add the JitPack repository to your build file
###### Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

###### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.LiuShuai1020:LinkageListView:v1.0.1'
	}


### Usage
###### 备注：LinkageListView 只认识 LinkageModel 数据，所以你要将你的数据类型转换成 LinkageListView 认识的 LinkageModel 类型。


###### Step 1. 在布局文件中使用：

	<com.liushiyu.linkagelistview.LinkageListView
            android:id="@+id/mLinkageListView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

###### Step 2. 在Activity中创建LinkageListView对象，并使用它：
	是否联动
    mLinkageListView.setLinkageScroll(false);

	使用默认适配器
	mLinkageListView.setLinkageData(List<LinkageModel> modelList)

	设置监听器
    mLinkageListView.setLinkageListener(LinkageListViewItemClick linkageListViewListener)

    设置默认项
    mLinkageListView.setDefaultItem(int leftIndex, int rightIndex)


###### Step 3. 拓展：
	设置样式工具类
	mLinkageListView.setLinkageColorUtil(LinkageColorUtil linkageColorUtil)

	自定义样式
	mLinkageListView.setLinkageData(LinkageListViewBaseAdapter leftBaseAdapter, LinkageListViewBaseAdapter rightBaseAdapter, List<LinkageModel> modelList)


### 重要：
1.LinkageListView 只认识 LinkageModel 类型的数据，所以你需要创建一个：

 	private List<LinkageModel> linkageRightModelList = new ArrayList<>();

2.然后讲你的数据进行处理：

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

3.如何使用它：

    mLinkageListView.setLinkageData(addLinkageListViewTestData());

4.LinkageModel说明：

	relationship：左右关联字段
	itemTitle： 需要显示的内容
	leftOrRight： 左侧还是右侧 （BaseModel.LINKAGE_LEFT，BaseModel.LINKAGE_RIGHT）

5.扩展（YouDataModel 未你自己可以自定义的bean）：

	LinkageModel<YouDataModel> rightLinkageModel = new LinkageModel<>();

	YouDataModel youDataModel = new YouDataModel();

	rightLinkageModel.setLinkageModelData(youDataModel);

6.监听：

	mLinkageListView.setLinkageListener(this::onLinkageItemClick);

	public void onLinkageItemClick(LinkageModel linkageModel) {

        Toast.makeText(getApplication(), linkageModel.getItemTitle(), Toast.LENGTH_LONG).show();

        或者

    YouDataModel youDataModel = linkageModel.getLinkageModelData();
        Toast.makeText(getApplication(), youDataModel.getUserName(), Toast.LENGTH_LONG).show();

    }

7.自定义布局：

	mLinkageListView.setLinkageData(new TestLeftAdapter(this),new TestRightAdapter(this), addLinkageListViewTestData());

自定义的adapter必须继承 LinkageListViewBaseAdapter\<T>，如：

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
    public void refreshLinkageViewState(TestLeftAdapterViewHolder viewHolder, String direction, int state, LinkageColorUtil linkageColorUtil) {
        switch (state) {
            case LinkageModel.TYPE_NO_CHOICE: // 没有焦点时的样式
                viewHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_LEFT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.mTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorNoChoice()));
                break;
            case LinkageModel.TYPE_CHOICE_FOCUS: // 当用户点击到item的时候的样式
                viewHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(linkageColorUtil.getChoiceBackgroundColor()));
                viewHolder.mTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceFocus()));
                break;
            case LinkageModel.TYPE_CHOICE_NO_FOCUS: // 当用户焦点离开时的样式：
                viewHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(direction.equals(LINKAGE_RIGHT) ? linkageColorUtil.getLeftBackgroundColor() : linkageColorUtil.getRightBackgroundColor()));
                viewHolder.mTextView.setTextColor(mContext.getResources().getColor(linkageColorUtil.getTextColorChoiceNoFocus()));
                break;        }
    }
    }


其中ViewHolder为：

    public class TestLeftAdapterViewHolder {
    public LinearLayout mLinearLayout;
    public TextView mTextView;

    	public TestLeftAdapterViewHolder(View view) {
       	 mLinearLayout = view.findViewById(R.id.mLinearLayout);
        	 mTextView = view.findViewById(R.id.mTextView);
       }
	}

LinkageListView 方法

            // 设置样式工具类
            public void setLinkageColorUtil(LinkageColorUtil linkageColorUtil);
            // 使用默认适配器
            public void setLinkageData(List<LinkageModel> modelList);
            // 扩展自定义风格：设置联动数据
            public void setLinkageData(LinkageListViewBaseAdapter leftBaseAdapter, LinkageListViewBaseAdapter rightBaseAdapter, List<LinkageModel> modelList);
            // 设置默认项
            public void setDefaultItem(int leftIndex, int rightIndex);
            // 设置监听器
            public void setLinkageListener(LinkageListViewItemClick linkageListViewListener);
            // 设置两边 LinkageListView 的权重比
            public void setLinkageLayoutWeight(float leftWeight, float rightWeight);
            // 设置左边 Divider 线
            public void setLinkageLeftDivider(Drawable divider);
            // 设置左边item Divider 的高度
            public void setLinkageLeftDividerHeight(int height);
            // 隐藏 Left LinkageListView 的Divider
            public void hideLinkageLeftDivider();
            // 设置右边 Divider 线
            public void setLinkageRightDivider(Drawable divider);
            // 设置右边item Divider 的高度
            public void setLinkageRightDividerHeight(int height);
            // 隐藏 Right LinkageListView 的Divider
            public void hideLinkageRightDivider();
            // 设置没有数据时显示的View 代替"暂无数据"
            public abstract void setLinkageNullDataView(View view);
            // 获取左边选中的下标
            public int getChoiceLeftIndex() {}
            // 获取右边选中的下标
            public int getChoiceRightIndex() {}


