package at.htl.trainingproject;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import javax.inject.Inject;

import at.htl.trainingproject.ui.layout.MainView;
import at.htl.trainingproject.util.Config;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    public static final String TAG = MainActivity.class.getName();

    @Inject
    MainView mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Config.load(this);
        String baseUrl = Config.getProperty("json.placeholder.baseurl");
        if (baseUrl == null) {
            Log.e(TAG, "Base URL is not defined in the properties file.");
        } else {
            Log.i(TAG, "onCreate: Base URL is " + baseUrl);
        };*/
        mainView.buildContent(this);
    }
}
