package lastdev.ideatest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import lastdev.ideatest.R;
import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/22/2017.
 */

public class DetailActivity  extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "DetailFragment";
    public static final String TAG = "DetailActivity";


    /**
     * Creates the DetailFragment or finds the DetailFragment by Tag;
     * Deserializes the StringExtra into the song result using Gson, to be used by the fragment
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag(TAG_RETAINED_FRAGMENT);
        Log.e("Detail","DetailFragment==null|"+(fragment == null));
        if (fragment == null) {
            // Create the fragment
            fragment = DetailFragment.newInstance();

            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment, TAG_RETAINED_FRAGMENT).commit();
        }

        Gson gson = new Gson();
        Results.Result result = gson.fromJson(getIntent().getStringExtra(TAG), Results.Result.class);
        DetailPresenter detailPresenter = new DetailPresenter(fragment,result);

    }




}
