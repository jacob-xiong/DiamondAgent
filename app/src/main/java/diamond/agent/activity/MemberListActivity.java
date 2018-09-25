package diamond.agent.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.adapter.MemberListTabLayoutAdapter;
import diamond.agent.utils.ScreenUtils;

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
    protected boolean isShowActionBarTitle() {
        return true;
    }

    @Override
    protected int getActionTitle() {
        return R.string.diamond_agent_center_member_list_title;
    }

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
        reflex(mMemberTabLayout);
    }

    @Override
    protected void initIntent() {

    }

    public void reflex(final TabLayout tabLayout) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = ScreenUtils.dip2px(tabLayout.getContext(), 20);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0,  ScreenUtils.dip2px(tabLayout.getContext(), 1));

                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
