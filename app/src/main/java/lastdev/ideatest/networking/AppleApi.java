package lastdev.ideatest.networking;

import lastdev.ideatest.model.Results;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Josiah on 6/21/2017.
 * SearchQuery for the iTunes Api.
 * Returns an RxJava Observable
 */

public interface AppleApi {

    @GET("search?term=Michael+jackson")
    Observable<Results> getResults();
}
