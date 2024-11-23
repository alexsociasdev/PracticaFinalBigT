public class Logger {
    public synchronized static void log(String mensaje) {
        System.out.println(mensaje);
    }
}
