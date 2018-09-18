package diamond.agent.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.adapter.MemberListTabLayoutAdapter;

/**
 * @author by xiongyan on 2018/9/17.
 */
public class MemberListActivity extends BaseActivity {
    @BindView(R.id.member_list_tab_layout)
    TabLayout mMemberTabLayout;
    @BindView(R.id.member_list_vp)
    ViewPager mMemberViewPager;
    private MemberListTabLayoutAdapter memberListTabLayoutAdapter;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.member_list_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        memberListTabLayoutAdapter = new MemberListTabLayoutAdapter(getSupportFragmentManager());
        memberListTabLayoutAdapter.setTitle(getResources().getStringArray(R.array.member_tab_title));
        mMemberViewPager.setAdapter(memberListTabLayoutAdapter);
        mMemberTabLayout.setupWithViewPager(mMemberViewPager);
    }

    @Override
    protected void initIntent() {

    }
}
