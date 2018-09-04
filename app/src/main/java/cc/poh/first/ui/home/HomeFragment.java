package cc.poh.first.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.poh.first.R;
import cc.poh.first.databinding.FragmentHomeBinding;
import cc.poh.first.ui.scaffold.SecondaryFragment;
import cc.poh.first.ui.utils.FabBehavior;

/**
 * A fragment representing a home fragment
 */
public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;

    public HomeFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);

        initialiseTabs();

        return mBinding.getRoot();
    }

    private void initialiseTabs() {
        // Populate the tabs
        FragmentPagerAdapter adapter = new HomeTabAdapter(getChildFragmentManager());
        mBinding.viewpager.setAdapter(adapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewpager);
    }

    private class HomeTabAdapter extends FragmentPagerAdapter {
        HomeTabAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                case 0: return new PostListFragment();
                case 1: return new SecondaryFragment();
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                default:
                case 0: return getContext().getString(R.string.tab_primary);
                case 1: return getContext().getString(R.string.tab_secondary);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
