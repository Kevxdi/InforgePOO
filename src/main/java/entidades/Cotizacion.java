package entidades;

public class Cotizacion {

    private int id;
    private String fecha;
    private double total;
    private int idCliente;

    public Cotizacion() {
    }

    public Cotizacion(int id, String fecha, double total, int idCliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
