package lastdev.ideatest.ui;

import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/22/2017.
 */

public class DetailPresenter implements DetailContract.Presenter{
    DetailContract.View detailView;
    Results.Result result;



    //Sets the Presenter inside the DetailView
    public DetailPresenter(DetailContract.View detailView, Results.Result result) {
        this.detailView = detailView;
        this.result = result;
        detailView.setPresenter(this);

    }

    //Sets the text and image for the DetailView
    @Override
    public void start() {
        detailView.loadDetails(result);
    }

    //Starts the sample music
    @Override
    public void playSongButtonClicked(){
        detailView.playSongFromUrl(result.getPreviewUrl());
    }
}
