package diamond.agent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import diamond.agent.R;

/**
 * Created by Jacob on 2018-9-18.
 */

public class MemberListFragment extends BaseFragment {
    private static final String PAGE_INDEX = "page_index";
    private int mPageIndex;
    @BindView(R.id.member_group_list_view)
    LinearLayout mGroupListView;
    @BindView(R.id.member_list_vertical_line)
    LinearLayout mMemberListVerticalLine;

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

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(View view) {
        initView();
        prepareMemberListVerticalLine();
    }


    private void initView() {
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
}
