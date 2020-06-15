package autoupdate.targetapp2.util;

/**
 * This interface deals with comparing current version and latestVersion of app
 * Assuming that we might have a custom versioning system which may require different implementations.
 * Implementation against interface will provide flexibility of adding a new implementation
 * without changing the parts where the isUpdatAvailable method has been called.
 * @author Prabhat Sharma
 */
public interface IVersionCompareUtil {

    public boolean isUpdateAvailable(String latestVersion);
}
