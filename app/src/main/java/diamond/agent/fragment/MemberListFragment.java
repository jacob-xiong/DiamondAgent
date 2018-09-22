package diamond.agent.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberItemData;
import diamond.agent.mvp.data.MemberListData;
import diamond.agent.mvp.presenter.MemberListPresenter;
import diamond.agent.mvp.view.MemberListView;
import diamond.agent.utils.ScreenUtils;

/**
 * Created by Jacob on 2018-9-18.
 */

public class MemberListFragment extends BaseFragment<MemberListPresenter> implements MemberListView {
    private static final String PAGE_INDEX = "page_index";
    private int mPageIndex;
    @BindView(R.id.member_group_list_view)
    LinearLayout mGroupListView;
    @BindView(R.id.member_list_vertical_line)
    LinearLayout mMemberListVerticalLine;
    /**
     * 一级消费者
     */
    @BindView(R.id.first_consumer)
    TextView mFirstConsumer;

    /**
     * 二级消费者
     */
    @BindView(R.id.second_consumer)
    TextView mSecondConsumer;

    /**
     * 三级消费者
     */
    @BindView(R.id.three_consumer)
    TextView mThreeConsumer;


    /**
     * 全部成员
     */
    @BindView(R.id.all_member)
    TextView mAllMember;


    /**
     * 全部总计人数
     */
    @BindView(R.id.member_num_total)
    TextView mMemberNumTotal;

    /**
     * 全部消费金额
     */
    @BindView(R.id.member_consumption_total)
    TextView mMemberConsumpTionTotal;


    /**
     * 全部提成金额
     */
    @BindView(R.id.member_extract_total)
    TextView mMemberExtractTotal;

    private boolean isVisibleToUser = false;
    private boolean isCreateView = false;
    private boolean isGetDataSuccess = false;
    private MemberListData mMemberListData;

    public static Fragment newInstance(int pageIndex) {
        MemberListFragment fragment = new MemberListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_INDEX, pageIndex);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageIndex = getArguments().getInt(PAGE_INDEX);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.member_list_fragment;
    }

    @Override
    protected void initPresenter() {
        presenter = new MemberListPresenter(this, getContext());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(View view) {
        if (isVisibleToUser && !isGetDataSuccess && presenter != null) {
            presenter.getMemberListData("id");
        }

    }


    private void initView() {
        if (mMemberListData != null) {
            mFirstConsumer.setText(getListTitle(5, getString(R.string.diamond_agent_consumer_first_consumer, getDefaultZero(mMemberListData.getFirstMemberNum()))));
            mSecondConsumer.setText(getListTitle(5, getString(R.string.diamond_agent_consumer_second_consumer, getDefaultZero(mMemberListData.getSecondMemberNum()))));
            mThreeConsumer.setText(getListTitle(5, getString(R.string.diamond_agent_consumer_three_consumer, getDefaultZero(mMemberListData.getThreeMemberNum()))));
            mAllMember.setText(getListTitle(4, getString(R.string.diamond_agent_consumer_all_member, getDefaultZero(mMemberListData.getTotalNum()))));
            mMemberNumTotal.setText(getString(R.string.diamond_agent_center_person, getDefaultZero(mMemberListData.getTotalNum())));
            mMemberConsumpTionTotal.setText(getString(R.string.diamond_agent_center_amount, getDefaultZero(mMemberListData.getTotalConsumed())));
            mMemberExtractTotal.setText(getString(R.string.diamond_agent_center_amount, getDefaultZero(mMemberListData.getTotalCommission())));
            prePareListView();
            prepareMemberListVerticalLine();
        }


    }

    private void prePareListView() {
        if (mGroupListView.getChildCount() == 3) {
            return;
        }
        if (mMemberListData.getMemberLevelDataList() == null) {
            return;
        }
        for (int i = 0; i < mMemberListData.getMemberLevelDataList().size(); i++) {
            MemberGroupData groupData = mMemberListData.getMemberLevelDataList().get(i);
            if (groupData == null) {
                return;
            }
            if (groupData.getMemberLevelItemList() == null) {
                return;
            }
            View groupView = LayoutInflater.from(getContext()).inflate(R.layout.member_group_list_item, mGroupListView, false);
            LinearLayout itemLinearLayout = (LinearLayout) groupView.findViewById(R.id.member_group_list_item_view);
            for (int j = 0; j < groupData.getMemberLevelItemList().size() + 2; j++) {
                MemberItemData itemData = groupData.getMemberLevelItemList().get(j - 1);
                if (itemData == null) {
                    return;
                }
                View itemView = LayoutInflater.from(getContext()).inflate(R.layout.member_group_value_item, itemLinearLayout, false);
                TextView consumerInTotal = (TextView) itemView.findViewById(R.id.consumer_in_total);
                TextView consumerTitle = (TextView) itemView.findViewById(R.id.consumer_title);
                TextView consumerAlreadyUse = (TextView) itemView.findViewById(R.id.consumer_already_use);
                TextView consumerExtract = (TextView) itemView.findViewById(R.id.consumer_item_extract);
                if (j == 0) {
                    itemView.setPadding(0, 0, 0, ScreenUtils.dip2px(getContext(), 10));
                    consumerTitle.setText(getItemTitle(i));
                } else if (0 < j && j < groupData.getMemberLevelItemList().size() + 1) {
                    itemView.setPadding(0, 0, 0, 0);
                    consumerTitle.setText(itemData.getMemberId());
                    consumerAlreadyUse.setText(getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(itemData.getMemberConsumed())));
                    consumerExtract.setText(getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(itemData.getMemberCommission())));

                } else if (j == groupData.getMemberLevelItemList().size() + 1) {
                    consumerInTotal.setVisibility(View.VISIBLE);
                    consumerInTotal.setTextColor(Color.parseColor("#FF8C00"));
                    consumerTitle.setTextColor(Color.parseColor("#FF8C00"));
                    consumerAlreadyUse.setTextColor(Color.parseColor("#FF8C00"));
                    consumerExtract.setTextColor(Color.parseColor("#FF8C00"));
                    consumerTitle.setText(getResources().getString(R.string.diamond_agent_center_person, getDefaultZero(groupData.getMemberLevelAllNum())));
                    consumerAlreadyUse.setText(getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(groupData.getMemberLevelAllConsumed())));
                    consumerExtract.setText(getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(groupData.getGetMemberLevelAllNumCommission())));
                    itemView.setPadding(0, ScreenUtils.dip2px(getContext(), 10), 0, ScreenUtils.dip2px(getContext(), 5));
                }

                itemLinearLayout.addView(itemView);


            }
            mGroupListView.addView(groupView);
        }
    }

    private String getItemTitle(int i) {
        String title = "消费者";
        switch (i) {
            case 0:
                title = getResources().getString(R.string.diamond_agent_consumer_title_one);
                break;
            case 1:
                title = getResources().getString(R.string.diamond_agent_consumer_title_two);
                break;
            case 2:
                title = getResources().getString(R.string.diamond_agent_consumer_title_three);
                break;
            default:
                break;
        }
        return title;
    }

    private void prepareMemberListVerticalLine() {
        mGroupListView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mGroupListView.getViewTreeObserver().removeOnPreDrawListener(this);
                FrameLayout.LayoutParams imageParams = (FrameLayout.LayoutParams) mMemberListVerticalLine.getLayoutParams();
                if (mGroupListView.getHeight() > 0) {
                    imageParams.height = mGroupListView.getHeight();
                    mMemberListVerticalLine.setLayoutParams(imageParams);
                }
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCreateView = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && isCreateView && presenter != null) {
            presenter.getMemberListData("id");

        }
    }

    @Override
    public void getMemberListSuccess(MemberListData resultData) {

        isGetDataSuccess = resultData != null;
        if (resultData != null) {
            mMemberListData = resultData;
            initView();
        }
    }

    @Override
    public void getMemberListFail(String msg) {

    }

    @Override
    public void loadFailure(Throwable throwable) {

    }

    @Override
    public void onException(BaseResultData result) {

    }


    private String getDefaultZero(String str) {
        return TextUtils.isEmpty(str) ? getString(R.string.diamond_agent_center_zero) : str;
    }

    private SpannableStringBuilder getListTitle(int length, String str) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#999999"))), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#FF8C00"))), length + 1, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }
}
