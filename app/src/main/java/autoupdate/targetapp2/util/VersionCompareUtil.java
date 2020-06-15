package autoupdate.targetapp2.util;

import autoupdate.targetapp2.BuildConfig;

/**
 * This class implements IVersionCompareUtil interface.
 * @author Prabhat Sharma
 */
public class VersionCompareUtil implements IVersionCompareUtil{

    /**
     * This method returns whether the app is updatable or not
     * @param latestVersion the latestVersion code
     * @return boolean representing whether the app needs an update or not.
     */
    @Override
    public boolean isUpdateAvailable(String latestVersion) {
        int currVersion = BuildConfig.VERSION_CODE;
        int latestVer = Integer.parseInt(latestVersion);
        if(currVersion < latestVer)
            return true;
        return false;
    }

}
