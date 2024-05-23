package Conversor.Principal;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaRate {

    public MonedaRate rateConversion(String base, String target) {

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/19e4863619624573d9e44811/pair/" + base + "/"+ target);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return  new Gson().fromJson(response.body(), MonedaRate.class);
        } catch (Exception e) {
            throw new RuntimeException("Conversion no disponible");
        }


    }

}