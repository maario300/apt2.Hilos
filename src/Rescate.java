import java.util.List;

public class Rescate implements Runnable {

    private Balsa balsa;
    private Barco barco;

    public Rescate(Balsa balsa, Barco barco) {
        this.balsa = balsa;
        this.barco = barco;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // capacidad de la balsa
                List<Pasajero> rescatados = barco.rescatar(balsa.getCapacidad());

                if (rescatados.isEmpty()) {
                    // No quedan pasajeros
                    break;
                }


                // rescate de cada balsa

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
    }
}
