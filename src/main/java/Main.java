import java.util.List;

import logic.LinearRegression;
import models.Transaction;
import retrofit2.Response;
import service.BitstampService;
import service.ServiceGenerator;

public class Main {

    public static void main(String[] args) {
        BitstampService bitstampService = ServiceGenerator.createService(BitstampService.class, ServiceGenerator.ApiEndpoint.BITSTAMP);

        Response<List<Transaction>> response = null;
        try {
            response = bitstampService.getTransactions().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null && response.isSuccessful()) {
            List<Transaction> transaction = response.body();
            System.out.println(response.body());
            LinearRegression linearRegression = new LinearRegression(transaction);
        } else if (response != null) {
            System.out.println("Response failed with error code = " + response.code() + "; message = " + response.errorBody());
        } else {
            System.out.println("Response = null");
        }
    }
}
