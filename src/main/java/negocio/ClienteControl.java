package negocio;

import database.Conexion;
import datos.ClienteDAO;
import entidades.Cliente;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteControl {

    private final ClienteDAO DATOS;
    private Cliente obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ClienteControl() {
        this.DATOS = new ClienteDAO(Conexion.getInstancia().conectar());
        this.obj = new Cliente();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar() {
        List<Cliente> lista = DATOS.listar("");

        String titulos[] = {"ID", "Nombre", "Email", "Teléfono", "Dirección"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String registro[] = new String[5];
        this.registrosMostrados = 0;

        for (Cliente item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getEmail() != null ? item.getEmail() : "N/A";
            registro[3] = item.getTelefono() != null ? item.getTelefono() : "N/A";
            registro[4] = item.getDireccion() != null ? item.getDireccion() : "N/A";

            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String nombre, String email, String telefono, String direccion) {
        obj = new Cliente(0, nombre, email, telefono, direccion);
        String resultado;
        if (DATOS.insertar(obj)) {
            resultado = "OK";
        } else {
            resultado = "Error al insertar el cliente";
        }
        desconectar();
        return resultado;
    }

    public String actualizar(int id, String nombre, String email, String telefono, String direccion) {
        obj = new Cliente(id, nombre, email, telefono, direccion);
        String resultado;
        if (DATOS.actualizar(obj)) {
            resultado = "OK";
        } else {
            resultado = "Error al actualizar el cliente";
        }
        desconectar();
        return resultado;
    }

    public String eliminar(int id) {
        String resultado;
        if (DATOS.desactivar(id)) {
            resultado = "OK";
        } else {
            resultado = "No se puede eliminar el cliente";
        }
        desconectar();
        return resultado;
    }

    public int total() {
        return registrosMostrados;
    }

    public void desconectar() {
        Conexion.getInstancia().desconectar();
    }
}
