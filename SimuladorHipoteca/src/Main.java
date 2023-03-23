import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Calculadora {
    static double dividir(double a, double b) {
        // if (capital <= 0) error: capital no valido
        // if (plazo <= 0) error: plazo no valido

        double interes = 0;

        try {
            // obtener 'interes' del banco...
            interes = Double.parseDouble(HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create("https://fakebank-tan.vercel.app/api/get-interest")).GET().build(), HttpResponse.BodyHandlers.ofString()).body());
        } catch (Exception e) {
            // error obteniendo interes
        }

        //return a/b;
        return a*interes/12/(1-Math.pow(1+(interes/12),-b*12));
    }
}

public class Main {
    public static void main(String[] args) {
        double cuota = Calculadora.dividir(2000, 2);

        System.out.println(cuota);
    }
}
