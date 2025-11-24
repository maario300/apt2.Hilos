import java.util.ArrayList;
import java.util.List;

public class Barco {

    private List<Pasajero> pasajeros;

    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros = new ArrayList<>(pasajeros);
    }

    public List<Pasajero> rescatar(int capacidad) {
        List<Pasajero> rescatados = new ArrayList<>();
        int count = 0;
        int prioridad = 1;

        // Mientras queden prioridades por revisar y no se llene la capacidad
        while (count < capacidad && prioridad <= 4) {

            for (int i = 0; i < pasajeros.size() && count < capacidad; i++) {
                Pasajero p = pasajeros.get(i);

                if (p.getPrioridad() == prioridad) {
                    rescatados.add(p);
                    pasajeros.remove(i);
                    i--;
                    count++;
                }
            }

            prioridad++;
        }

        return rescatados;
    }

    public int pasajerosRestantes() {
        return pasajeros.size();
    }
}



