package lastdev.ideatest.ui;

import lastdev.ideatest.BasePresenter;
import lastdev.ideatest.BaseView;
import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/21/2017.
 */

public interface MainContract {

    public interface View extends BaseView<Presenter>{

        void loadResults(Results results);

        void showResultDetails(Results.Result result);

        void showEmptyList();

        void hideEmptyList();
    }
    public interface Presenter extends BasePresenter{

        void start();

        void unSubscribe();

        void onResultClick(Results.Result item);

        void onRefreshClick();
    }
}
