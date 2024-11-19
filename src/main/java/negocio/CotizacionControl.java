package negocio;

import database.Conexion;
import datos.CotizacionDAO;
import entidades.Cotizacion;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CotizacionControl {

    private final CotizacionDAO DATOS;
    private Cotizacion obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public CotizacionControl() {
        this.DATOS = new CotizacionDAO(Conexion.getInstancia().conectar());
        this.obj = new Cotizacion();
        this.registrosMostrados = 0;
    }

    // Método para listar todas las cotizaciones
    public DefaultTableModel listar() {
        List<Cotizacion> lista = DATOS.listar("");

        String[] titulos = {"ID", "Fecha", "Total", "ID Cliente"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[4];
        this.registrosMostrados = 0;

        for (Cotizacion item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getFecha().toString();
            registro[2] = Double.toString(item.getTotal());
            registro[3] = Integer.toString(item.getIdCliente());

            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    // Método para insertar una nueva cotización
    public String insertar(String fecha, double total, int idCliente) {
        obj = new Cotizacion(0, fecha, total, idCliente);
        String resultado;
        if (DATOS.insertar(obj)) {
            resultado = "OK";
        } else {
            resultado = "Error al insertar la cotización";
        }
        desconectar();
        return resultado;
    }

    // Método para actualizar una cotización existente
    public String actualizar(int id, String fecha, double total, int idCliente) {
        obj = new Cotizacion(id, fecha, total, idCliente);
        String resultado;
        if (DATOS.actualizar(obj)) {
            resultado = "OK";
        } else {
            resultado = "Error al actualizar la cotización";
        }
        desconectar();
        return resultado;
    }

    // Método para eliminar una cotización (desactivar)
    public String eliminar(int id) {
        String resultado;
        if (DATOS.desactivar(id)) {
            resultado = "OK";
        } else {
            resultado = "No se puede eliminar la cotización";
        }
        desconectar();
        return resultado;
    }

    // Método para obtener el total de registros mostrados
    public int total() {
        return registrosMostrados;
    }

    // Método para desconectar la conexión a la base de datos
    public void desconectar() {
        Conexion.getInstancia().desconectar();
    }
}
