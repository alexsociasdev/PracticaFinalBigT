import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Clase Steersman
public class Steersman extends Thread {
    private final String nombre;
    private final Drakkar drakkar;
    private final Expedicion expedicion;
    private List<Integer> avances = new ArrayList<>();
    private int vikingosListos = 0;
    private boolean saqueado = false;

    public Steersman(String nombre, Drakkar drakkar, Expedicion expedicion) {
        this.nombre = nombre;
        this.drakkar = drakkar;
        this.expedicion = expedicion;
    }

    public synchronized void reportarRemada(int metros) {
        avances.add(metros);
        vikingosListos++;
        if (vikingosListos == 3) {
            notify();
        }
    }

    @Override
    public void run() {
        while (!drakkar.isEnCasa()) {
            synchronized (this) {
                while (vikingosListos < 3) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int avanceTotal = calcularAvance();
                Logger.log("[Steersman] " + nombre + ": avanzamos " + avanceTotal + " metros");
                drakkar.avanzar(avanceTotal);

                if (!saqueado && drakkar.getDistanciaRecorrida() >= expedicion.getDistancia()) {
                    saqueado = true;
                    Logger.log("[Steersman] " + nombre + ": Atacando el poblado");
                    try {
                        Thread.sleep((int) (Math.random() * 10 + 1) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Logger.log("[Steersman] " + nombre + ": Saqueo completo, regresando a casa");
                    drakkar.iniciarRegreso();
                }

                if (drakkar.isEnCasa()) {
                    Logger.log("[Steersman] " + nombre + ": Hemos vuelto a casa");
                    break;
                }

                vikingosListos = 0;
                avances.clear();
                notifyAll(); // Notifica a todos los hilos
            }
        }
        Logger.log("[Steersman] " + nombre + ": Hilo terminado.");
    }

    private int calcularAvance() {
        int sumaMetros = 0;
        for (int i = 0; i < avances.size(); i++) {
         sumaMetros += avances.get(i);
        }
        Random random = new Random();
        int penalizacionViento = random.nextInt(0, expedicion.getMaxMetros());
        Logger.log("[Steersman] " + nombre + ": el viento nos ha hecho perder " + penalizacionViento + " metros");
        return sumaMetros - penalizacionViento;
    }
}
