package lastdev.ideatest.ui;

import lastdev.ideatest.BaseView;
import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/22/2017.
 */

public interface DetailContract  {

    interface View extends BaseView<Presenter> {

        void loadDetails(Results.Result result);

        void playSongFromUrl(String previewUrl);
    }
    interface Presenter {

        void start();

        void playSongButtonClicked();
    }
}
