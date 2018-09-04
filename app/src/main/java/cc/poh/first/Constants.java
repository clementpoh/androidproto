package cc.poh.first;

import android.content.Context;

/**
 * Created by cpoh on 19/2/2018.
 */

public abstract class Constants {
    public static final int PHOTO_TAKE = 0;
    public static final int PHOTO_CHOOSE = 1;
    public static final int PHOTO_EDIT = 101;
    public static final int PHOTO_REMOVE = 2;


    public static class Authority {
        public static final String FILE_PROVIDER = "cc.poh.first.fileprovider";
    }

    public static class Error {
        public static final String CREATE_FILE = "Error creating file";
    }
}
