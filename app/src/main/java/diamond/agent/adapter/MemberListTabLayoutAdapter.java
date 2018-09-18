package diamond.agent.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import diamond.agent.activity.MemberListFragment;

/**
 * Created by Jacob on 2018-9-18.
 */

public class MemberListTabLayoutAdapter extends FragmentPagerAdapter {
    private String[] mTableTitleArray;

    public MemberListTabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitle(String[] array) {
        mTableTitleArray = array;
    }

    @Override
    public Fragment getItem(int position) {
        return MemberListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTableTitleArray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTableTitleArray[position];
    }
}
