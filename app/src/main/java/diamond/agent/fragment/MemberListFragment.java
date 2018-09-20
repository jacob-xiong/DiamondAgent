package diamond.agent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.MemberListData;
import diamond.agent.mvp.presenter.MemberListPresenter;
import diamond.agent.mvp.view.MemberListView;

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
    private boolean isVisibleToUser = false;
    private boolean isCreateView = false;

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
        if (isVisibleToUser) {
            initView();
            prepareMemberListVerticalLine();
        }

    }


    private void initView() {
        if (mGroupListView.getChildCount() == 3) {
            return;
        }
        System.out.println("=============1===========" + System.currentTimeMillis());
        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.member_group_list_item, mGroupListView, false);
            LinearLayout list_item_view = (LinearLayout) view.findViewById(R.id.member_group_list_item_view);
            for (int j = 0; j < 6; j++) {
                View itemView = LayoutInflater.from(getContext()).inflate(R.layout.member_group_value_item, list_item_view, false);
                if (j == 0) {
                    itemView.setPadding(0, 0, 0, 15);
                } else if (j == 5) {
                    itemView.setPadding(0, 15, 0, 5);
                } else {
                    itemView.setPadding(0, 0, 0, 0);
                }


                list_item_view.addView(itemView);


            }
            mGroupListView.addView(view);
        }
        System.out.println("==============2==========" + System.currentTimeMillis());

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
            initView();
            prepareMemberListVerticalLine();
        }
    }

    @Override
    public void getMemberListSuccess(MemberListData resultData) {

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
}
