class Calculadora {
    interface Callback {
        void resultado(int resultado);
        void esImposible();
    }

    static void divide(int a, int b, Callback callback) {
        if (b == 0) {
            callback.esImposible();
        } else {
            callback.resultado(a/b);
        }
    }
}

class Usuario implements Calculadora.Callback {
    @Override
    public void resultado(int resultado) {
        System.out.println("EL RESULTADO ES " + resultado);
    }

    @Override
    public void esImposible() {
        System.out.println("CALCULO IMPOSIBLE");
    }
}


public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();

        Calculadora.divide(20, 0, usuario);
    }
}