package cc.poh.first;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by cpoh on 18/2/2018.
 */

public abstract class IntentHelper {
    static private final String TAG = "IntentHelper";

    public static Uri sPhotoUri = null;

    private IntentHelper() {}

    public static Intent takePhoto(Context context) {
        try  {
            File img = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg");
            sPhotoUri = FileProvider.getUriForFile(context, Constants.Authority.FILE_PROVIDER, img);

            return new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .putExtra(MediaStore.EXTRA_OUTPUT, sPhotoUri);

        } catch (IOException exception) {
            Log.e(TAG, Constants.Error.CREATE_FILE);
            exception.printStackTrace();
            return null;
        }
    }

    public static Intent choosePhoto() {
        int flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;

        return new Intent(Intent.ACTION_OPEN_DOCUMENT,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .addFlags(flags)
                .setType("image/*");
    }

    public static Intent editPhoto(Context context) {
        return editImage(context, sPhotoUri);
    }

    // https://stackoverflow.com/questions/43301098/android-action-edit-intent-to-edit-image
    public static Intent editImage(Context context, Uri imageUri) {

        int flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_READ_URI_PERMISSION;

        Intent editIntent = new Intent(Intent.ACTION_EDIT)
                .setDataAndType(imageUri, "image/*")
                .addFlags(flags)
                .putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        // This work-around allows the intent to access our private FileProvider storage.
        // http://stackoverflow.com/questions/24835364/android-open-private-file-with-third-party-app
        List<ResolveInfo> infoList = context.getPackageManager()
                .queryIntentActivities(editIntent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo info : infoList) {
            String packageName = info.activityInfo.packageName;
            context.grantUriPermission(packageName, imageUri, flags);
        }

        return editIntent;
    }

    public static void launchIntent(Activity activity, Intent intent, int requestCode) {
        if (intent == null) return;

        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            Toast.makeText(activity, R.string.error_unsupported, Toast.LENGTH_LONG).show();
        }
    }
}

