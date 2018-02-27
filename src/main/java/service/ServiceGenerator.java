package service;

import java.text.DateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceGenerator {
    public enum ApiEndpoint {
        DEVICES("http://apps.outfit7.com/rest/admin/"),
        APPS("http://o7apps.appspot.com/rest/talkingFriends/"),
        WDA_SERVER("http://127.0.0.1");

        public final String url;

        ApiEndpoint(String url) {
            this.url = url;
        }
    }

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "talkingBoss";

    private static Retrofit retrofit(ApiEndpoint endpoint) {
        OkHttpClient client = setUpOkHttpClient(endpoint);
        return createRetrofitBuilder(endpoint.url).client(client).build();
    }

    private static Retrofit.Builder createRetrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(setUpJackson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public static <S> S createService(Class<S> serviceClass, ApiEndpoint endpoint) {
        return retrofit(endpoint).create(serviceClass);
    }

    private static Authenticator getBasicAuthenticator() {
        return (route, response) -> {
            String credential = Credentials.basic(USERNAME, PASSWORD);
            return response.request().newBuilder().header("Authorization", credential).build();
        };
    }

    private static Headers getJsonHeader(String authToken) {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Content-Type", "application/json");
        builder.add("Accept", "application/json");
        if (authToken != null && !authToken.isEmpty()) {
            builder.add("X-MY-Auth", authToken);
        }
        return builder.build();
    }

    private static ObjectMapper setUpJackson() {
        return new ObjectMapper()
                .setDateFormat(DateFormat.getDateInstance());
    }

    private static OkHttpClient setUpOkHttpClient(ApiEndpoint endpoint) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (endpoint == ApiEndpoint.DEVICES) {
            httpClient.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                                       .headers(getJsonHeader(Credentials.basic(USERNAME, PASSWORD)))
                                       .build();
                return chain.proceed(request);
            }).authenticator(getBasicAuthenticator());
        }

        return httpClient.build();
    }
}
