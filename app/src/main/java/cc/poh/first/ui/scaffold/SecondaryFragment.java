package cc.poh.first.ui.scaffold;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.poh.first.data.DummyContent;
import cc.poh.first.databinding.RecyclerListBinding;

/**
 * Created by cpoh on 4/3/2018.
 */

public class SecondaryFragment extends Fragment {
    private RecyclerListBinding mBinding;

    // Required empty public constructor
    public SecondaryFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = RecyclerListBinding.inflate(inflater, container, false);

        // Set the adapter
        mBinding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.list.setAdapter(new LinearAdapter(DummyContent.ITEMS));

        return mBinding.getRoot();
    }
}
