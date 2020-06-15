package autoupdate.targetapp2.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import autoupdate.targetapp2.MainActivity;
import autoupdate.targetapp2.config.UpdateConfigData;

/**
 * Dialog fragment class for showing optional dialogs when update is not mandatory
 */
public class OptionalDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(UpdateConfigData.OPTIONAL_UPDATE_MSG)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Activity main = (MainActivity)getActivity();
                        ((MainActivity) main).startInstall();
                    }
                })
                .setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

}
