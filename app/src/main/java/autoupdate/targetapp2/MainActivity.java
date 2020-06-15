package autoupdate.targetapp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

import autoupdate.targetapp2.config.UpdateConfigData;
import autoupdate.targetapp2.fragments.MandatoryDialog;
import autoupdate.targetapp2.fragments.OptionalDialog;
import autoupdate.targetapp2.singleton.SharedPrefSingleton;
import autoupdate.targetapp2.util.IVersionCompareUtil;
import autoupdate.targetapp2.util.VersionCompareUtil;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MAIN ACTIVITY";

    private TextView textView;
    private Context context;
    private IVersionCompareUtil versionCompareUtil;
    private MandatoryDialog mandatoryDialog;
    private OptionalDialog optionalDialog;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.dummy);
        versionCompareUtil = new VersionCompareUtil();
        context = getApplicationContext();

        textView.setText("Current version is : " + Integer.toString(BuildConfig.VERSION_CODE));
        filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + File.separator + UpdateConfigData.APK_FILE_NAME;
    }

    @Override
    public void onResume(){
        super.onResume();
        try {
            SharedPreferences sharedPref = SharedPrefSingleton.getInstance(context);
            String latestVersion = sharedPref.getString(UpdateConfigData.VERSION_KEY,
                    String.valueOf(BuildConfig.VERSION_CODE));
            String isMandatory = sharedPref.getString(UpdateConfigData.MANDATORY_KEY, "false");
            filePath = sharedPref.getString(UpdateConfigData.FILE_PATH, filePath);
            boolean isUpdateAvailable = versionCompareUtil.isUpdateAvailable(latestVersion);
            Log.i(TAG, "Is update available : " + isUpdateAvailable);

            if(true){
                mandatoryDialog = new MandatoryDialog();
                mandatoryDialog.setCancelable(false);
                mandatoryDialog.show(getSupportFragmentManager(),"MandatoryDialog");
            }
            else if(isUpdateAvailable && isMandatory.equals("false")){
                optionalDialog = new OptionalDialog();
                optionalDialog.show(getSupportFragmentManager(), "OptionalDialog");
            }

        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * This method initiates PackageInstaller activity to install update
     * It is called from the dialog fragment
     */
    public void startInstall(){
        try {
            File mfile = new File(filePath);
            Intent intent;
            Uri apkURI;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                apkURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", mfile);
                intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                intent.setData(apkURI);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                apkURI = Uri.fromFile(mfile);
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(apkURI, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            startActivity(intent);
            Log.i(TAG, "PackageInstaller called for updating app");
        }catch (Exception e){
            Log.e(TAG, "err installing update : " + e.getMessage());
        }
    }
}
