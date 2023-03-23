import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class SimuladorHipoteca {

    interface Callback {
        void errorPlazo();
        void errorCapital();
        void okCuota(double cuota);
    }

    static void calcularCuota(double capital, double plazo, Callback callback) {
        if (plazo == 0) {
            callback.errorPlazo();
        }
        else if (capital == 0){
            callback.errorCapital();
        } else {
            double interes = 0;

            try { interes = Double.parseDouble(HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create("https://fakebank-tan.vercel.app/api/get-interest")).GET().build(), HttpResponse.BodyHandlers.ofString()).body());} catch (Exception e) {}

            callback.okCuota(capital*interes/12/(1-Math.pow(1+(interes/12),-plazo*12)));
        }
    }
}

class Usuario implements SimuladorHipoteca.Callback {

    @Override
    public void errorPlazo() {
        System.out.println("PLAZO INVALIDO");
    }

    @Override
    public void errorCapital() {
        System.out.println("CAPITAL INVALIDO");
    }

    @Override
    public void okCuota(double cuota) {
        System.out.println("LA CUOTA ES " + cuota + "€");
    }
}

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        SimuladorHipoteca.calcularCuota(2000, 2, usuario);
    }
}
