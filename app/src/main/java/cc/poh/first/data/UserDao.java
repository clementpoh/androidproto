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
public interface UserDao {
    @Query("select * from userentity")
    LiveData<List<UserEntity>> loadAllUsers();

    @Query("select * from userentity where id = :id")
    LiveData<UserEntity> loadUser(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(UserEntity... users);

    @Update
    void updateUsers(UserEntity... users);

    @Delete
    void deleteUsers(UserEntity... users);
}
