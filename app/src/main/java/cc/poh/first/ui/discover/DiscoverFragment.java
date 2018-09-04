package cc.poh.first.ui.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.poh.first.data.DummyContent;
import cc.poh.first.databinding.FragmentDiscoverBinding;

/**
 * Created by cpoh on 5/3/2018.
 */

public class DiscoverFragment extends Fragment {
    private static final int NUM_COLUMNS = 2;

    private FragmentDiscoverBinding mBinding;

    // Required empty public constructor
    public DiscoverFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentDiscoverBinding.inflate(inflater, container, false);

        // Set the adapter
        mBinding.recycler.list.setLayoutManager(new GridLayoutManager(getContext(), NUM_COLUMNS));
        mBinding.recycler.list.setAdapter(new CategoryAdapter(DummyContent.ITEMS));

        return mBinding.getRoot();
    }
}
