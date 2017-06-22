package lastdev.ideatest.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**Sets the base url for the iTunes api queries.
 * * Simplifies the RetroFit Client instantiation.
 */

public class AppleUtils {
    public static final String BASE_URL = "https://itunes.apple.com/";

    public static AppleApi getAppleService() {

        return getClient(BASE_URL).create(AppleApi.class);
    }

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLenient();
            Gson gson = gsonBuilder.create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
