package lastdev.ideatest.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import lastdev.ideatest.R;
import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/22/2017.
 */

public class DetailFragment extends Fragment implements DetailContract.View {

    DetailContract.Presenter presenter;
    private TextView title;
    private TextView description;
    private Toolbar toolbar;
    private AppCompatImageView image;
    private String TAG = "DetailFragment";
    private TextView price;
    private TextView duration;
    private TextView genre;
    private TextView releaseDate;
    private TextView explicitness;
    private MediaPlayer myMediaPlayer;
    private FloatingActionButton fab;

    //Returns a new Instance of DetailFragment
    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    private View mRootView;

    /*
      Sets the Fragment to setRetainInstance(true) to keep the data/views or orientation change.
    */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    /*Returns a newly inflated View or returns the older one.
      Initializes the views.
    */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e(TAG,"onCreateView");
        if(mRootView==null){

            mRootView = inflater.inflate(R.layout.detail_frag_main, container, false);

            toolbar = (android.support.v7.widget.Toolbar) mRootView.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
            image = (AppCompatImageView) mRootView.findViewById(R.id.detail_image);
            title = (TextView) mRootView.findViewById(R.id.detail_title);
            description = (TextView) mRootView.findViewById(R.id.detail_description);
            price = (TextView) mRootView.findViewById(R.id.price_description);
            duration = (TextView) mRootView.findViewById(R.id.duration_description);
            genre = (TextView) mRootView.findViewById(R.id.genre_description);
            releaseDate = (TextView) mRootView.findViewById(R.id.release_date_description);
            explicitness = (TextView) mRootView.findViewById(R.id.explicitness_description);
            fab = (FloatingActionButton) mRootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.playSongButtonClicked();
                }
            });


            presenter.start();

        }





        return mRootView;
    }


    @Override
    public void setPresenter(DetailContract.Presenter basePresenter) {
        this.presenter = basePresenter;
    }

    /*Sets the TextViews with the details of the Song
      Loads a larger Album Image into the ImageView embedded in the CollapsibleToolbarLayout.
      Sets the duration of the song from milliseconds to hh:mm:ss format.
      Sets the release date of the song. Converted from ISO 8601. Really hard to do in Java 7!
      Sets Genre, Explicitness, and Price.
    */
    @Override
    public void loadDetails(Results.Result result) {
        title.setText(result.getTrackCensoredName());
        description.setText(result.getCollectionCensoredName());
        Picasso.with(getContext())
                .load(result.getArtworkUrl100().replace("100x100bb","600x600bb"))
                .fit()
                .centerCrop()
                .into(image);

        if(result.getTrackPrice()==Results.ALBUM_PURCHASE_ONLY){
            price.setText(getString(R.string.album_purchase_only));
        } else {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            price.setText(format.format(result.getTrackPrice()));
        }

        int millis = result.getTrackTimeMillis();
        String hms = String.format(getResources().getConfiguration().locale,"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        duration.setText(hms);
        genre.setText(result.getPrimaryGenreName());

        try {
            String dateStr = result.getReleaseDate();
            String s = dateStr.replace("Z", "+00:00");
            s = s.substring(0, 22) + s.substring(23);
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", getResources().getConfiguration().locale);
            releaseDate.setText(sdf.format(date));

        } catch (ParseException e){
            Log.e(TAG,"ParseException|"+e);
        }
        if(result.getTrackExplicitness().equals(getString(R.string.not_explicit_format))){
            explicitness.setText(getString(R.string.not_explicit));
        } else {
            explicitness.setText(getString(R.string.explicit));
        }






    }

    //Streams the Song sample.
    // Stops any playing samples before playing again.
    @Override
    public void playSongFromUrl(String url) {
        if (myMediaPlayer != null) {
            try {
                if (myMediaPlayer.isPlaying()) {
                    myMediaPlayer.stop();
                }
            } catch (IllegalStateException e){

            }
            myMediaPlayer.release();
        }
         myMediaPlayer = new MediaPlayer();
        myMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            myMediaPlayer.setDataSource(url);
            myMediaPlayer.prepareAsync(); // might take long! (for buffering, etc)

        } catch (IOException e) {
            e.printStackTrace();
        }

        myMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer player) {
                player.start();
            }

        });
    }

    //Stops and Releases the MediaPlayer onPause.
    // MediaPlayer needs to be released or the parent activity wont be able to be destroyed,
    @Override
    public void onPause() {
        if (myMediaPlayer != null) {
            try {
                if (myMediaPlayer.isPlaying()) {
                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                }
            } catch (IllegalStateException e){

            }

        }
        super.onPause();


    }
}
