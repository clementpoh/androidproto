package cc.poh.first.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import cc.poh.first.App;
import cc.poh.first.PostRepository;
import cc.poh.first.data.PostEntity;

public class PostViewModel extends AndroidViewModel {
    private final LiveData<PostEntity> mPost;

    public PostViewModel(Application app, PostRepository postRepo, int postId) {
        super(app);

        mPost = postRepo.getPost(postId);
    }

    public LiveData<PostEntity> getPost() {
        return mPost;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application mApplication;
        private final int mPostId;
        private final PostRepository mPostRepo;

        public Factory(Application app, int productId) {
            mApplication = app;
            mPostId = productId;
            mPostRepo = ((App) app).getPostRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PostViewModel(mApplication, mPostRepo, mPostId);
        }
    }
}
