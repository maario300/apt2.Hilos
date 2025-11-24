public class Balsa {

    private String nombre;
    private int capacidad;
    private double tiempoSegundos;

    public Balsa(String nombre, int capacidad, double tiempoSegundos) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempoSegundos = tiempoSegundos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getTiempoSegundos() {
        return tiempoSegundos;
    }

    @Override
    public String toString() {
        return "Balsa{ nombre='" + nombre + '\'' +", capacidad=" + capacidad +", tiempoSegundos=" + tiempoSegundos ;
    }
}
