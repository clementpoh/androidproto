package cc.poh.first.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import cc.poh.first.App;
import cc.poh.first.data.PostEntity;

/**
 * Created by cpoh on 22/3/2018.
 */

public class PostListViewModel extends AndroidViewModel {
    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<PostEntity>> mPosts;

    public PostListViewModel(Application app) {
        super(app);

        mPosts = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        mPosts.setValue(null);

        LiveData<List<PostEntity>> posts = ((App) app).getPostRepository().getPosts();
        // observe the changes of the posts from the database and forward them
        mPosts.addSource(posts, mPosts::setValue);
    }

    public LiveData<List<PostEntity>> getPosts() {
        return mPosts;
    }

}
