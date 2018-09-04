package cc.poh.first;

import java.util.List;
import java.util.concurrent.Executor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import cc.poh.first.data.AppDb;
import cc.poh.first.data.PostDao;
import cc.poh.first.data.PostEntity;

/**
 * Created by cpoh on 22/3/2018.
 */

public class PostRepository {
    private static PostRepository sInstance;
    public static PostRepository getInstance(final AppDb db, final Executor diskIO) {
        if (sInstance == null) {
            synchronized (PostRepository.class) {
                if (sInstance == null) {
                    sInstance = new PostRepository(db, diskIO);
                }
            }
        }
        return sInstance;
    }

    private PostDao mPostDao;
    private MediatorLiveData<List<PostEntity>> mPosts;
    private Executor mDiskIO;

    private PostRepository(final AppDb db, final Executor diskIo) {
        mPostDao = db.postDao();
        mDiskIO = diskIo;

        mPosts = new MediatorLiveData<>();

        mPosts.addSource(mPostDao.loadAllPosts(), mPosts::postValue);
    }

    public LiveData<PostEntity> getPost(int postId) {
        MutableLiveData<PostEntity> data = new MutableLiveData<>();

//        PostEntity post = new PostEntity(0, 0, "title", "location", 1,
//                0, "description", new Date(), new Date());
//
//        data.setValue(post);
//        return data;
        return mPostDao.loadPost(postId);
    }

    public LiveData<List<PostEntity>> getPosts() {
        return mPostDao.loadAllPosts();
//        MutableLiveData<List<PostEntity>> data = new MutableLiveData<>();
//
//        ArrayList<PostEntity> posts = new ArrayList<>();
//        posts.add(new PostEntity("Hello"));
//        posts.add(new PostEntity("Hello World"));
//        posts.add(new PostEntity("Hello This"));
//        posts.add(new PostEntity("Hello is a"));
//        posts.add(new PostEntity("Hello thingy"));
//        posts.add(new PostEntity("Jump"));
//        posts.add(new PostEntity("Hello"));
//        posts.add(new PostEntity("Hello"));
//        posts.add(new PostEntity("Hello"));
//        posts.add(new PostEntity("Hello"));
//        posts.add(new PostEntity("Hello"));
//
//        data.setValue(posts);
//        return data;
    }

    public void savePost(PostEntity postEntity) {
        mDiskIO.execute(() -> mPostDao.insertPosts(postEntity));
    }
}
