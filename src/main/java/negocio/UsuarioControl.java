package control;

import datos.UsuarioDAO;
import entidades.Usuario;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioControl {

    private final UsuarioDAO DAO;

    // Constructor
    public UsuarioControl() {
        this.DAO = new UsuarioDAO();
    }

    // Método para agregar un nuevo usuario
    public boolean agregarUsuario(String nombre, String email, String contrasena, boolean esAdmin) {
        Usuario usuario = new Usuario(0, nombre, email, contrasena, esAdmin);
        if (DAO.insertar(usuario)) {
            JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar usuario.");
            return false;
        }
    }

    // Método para actualizar un usuario existente
    public boolean actualizarUsuario(int id, String nombre, String email, String contrasena, boolean esAdmin) {
        Usuario usuario = new Usuario(id, nombre, email, contrasena, esAdmin);
        if (DAO.actualizar(usuario)) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario.");
            return false;
        }
    }

    // Método para eliminar un usuario por ID
    public boolean eliminarUsuario(int id) {
        if (DAO.eliminar(id)) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario.");
            return false;
        }
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = DAO.obtenerPorId(id);
        if (usuario != null) {
            return usuario;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            return null;
        }
    }

    // Método para listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return DAO.listar();
    }

    // Método para mostrar la lista de usuarios en un mensaje emergente
    public void mostrarUsuarios() {
        List<Usuario> usuarios = listarUsuarios();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Usuario usuario : usuarios) {
                sb.append("ID: ").append(usuario.getId())
                  .append(", Nombre: ").append(usuario.getNombre())
                  .append(", Email: ").append(usuario.getEmail())
                  .append(", Admin: ").append(usuario.isEsAdmin() ? "Sí" : "No")
                  .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}
