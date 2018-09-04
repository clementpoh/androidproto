package cc.poh.first.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.poh.first.R;
import cc.poh.first.databinding.FragmentMoreBinding;
import cc.poh.first.ui.scaffold.PrimaryFragment;
import cc.poh.first.ui.scaffold.SecondaryFragment;


public class MoreFragment extends Fragment {
    private FragmentMoreBinding mBinding;

    public MoreFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        // Inflate the layout for this fragment
        mBinding = FragmentMoreBinding.inflate(inflater, container, false);

        // Populate the tabs
        FragmentPagerAdapter adapter = new ProfileTabAdapter(getChildFragmentManager());
        mBinding.viewpager.setAdapter(adapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewpager);

        return mBinding.getRoot();
    }

    private class ProfileTabAdapter extends FragmentPagerAdapter {
        ProfileTabAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                case 0: return new SecondaryFragment();
                case 1: return new SecondaryFragment();
                case 2: return new PrimaryFragment();
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Context context = getContext();

            switch (position) {
                default:
                case 0: return context.getString(R.string.tab_messages);
                case 1: return context.getString(R.string.tab_schedule);
                case 2: return context.getString(R.string.tab_settings);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

}
