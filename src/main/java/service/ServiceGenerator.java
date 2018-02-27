package service;

import java.text.DateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceGenerator {
    public enum ApiEndpoint {
        // vsi bitstamp api klici
        BITSTAMP("https://www.bitstamp.net/api/");

        public final String url;

        ApiEndpoint(String url) {
            this.url = url;
        }
    }

    private static Retrofit retrofit(ApiEndpoint endpoint) {
        OkHttpClient client = setUpOkHttpClient();
        return createRetrofitBuilder(endpoint.url).client(client).build();
    }

    private static Retrofit.Builder createRetrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(setUpJackson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public static <S> S createService(Class<S> serviceClass, ApiEndpoint endpoint) {
        return retrofit(endpoint).create(serviceClass);
    }

    private static ObjectMapper setUpJackson() {
        return new ObjectMapper()
                .setDateFormat(DateFormat.getDateInstance());
    }

    private static OkHttpClient setUpOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.build();
    }
}
