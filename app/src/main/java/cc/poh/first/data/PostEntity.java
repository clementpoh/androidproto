package cc.poh.first.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

import java.util.Date;

/**
 * Created by cpoh on 21/3/2018.
 */

@Entity(
//    foreignKeys = {
//        @ForeignKey(entity = UserEntity.class,
//                parentColumns = "id",
//                childColumns = "userId",
//                onDelete = ForeignKey.CASCADE)
//    },
    indices = {
        @Index(value = "userId")
    }
)
public class PostEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public Uri photo;
    public String title;
    public String location;
    public int price;
    public int duration;
    public int difficulty;
    public String description;
    public Date expiry;
    public Date posted;

    @Ignore
    public PostEntity(String title) {
        this.title = title;
    }

    public PostEntity() { }
}
