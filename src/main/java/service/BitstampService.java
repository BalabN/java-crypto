package service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BitstampService {
    @Headers("Accept: application/json")
    @GET("neki/neki")
    Call<String> dejMiNeki(@Path("udid") String udid);
}
