package cc.poh.first.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import cc.poh.first.Constants;
import cc.poh.first.IntentHelper;
import cc.poh.first.R;

/**
 * Created by cpoh on 17/2/2018.
 */

public class PhotoDialogFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.photo_change)
                .setNegativeButton(R.string.action_cancel, null)
                .setItems(R.array.array_photo, (dialog, which) -> {
                    Intent intent = null;
                    switch (which) {
                        case Constants.PHOTO_TAKE:
                            intent = IntentHelper.takePhoto(getContext());
                            break;
                        case Constants.PHOTO_CHOOSE:
                            intent = IntentHelper.choosePhoto();
                            break;
                    }

                    IntentHelper.launchIntent(getActivity(), intent, which);
                });
        return builder.create();
    }
}
