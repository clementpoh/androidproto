package cc.poh.first.ui.scaffold;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.poh.first.databinding.RecyclerListBinding;
import cc.poh.first.ui.home.PostAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrimaryFragment extends Fragment {
    private static final int NUM_COLUMNS = 2;

    private RecyclerListBinding mBinding;

    // Required empty public constructor
    public PrimaryFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = RecyclerListBinding.inflate(inflater, container, false);

        // Set the adapter
        StaggeredGridLayoutManager manager = new
                StaggeredGridLayoutManager(NUM_COLUMNS, StaggeredGridLayoutManager.VERTICAL);

        mBinding.list.setLayoutManager(manager);
        mBinding.list.setAdapter(new PostAdapter(null));

        return mBinding.getRoot();
    }
}
