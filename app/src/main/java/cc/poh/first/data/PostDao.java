package cc.poh.first.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by cpoh on 21/3/2018.
 */

@Dao
public interface PostDao {
    @Query("select * from postentity")
    LiveData<List<PostEntity>> loadAllPosts();

    @Query("select * from Postentity where id = :id")
    LiveData<PostEntity> loadPost(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(PostEntity... posts);

    @Update
    void updatePosts(PostEntity... posts);

    @Delete
    void deletePosts(PostEntity... posts);
}
