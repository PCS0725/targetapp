package autoupdate.targetapp2.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

import autoupdate.targetapp2.MainActivity;
import autoupdate.targetapp2.config.UpdateConfigData;

/**
 * Dialog fragment class for showing non-dismissible dialogs when update not mandatory
 * Proceed to install the update if 'Update' button is clicked, or exit the app if 'Exit App'
 * button is clicked.
 */
public class MandatoryDialog extends DialogFragment {
    private final String LOG_TAG = "DIALOG";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(UpdateConfigData.MANDATORY_UPDATE_MSG)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Activity main = getActivity();
                        Log.i(LOG_TAG,"Update clicked");
                        ((MainActivity) main).startInstall();
                    }
                })
                .setNegativeButton("Exit app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Exit the app
                        System.exit(0);
                    }
                });
        return builder.create();
    }

}
