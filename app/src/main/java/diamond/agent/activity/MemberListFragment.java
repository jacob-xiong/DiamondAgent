package diamond.agent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import diamond.agent.R;

/**
 * Created by Jacob on 2018-9-18.
 */

public class MemberListFragment extends Fragment {
    private static final String PAGE_INDEX = "page_index";
    private int mPageIndex;
    @BindView(R.id.member_group_list_view)
    LinearLayout mGroupListView;

    public static Fragment newInstance(int pageIndex) {
        MemberListFragment fragment = new MemberListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_INDEX, pageIndex);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member_list_fragment, container, false);
        mPageIndex = getArguments().getInt(PAGE_INDEX);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
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
}
