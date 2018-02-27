package service;

import java.util.List;

import models.Transaction;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BitstampService {
    @Headers("Accept: application/json")
    @GET("transactions/")
    Call<List<Transaction>> getTransactions();
}
