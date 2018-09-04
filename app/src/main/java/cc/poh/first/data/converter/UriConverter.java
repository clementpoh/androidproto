package cc.poh.first.data.converter;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

/**
 * Created by cpoh on 23/3/2018.
 */

public class UriConverter {
    @TypeConverter
    public static Uri toUri(String uri) {
        return uri == null ? null : Uri.parse(uri);
    }

    @TypeConverter
    public static String toString(Uri uri) {
        return uri == null ? null : uri.toString();
    }
}
