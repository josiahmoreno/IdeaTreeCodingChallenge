package lastdev.ideatest.ui;

import android.util.Log;

import java.util.Iterator;

import lastdev.ideatest.model.Results;
import lastdev.ideatest.networking.AppleApi;
import lastdev.ideatest.networking.AppleUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Josiah on 6/21/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private  CompositeSubscription mSubscriptions;
    private  AppleApi appleApi;
    MainContract.View mainView;
    private String TAG = "MainPresenter";


    /*
    Instantiate the Retrofit client with the Apple api configured.
    Create the RxJava Subscription handler.
    Called after the Fragment's view inflation

     */
    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
         appleApi = AppleUtils.getAppleService();
        this.mainView.setPresenter(this);
        this.mSubscriptions = new CompositeSubscription();
        loadResults();
    }

    @Override
    public void start() {
        loadResults();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void onResultClick(Results.Result item) {
        mainView.showResultDetails(item);
    }

    @Override
    public void onRefreshClick() {
        loadResults();
    }


    /*
    *Retrieves the List of Results from iTunes.
    * Filter out the objects that are not songs.
    *
     */
    public void loadResults(){
        Subscription subscription = appleApi.getResults().subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .map(new Func1<Results, Results>() {
                    @Override
                    public Results call(Results results) {

                        Iterator<Results.Result> it = results.getResults().iterator();
                        while (it.hasNext()) {

                            Results.Result check = it.next();
                            if(!check.getKind().equals(Results.TYPE_SONG)) {

                                it.remove();
                            }
                        }

                        results.setResultCount(results.getResults().size());
                        return results;

                    }
                })
                .subscribe(new Action1<Results>() {
                    @Override
                    public void call(Results results) {
                        onResults(results);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onResultsError(throwable);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        Log.e(TAG,"getResults|onComplete");
                    }
                });

        mSubscriptions.add(subscription);
    }

    //Load the Results into the MainView(MainFragment). If the Results are empty, show the refresh button
    public void onResults(Results results){
        Log.e(TAG,"onResults|size="+results.getResultCount());
        if(results.getResultCount()>0) {
            mainView.hideEmptyList();
            mainView.loadResults(results);
        } else {
            mainView.showEmptyList();
        }


    }
    //Show the refresh button
    public void onResultsError(Throwable throwable){
        Log.e(TAG,"onResultsError|"+throwable.toString());
        Log.e(TAG,"onResultsError|"+throwable.getMessage());
        Log.e(TAG,"onResultsError|"+throwable.getLocalizedMessage());
        mainView.showEmptyList();
    }


}
