package autoupdate.targetapp2.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import autoupdate.targetapp2.config.UpdateConfigData;
import autoupdate.targetapp2.singleton.SharedPrefSingleton;

/**
 *The broadcast receiver listening for broadcasts specifically targeted to this app.
 * @author Prabhat Sharma
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    /**
     * Callback method, gets called when an explicit intent is received.
     * It checks if intent is UPDATE_INTENT and then stores the values received from intent
     * int shared preferences
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(UpdateConfigData.UPDATE_INTENT)) {
            StringBuilder sb = new StringBuilder();

            String isMandatory = intent.getExtras().getString(UpdateConfigData.MANDATORY_KEY);
            String latestVersion = intent.getExtras().getString(UpdateConfigData.VERSION_KEY);
            String fileDir = intent.getExtras().getString(UpdateConfigData.FILE_PATH);
            //get data from the received intent here
            sb.append("Action: " + action + "\n");
            sb.append("Version : " + latestVersion + "\n");
            sb.append("Is mandatory : " + isMandatory + "\n");
            sb.append("File location : " + fileDir + "\n");
            String log = sb.toString();

            SharedPreferences sharedPref = SharedPrefSingleton.getInstance(context);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(UpdateConfigData.MANDATORY_KEY, isMandatory);
            editor.putString(UpdateConfigData.VERSION_KEY, latestVersion);
            editor.putString(UpdateConfigData.FILE_PATH, fileDir);
            editor.commit();

            Log.i(TAG, log);
            Toast.makeText(context, log, Toast.LENGTH_LONG).show();
        }
    }
}
