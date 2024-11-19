package negocio;

import datos.ProyectoDAO;
import entidades.Proyecto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ProyectoControl {

    private final ProyectoDAO DATOS;
    private Proyecto obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ProyectoControl() {
        this.DATOS = new ProyectoDAO();
        this.obj = new Proyecto();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Proyecto> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));

        String titulos[] = {"ID", "Nombre", "Descripción", "Fecha Inicio", "Fecha Fin", "Usuario ID", "Especificaciones", "Plano", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String registro[] = new String[9];
        this.registrosMostrados = 0;

        for (Proyecto item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            registro[3] = item.getFechaInicio().toString();
            registro[4] = item.getFechaFin().toString();
            registro[5] = Integer.toString(item.getUsuarioId());
            registro[6] = item.getEspecificaciones();
            registro[7] = item.getPlano() != null ? "Sí" : "No";
            registro[8] = item.isActivo() ? "Activo" : "Inactivo";

            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String nombre, String descripcion, String fechaInicio, String fechaFin, int usuarioId, String especificaciones, byte[] plano) {
        try {
            if (DATOS.existencia(nombre)) {
                return "El proyecto ya existe";
            }
        } catch (Exception e) {
            return "Error al verificar la existencia: " + e.getMessage();
        }
        return null;
    }

    public String actualizar(int id, String nombre, String nombreAnt, String descripcion, String fechaInicio, String fechaFin, int usuarioId, String especificaciones, byte[] plano) {
        if (nombreAnt != null && nombre.equals(nombreAnt) || !DATOS.existencia(nombre)) {
            obj = new Proyecto(id, nombre, descripcion, fechaInicio, fechaFin, usuarioId, especificaciones, plano, true);

            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error al actualizar el proyecto";
            }
        } else {
            return "El proyecto ya existe";
        }
    }

    public String desactivar(int id) {
        return DATOS.desactivar(id) ? "OK" : "No se puede desactivar el proyecto";
    }

    public String activar(int id) {
        return DATOS.activar(id) ? "OK" : "No se puede activar el proyecto";
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}
