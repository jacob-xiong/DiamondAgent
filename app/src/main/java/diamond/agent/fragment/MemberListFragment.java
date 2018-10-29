package diamond.agent.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.activity.MemberDetailActivity;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.LevelVO;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberItemData;
import diamond.agent.mvp.data.MemberLevelData;
import diamond.agent.mvp.data.MemberListData;
import diamond.agent.mvp.presenter.MemberListPresenter;
import diamond.agent.mvp.view.MemberListView;
import diamond.agent.utils.FastClickUtils;
import diamond.agent.utils.ScreenUtils;
import diamond.agent.utils.User;

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
    private MemberLevelData mMemberListData;
    private double allBuyMoney;
    private double allRebateMoney;

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
        presenter = new MemberListPresenter(this, getActivity());
    }

    @Override
    protected void loadData() {
        if (isVisibleToUser && !isGetDataSuccess && presenter != null) {
            presenter.getMemberListData(User.userId, getTypeByPageIndex(mPageIndex));
        }
    }

    @Override
    protected void initView(View view) {


    }

    private String getTypeByPageIndex(int mPageIndex) {
        String str = "";
        switch (mPageIndex) {
            case 0:
                str = "";
                break;
            case 1:
                str = "month";
                break;
            case 2:
                str = "day";
                break;
            default:
                break;
        }
        return str;
    }


    private void initView() {
        if (mMemberListData != null) {
            initAllMoney();
            mFirstConsumer.setText(getListTitle(5, getString(R.string.diamond_agent_consumer_first_consumer, mMemberListData.getLevel1().size() + "")));
            mSecondConsumer.setText(getListTitle(5, getString(R.string.diamond_agent_consumer_second_consumer, mMemberListData.getLevel2().size() + "")));
            mThreeConsumer.setText(getListTitle(5, getString(R.string.diamond_agent_consumer_three_consumer, mMemberListData.getLevel3().size() + "")));
            mAllMember.setText(getListTitle(4, getString(R.string.diamond_agent_consumer_all_member, (mMemberListData.getLevel1().size() + mMemberListData.getLevel2().size() + mMemberListData.getLevel3().size()) + "")));
            mMemberNumTotal.setText(getString(R.string.diamond_agent_center_person, (mMemberListData.getLevel1().size() + mMemberListData.getLevel2().size() + mMemberListData.getLevel3().size()) + ""));
            mMemberConsumpTionTotal.setText(getString(R.string.diamond_agent_center_amount, String.valueOf(allBuyMoney)));
            mMemberExtractTotal.setText(getString(R.string.diamond_agent_center_amount, String.valueOf(allRebateMoney)));
            prePareListView();
            prepareMemberListVerticalLine();
        }


    }

    private void prePareListView() {
        if (mGroupListView.getChildCount() == 3) {
            return;
        }

        for (int i = 0; i < 3; i++) {

            View groupView = LayoutInflater.from(getContext()).inflate(R.layout.member_group_list_item, mGroupListView, false);
            final View queryLevel = groupView.findViewById(R.id.query_detail);
            queryLevel.setTag(i);
            queryLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FastClickUtils.isNormalClick()) {
                        startDetailActivity(queryLevel.getTag());
                    }
                }
            });
            final int index = i;
            LinearLayout itemLinearLayout = (LinearLayout) groupView.findViewById(R.id.member_group_list_item_view);
            initItemView(getLevelList(index), itemLinearLayout, index);
            mGroupListView.addView(groupView);
        }

    }

    private List<LevelVO> getLevelList(int index) {
        switch (index) {
            case 0:
                return mMemberListData.getLevel1();
            case 1:
                return mMemberListData.getLevel2();
            case 2:
                return mMemberListData.getLevel3();
        }
        return new ArrayList<>();
    }

    private void initItemView(List<LevelVO> list, LinearLayout itemLinearLayout, int index) {
        double itemAllBuyMoney = 0.0;
        double itemAllRebateMoney = 0.0;
        int size = getFirstFourSize(list);
        for (int j = 0; j < size + 2; j++) {

            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.member_group_value_item, itemLinearLayout, false);
            TextView consumerInTotal = (TextView) itemView.findViewById(R.id.consumer_in_total);
            TextView consumerTitle = (TextView) itemView.findViewById(R.id.consumer_title);
            TextView consumerAlreadyUse = (TextView) itemView.findViewById(R.id.consumer_already_use);
            TextView consumerExtract = (TextView) itemView.findViewById(R.id.consumer_item_extract);

            if (j == 0) {
                itemView.setPadding(0, ScreenUtils.dip2px(getContext(), 8), 0, ScreenUtils.dip2px(getContext(), 5));
                consumerTitle.setText(getItemTitle(index));
            } else if (0 < j && j < list.size() + 1) {
                LevelVO itemData = list.get(j - 1);
                if (itemData == null) {
                    return;
                }
                itemView.setPadding(0, ScreenUtils.dip2px(getContext(), 5), 0, 0);
                consumerTitle.setText(itemData.getLevel1Name());
                consumerAlreadyUse.setText(getResources().getString(R.string.diamond_agent_center_amount, String.valueOf(itemData.getBuyMoney())));
                consumerExtract.setText(getResources().getString(R.string.diamond_agent_center_amount, String.valueOf(itemData.getRebateMoney())));
                itemAllBuyMoney = itemAllBuyMoney + itemData.getBuyMoney();
                itemAllRebateMoney = itemAllRebateMoney + itemData.getRebateMoney();


            } else if (j == list.size() + 1) {
                consumerInTotal.setVisibility(View.VISIBLE);
                consumerInTotal.setTextColor(Color.parseColor("#FF8C00"));
                consumerTitle.setTextColor(Color.parseColor("#FF8C00"));
                consumerAlreadyUse.setTextColor(Color.parseColor("#FF8C00"));
                consumerExtract.setTextColor(Color.parseColor("#FF8C00"));
                consumerTitle.setText(getResources().getString(R.string.diamond_agent_center_person, list.size() + ""));
                consumerAlreadyUse.setText(getResources().getString(R.string.diamond_agent_center_amount, String.valueOf(itemAllBuyMoney)));
                consumerExtract.setText(getResources().getString(R.string.diamond_agent_center_amount, String.valueOf(itemAllRebateMoney)));
                itemView.setPadding(0, ScreenUtils.dip2px(getContext(), 8), 0, ScreenUtils.dip2px(getContext(), 5));
            }

            itemLinearLayout.addView(itemView);


        }
    }

    private int getFirstFourSize(List<LevelVO> list) {
        int size = 0;
        if (list.size() <= 4) {
            size = list.size();
        } else {
            size = 4;
        }
        return size;
    }

    private void startDetailActivity(Object tag) {
        if (tag == null) {
            return;
        }

        int levelKey = (int) tag;
        List<LevelVO> list = getLevelList(levelKey);
        if (list != null && list.size() > 0) {
            Intent intent = new Intent(context, MemberDetailActivity.class);
            intent.putExtra(MemberDetailActivity.LEVEL_DATA, (Serializable) list);
            startActivity(intent);
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
        if (isVisibleToUser && isCreateView) {
            if (presenter == null) {
                presenter = new MemberListPresenter(this, getActivity());
            }
            presenter.getMemberListData(User.userId, getTypeByPageIndex(mPageIndex));

        }
    }

    @Override
    public void getMemberListSuccess(MemberLevelData resultData) {

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
    public void getMemberDetailListSuccess(MemberGroupData resultData) {

    }

    @Override
    public void getMemberDetailListFail(String msg) {

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

    public void initAllMoney() {
        for (LevelVO levelVO : mMemberListData.getLevel1()) {
            allBuyMoney = allBuyMoney + levelVO.getBuyMoney();
            allRebateMoney = allRebateMoney + levelVO.getRebateMoney();
        }
        for (LevelVO levelVO : mMemberListData.getLevel2()) {
            allBuyMoney = allBuyMoney + levelVO.getBuyMoney();
            allRebateMoney = allRebateMoney + levelVO.getRebateMoney();
        }
        for (LevelVO levelVO : mMemberListData.getLevel3()) {
            allBuyMoney = allBuyMoney + levelVO.getBuyMoney();
            allRebateMoney = allRebateMoney + levelVO.getRebateMoney();
        }
    }
}
