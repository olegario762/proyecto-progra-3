
import java.util.ArrayList;

public class NodoAVL {
    Vehiculo vehiculo;
    NodoAVL izquierdo, derecho;
    int altura;

    public NodoAVL(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.altura = 1;
    }

    public void actualizarAltura() {
        int altIzq = (izquierdo == null) ? 0 : izquierdo.altura;
        int altDer = (derecho == null) ? 0 : derecho.altura;
        this.altura = 1 + Math.max(altIzq, altDer);
    }

    public int obtenerBalance() {
        int altIzq = (izquierdo == null) ? 0 : izquierdo.altura;
        int altDer = (derecho == null) ? 0 : derecho.altura;
        return altIzq - altDer;
    }
}
