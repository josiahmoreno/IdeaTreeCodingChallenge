package lastdev.ideatest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import lastdev.ideatest.R;
import lastdev.ideatest.adapters.ResultsAdapter;
import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/21/2017.
 */

public class MainFragment extends Fragment implements MainContract.View{

    private android.support.v7.widget.Toolbar toolbar;
    private ListView listView;
    private ResultsAdapter listAdapter;
    private MainContract.Presenter presenter;
    private AppCompatButton refreshButton;


    //Returns a new Instance of MainFragment
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    private View mRootView;


    /*Create the ListAdapter so the data can be loaded into it later
      Instantiate the MainPresenter with refernce to the MainContract.View interface.
      The Fragment is setRetainInstance(true) to save the data/views or orientation change.
    */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        listAdapter = new ResultsAdapter(getContext(),new ArrayList<Results.Result>());
        MainPresenter mainPresenter = new MainPresenter(this);
    }




    /*Returns a newly inflated View or returns the older one.
     Now on orientation change, the api doesn't need to be queried again.
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("MainFrag","onCreateView");
        if(mRootView==null){

            mRootView = inflater.inflate(R.layout.list_frag_main, container, false);
            toolbar = (android.support.v7.widget.Toolbar) mRootView.findViewById(R.id.toolbar);
            listView = (ListView) mRootView.findViewById(R.id.list_view);
            listView.setClickable(true);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //    Log.e("MainFrag"," onitemClick|"+position);
                    Results.Result result = (Results.Result) parent.getItemAtPosition(position);
                    presenter.onResultClick(result);
                    //  Log.e("MainFrag",result.getTrackCensoredName());
                }
            });

            listView.setAdapter(listAdapter);
            refreshButton =(AppCompatButton) mRootView.findViewById(R.id.try_again_button);
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onRefreshClick();
                }
            });

            presenter.start();
        }





        return mRootView;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Log.e("MainFrag","onViewCreated");

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter  = presenter;
    }

    //Load the Results object in the adapter.
    @Override
    public void loadResults(Results results) {
        listAdapter.loadResults(results);
    }

    //Starts the DetailActivity, with the details of the song serialized into a json string.
    @Override
    public void showResultDetails(Results.Result result) {
        Gson gson = new Gson();
        Intent intent = new Intent(getContext(),DetailActivity.class);

        intent.putExtra(DetailActivity.TAG,gson.toJson(result));
        startActivity(intent);
    }


    //show/hide the refresh button, when api is unable to queried
    @Override
    public void showEmptyList(){
        refreshButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyList(){
        refreshButton.setVisibility(View.GONE);
    }


    //unsubscribe from a running network call
    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.unSubscribe();
    }


}
