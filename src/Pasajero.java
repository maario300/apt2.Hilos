import java.util.List;

public class Pasajero {

    private int id;
    private String tipo;
    private int prioridad; // 1 = más urgente

    public Pasajero(int id, String tipo, int prioridad) {
        this.id = id;
        this.tipo = tipo;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "P" + id + "(" + tipo + ", prio=" + prioridad + ")";
    }
}

