package cc.poh.first.ui.home;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.poh.first.data.PostEntity;
import cc.poh.first.databinding.FragmentPostBinding;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class PostFragment extends Fragment {
    private static final String KEY_POST_ID = "post_id";

    private int postId;
    private FragmentPostBinding mBinding;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param postId of the post to show.
     * @return A new instance of fragment PostFragment.
     */
    public static PostFragment forPost(int postId) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POST_ID, postId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Application app = getActivity().getApplication();
        int postId = getArguments().getInt(KEY_POST_ID);

        PostViewModel.Factory factory = new PostViewModel.Factory(app, postId);

        PostViewModel model = ViewModelProviders.of(this, factory).get(PostViewModel.class);
        model.getPost().observe(this, postEntity -> {
             mBinding.setPost(postEntity);
             mBinding.backdrop.setImageURI(postEntity.photo);
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null) return;
        postId = getArguments().getInt(KEY_POST_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentPostBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }
}
