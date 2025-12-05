import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        // Crear pasajeros
        List<Pasajero> pasajeros = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 352; i++) {
            int tipoRandom = random.nextInt(4);
            String tipo;
            int prioridad;
            switch (tipoRandom) {
                case 0 -> {
                    tipo = "Niño";
                    prioridad = 1;
                }
                case 1 -> {
                    tipo = "Adulto";
                    prioridad = 2;
                }
                case 2 -> {
                    tipo = "Anciano";
                    prioridad = 3;
                }
                default -> {
                    tipo = "Tripulacion";
                    prioridad = 4;
                }
            }
            pasajeros.add(new Pasajero(i, tipo, prioridad));
        }

        // Crear barco con los pasajeros
        Barco barco = new Barco(pasajeros);

        // Crear las balsas
        List<Balsa> balsas = new ArrayList<>();
        balsas.add(new Balsa("Acasta", 1, 0.5));
        balsas.add(new Balsa("Banff", 2, 1));
        balsas.add(new Balsa("Cadiz", 3, 2));
        balsas.add(new Balsa("Deimos", 4, 4));
        balsas.add(new Balsa("Expedicion", 5, 8));

        // lanzar hilos de rescate
        List<Thread> hilos = new ArrayList<>();
        for (Balsa b : balsas) {
            Thread t = new Thread(new Rescate(b, barco,semaphore));
            hilos.add(t);
            t.start();
        }

        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main interrumpido.");
            }
        }

        System.out.println(" Rescate finalizado. Pasajeros restantes: " + barco.pasajerosRestantes());
    }
}
