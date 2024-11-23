import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Logger.log("Distancia al poblado a saquear:");
        int distancia = input.nextInt();

        Logger.log("Metros mínimos por remada:");
        int minMetros = input.nextInt();

        Logger.log("Metros máximos por remada:");
        int maxMetros = input.nextInt();

        Expedicion expedicion = new Expedicion(distancia, minMetros, maxMetros);

        Drakkar drakkarA = new Drakkar("Drakkar A");
        Drakkar drakkarB = new Drakkar("Drakkar B");

        Steersman steersmanA = new Steersman("Alvar", drakkarA, expedicion);
        Steersman steersmanB = new Steersman("Bjorn", drakkarB, expedicion);
        Logger.log("Capitanes listos");
        Vikingo[] vikingos = {
                new Vikingo("Asmund", drakkarA, expedicion, steersmanA),
                new Vikingo("Arne", drakkarA, expedicion, steersmanA),
                new Vikingo("Agnar", drakkarA, expedicion, steersmanA),
                new Vikingo("Bjarni", drakkarB, expedicion, steersmanB),
                new Vikingo("Baldur", drakkarB, expedicion, steersmanB),
                new Vikingo("Bersi", drakkarB, expedicion, steersmanB)
        };
        Logger.log("vikingos listos");
        steersmanA.start();
        steersmanB.start();

        for (Vikingo vikingo : vikingos) {
            vikingo.start();
        }
        Logger.log("start de todos los hilos");
        try {
            steersmanA.join();
            steersmanB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ganador;
        if (drakkarA.isEnCasa() && !drakkarB.isEnCasa()) {
            ganador = drakkarA.getNombre();
        } else {
            ganador = drakkarB.getNombre();
        }
        Logger.log("El Drakkar ganador ha sido: " + ganador);

    }
}
