import models.Transaction;
import retrofit2.Response;
import service.BitstampService;
import service.ServiceGenerator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        BitstampService bitstampService = ServiceGenerator.createService(BitstampService.class, ServiceGenerator.ApiEndpoint.BITSTAMP);

        Response<List<Transaction>> response = null;
        try {
            response = bitstampService.getTransactions().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
