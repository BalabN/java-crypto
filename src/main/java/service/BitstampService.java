package service;

import models.Transaction;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.List;

public interface BitstampService {
    @Headers("Accept: application/json")
    @GET("transactions/")
    Call<List<Transaction>> getTransactions();

    
}
