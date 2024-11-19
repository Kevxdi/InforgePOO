package entidades;

public class ItemCotizacion {
    private int id;
    private int cotizacionId;
    private String descripcion;
    private int cantidad;
    private double costoUnitario;
    private double subtotal;

    public ItemCotizacion(int id, int cotizacionId, String descripcion, int cantidad, double costoUnitario, double subtotal) {
        this.id = id;
        this.cotizacionId = cotizacionId;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(int cotizacionId) {
        this.cotizacionId = cotizacionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
