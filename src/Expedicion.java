public class Expedicion {
    public int distancia;
    public int min_metros;
    public int max_metros;

    public Expedicion(int distancia, int min_metros, int max_metros) {
        this.distancia = distancia;
        this.min_metros = min_metros;
        this.max_metros = max_metros;
    }
    public int getDistancia() {
        return distancia;
    }

    public int getMinMetros() {
        return min_metros;
    }

    public int getMaxMetros() {
        return max_metros;
    }
}
