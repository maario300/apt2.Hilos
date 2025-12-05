import java.util.List;
import java.util.concurrent.Semaphore;

public class Rescate implements Runnable {

    private Balsa balsa;
    private Barco barco;
    Semaphore semaphore = new Semaphore(1);



    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public Balsa getBalsa() {
        return balsa;
    }

    public void setBalsa(Balsa balsa) {
        this.balsa = balsa;
    }

    /*
            CREAR SEMAFORO

            ACCEDERA
             this.getSemaf().acquire();
             LA LISTA DE PASAJEROS CON LOS PERMISOS
            Y CUANDO TERMINE SE LIBERA
              this.getSemaf().release();
             */

    public Rescate(Balsa balsa, Barco barco, Semaphore semaphore) {
        this.balsa = balsa;
        this.barco = barco;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {

                this.semaphore.acquire();
                // capacidad de la balsa
                List<Pasajero> rescatados = barco.rescatar(balsa.getCapacidad());

                if (rescatados.isEmpty()) {
                    // No quedan pasajeros
                    break;
                }


                // rescate de cada balsa
                wait();
                if (!rescatados.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Balsa ").append(balsa.getNombre())
                            .append(" ha rescatado ").append(rescatados.size()).append(" pasajeros: ");

                    for (Pasajero p : rescatados) {
                        sb.append(p.getId()).append(" ");
                    }

                    // Imprime toda la línea de golpe
                    System.out.println(sb.toString());
                }


                // tiempo de rescate
                Thread.sleep((long) (balsa.getTiempoSegundos() * 1000));

                System.out.println("Balsa regresada: " + balsa.getNombre());
            } catch (InterruptedException ex) {
                System.getLogger(Rescate.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }


        this.semaphore.release();
    }
}
