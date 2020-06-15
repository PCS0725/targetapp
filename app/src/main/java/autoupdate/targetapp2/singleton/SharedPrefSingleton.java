package autoupdate.targetapp2.singleton;

import android.content.Context;
import android.content.SharedPreferences;

import autoupdate.targetapp2.config.UpdateConfigData;

/**
 * Singleton class for accessing shared preferences.
 * @author Prabhat Sharma
 */
public class SharedPrefSingleton {

    private static SharedPreferences sharedPref = null;

    private SharedPrefSingleton(Context context){}

    public static SharedPreferences getInstance(Context context){
        if(sharedPref == null) {
            sharedPref = context.getSharedPreferences(UpdateConfigData.PREF_FILE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPref;
    }
}
