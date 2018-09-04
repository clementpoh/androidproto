package cc.poh.first.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

import java.util.Date;
import java.util.List;

/**
 * Created by cpoh on 21/3/2018.
 */

@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Uri avatar;
    private String name;
    private String location;
    private String occupation;
    private Date dob;
    private Date registered;
    private Date lastSeen;

    public UserEntity(int id, Uri avatar, String name, String location, String occupation,
                      Date dob, Date registered, Date lastSeen) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.location = location;
        this.occupation = occupation;
        this.dob = dob;
        this.registered = registered;
        this.lastSeen = lastSeen;
    }


    public int getId() { return id; }
    public Uri getAvatar() { return avatar; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getOccupation() { return occupation; }
    public Date getDob() { return dob; }
    public Date getRegistered() { return registered; }
    public Date getLastSeen() { return lastSeen; }
}
