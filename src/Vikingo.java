import java.util.Random;

public class Vikingo extends Thread {
    public String nombre;
    private final Drakkar drakkar;
    private final Expedicion expedicion;
    private final Steersman steersman;

    public Vikingo(String nombre, Drakkar drakkar, Expedicion expedicion, Steersman steersman) {
        this.nombre = nombre;
        this.drakkar = drakkar;
        this.expedicion = expedicion;
        this.steersman = steersman;
    }
    /*private static int remada(int min, int max) {
        Random r = new Random();
        return r.nextInt(min, max);
    }*/

    @Override
    public void run() {
        while (!drakkar.isEnCasa()) {
            synchronized (steersman) {
                try {
                    steersman.wait(); // Espera a la señal del Steersman
                    if (drakkar.isEnCasa()) break; // Termina si ya llegaron a casa

                    int metros = remar();
                    Logger.log("[Vikingo] " + nombre + ": remando " + metros + " metros");
                    steersman.reportarRemada(metros);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // Termina el hilo si es interrumpido
                }
            }
        }
        Logger.log("[Vikingo] " + nombre + ": Hilo terminado.");
    }
    private int remar(){
        Random random = new Random();
        int min = expedicion.getMinMetros();
        int max = expedicion.getMaxMetros();
        return random.nextInt(min, max);
    }
}
