package cc.poh.first.ui.post;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import cc.poh.first.Constants;
import cc.poh.first.IntentHelper;
import cc.poh.first.App;
import cc.poh.first.PostRepository;
import cc.poh.first.R;
import cc.poh.first.data.PostEntity;
import cc.poh.first.databinding.ActivityEditPostBinding;
import cc.poh.first.ui.dialog.PhotoDialogFragment;

public class EditPostActivity extends AppCompatActivity {
    private static final String PHOTO_FRAGMENT_TAG = "choose_photo";

    ActivityEditPostBinding mBinding;

    View.OnClickListener mHeadListener =
            v -> new PhotoDialogFragment().show(getSupportFragmentManager(), PHOTO_FRAGMENT_TAG);

    View.OnClickListener mSubmitListener = v -> {
        App app = (App) getApplication();
        PostRepository postRepository = app.getPostRepository();

        PostEntity post = new PostEntity(mBinding.content.editTitle.getText().toString());
        post.photo = (Uri) mBinding.banner.getTag();
        post.price = mBinding.content.spinnerPrice.getSelectedItemPosition();
        post.location = mBinding.content.editLocation.getText().toString();
        post.duration = mBinding.content.spinnerDuration.getSelectedItemPosition();
        post.difficulty = mBinding.content.spinnerDifficulty.getSelectedItemPosition();
        post.description = mBinding.content.editDescription.getText().toString();

        postRepository.savePost(post);
        finish();
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_post);

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.toolbarLayout.setOnClickListener(mHeadListener);
        mBinding.content.fabSubmit.setOnClickListener(mSubmitListener);

        ArrayAdapter price = ArrayAdapter.createFromResource(this, R.array.array_prices, R.layout.spinner_item);
        price.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.content.spinnerPrice.setAdapter(price);

        ArrayAdapter duration = ArrayAdapter.createFromResource(this, R.array.array_durations, R.layout.spinner_item);
        duration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.content.spinnerDuration.setAdapter(duration);

        ArrayAdapter difficulty = ArrayAdapter.createFromResource(this, R.array.array_difficulty, R.layout.spinner_item);
        difficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.content.spinnerDifficulty.setAdapter(difficulty);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        if (data == null) return;

        switch (requestCode) {
            case Constants.PHOTO_REMOVE:
                mBinding.banner.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case Constants.PHOTO_TAKE:
                Intent edit = Intent.createChooser(IntentHelper.editPhoto(this), null);
                IntentHelper.launchIntent(this, edit, Constants.PHOTO_EDIT);
                break;
            case Constants.PHOTO_CHOOSE:
                mBinding.banner.setImageURI(data.getData());
                mBinding.banner.setTag(data.getData());
                break;
            case Constants.PHOTO_EDIT:
                mBinding.banner.setImageURI(IntentHelper.sPhotoUri);
                mBinding.banner.setTag(IntentHelper.sPhotoUri);
                break;
        }
    }
}
