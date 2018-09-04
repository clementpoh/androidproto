package cc.poh.first.ui.home;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.poh.first.databinding.RecyclerListBinding;

import static android.content.ContentValues.TAG;
import static android.widget.GridLayout.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostListFragment extends Fragment {
    private static final int NUM_COLS = 2;

    private RecyclerListBinding mBinding;
    private PostAdapter mPostAdapter;
    private PostListViewModel mViewModel;
    private OnPostClick mHandler;

    private final OnPostClick mOnPostClick = postId -> {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) return;
        mHandler.onPostClick(postId);
    };

    // Required empty public constructor
    public PostListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = RecyclerListBinding.inflate(inflater, container, false);

        // Set the adapter
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(NUM_COLS, VERTICAL);
        mBinding.list.setLayoutManager(manager);

        mPostAdapter = new PostAdapter(mOnPostClick);
        mBinding.list.setAdapter(mPostAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        mViewModel.getPosts().observe(this, posts -> mPostAdapter.setValues(posts));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPostClick) {
            mHandler = (OnPostClick) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnPostClick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHandler = null;
    }
}
