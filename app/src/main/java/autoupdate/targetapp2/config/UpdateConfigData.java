package autoupdate.targetapp2.config;

public class UpdateConfigData {
    public static final String UPDATE_INTENT = "UPDATE_EVENT";

    public static final String OPTIONAL_UPDATE_MSG = "An optional update available";

    public static final String MANDATORY_UPDATE_MSG = "Install the update to proceed with the app";

    public static final String PREF_FILE_NAME = "funapp.autoupdate.DATA";

    public static final String APK_FILE_NAME = "targetapp2.apk";

    //keys in sharedPrefs
    public static final String VERSION_KEY = "AvailableVersion";
    public static final String MANDATORY_KEY = "IsUpdateMandatory";
    public static final String FILE_PATH = "FilePath";
}
