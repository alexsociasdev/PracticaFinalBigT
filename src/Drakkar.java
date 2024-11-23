public class Drakkar {
    private String nombre;
    private int distanciaRecorrida;
    private boolean regresando;
    private boolean llegado;

    public Drakkar(String nombre) {
        this.nombre = nombre;
        this.distanciaRecorrida = 0;
        this.regresando = false;
        this.llegado = false;
    }

    public synchronized void avanzar(int metros) {
        if (!regresando) {
            distanciaRecorrida += metros;
        } else {
            distanciaRecorrida -= metros;
            if (distanciaRecorrida <= 0) {
                distanciaRecorrida = 0;
                llegado = true;
            }
        }
    }

    public synchronized void iniciarRegreso() {
        regresando = true;
    }

    public synchronized boolean isRegresando() {
        return regresando;
    }

    public synchronized boolean isEnCasa() {
        return llegado;
    }

    public synchronized int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public String getNombre() {
        return nombre;
    }
}
