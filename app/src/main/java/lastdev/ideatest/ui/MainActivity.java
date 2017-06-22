package lastdev.ideatest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import lastdev.ideatest.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    @Override
    /**
     * Creates the MainFragment or finds the MainFragment by Tag;
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(TAG_RETAINED_FRAGMENT);
        Log.e("Main","MainFragment==null|"+(mainFragment == null));
        if (mainFragment == null) {
            // Create the fragment
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content,mainFragment, TAG_RETAINED_FRAGMENT).commit();
        }



    }


}
