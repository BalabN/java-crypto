import retrofit2.Response;
import service.BitstampService;
import service.ServiceGenerator;

public class Main {

    public static void main(String[] args) {
        BitstampService bitstampService = ServiceGenerator.createService(BitstampService.class, ServiceGenerator.ApiEndpoint.WDA_SERVER);

        Response<String> response = null;
        try {
            response = bitstampService.dejMiNeki("neki").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
