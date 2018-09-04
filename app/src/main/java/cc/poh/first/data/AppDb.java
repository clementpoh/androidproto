package cc.poh.first.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import cc.poh.first.data.converter.DateConverter;
import cc.poh.first.data.converter.UriConverter;

/**
 * Created by cpoh on 21/3/2018.
 */

@TypeConverters({DateConverter.class, UriConverter.class})
@Database(entities = { PostEntity.class, UserEntity.class }, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    private static final String DB_NAME = "kalpha.db";
    private static AppDb sInstance;

    public abstract UserDao userDao();
    public abstract PostDao postDao();

    public static synchronized AppDb getInstance(Context context) {
        if (sInstance == null) sInstance = Room
                .databaseBuilder(context.getApplicationContext(), AppDb.class, DB_NAME)
                .build();
        return sInstance;
    }
}
