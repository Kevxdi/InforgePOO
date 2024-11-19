
package negocio;

import datos.ItemCotizacionDAO;
import entidades.ItemCotizacion;
import java.sql.Connection;
import java.util.List;

public class ItemCotizacionControl {

    private final ItemCotizacionDAO itemCotizacionDAO;

    // Constructor que recibe la conexión y crea un DAO para gestionar la base de datos
    public ItemCotizacionControl(Connection connection) {
        this.itemCotizacionDAO = new ItemCotizacionDAO(connection);
    }

    // Método para agregar un nuevo ItemCotizacion
    public boolean agregarItem(int cotizacionId, String descripcion, int cantidad, double costoUnitario) {
        // Calcular el subtotal basado en la cantidad y el costo unitario
        double subtotal = cantidad * costoUnitario;

        // Crear un objeto ItemCotizacion con los datos proporcionados
        ItemCotizacion item = new ItemCotizacion(0, cotizacionId, descripcion, cantidad, costoUnitario, subtotal);

        // Insertar el ItemCotizacion en la base de datos usando el DAO
        return itemCotizacionDAO.insertar(item);
    }

    // Método para listar todos los items que coincidan con un texto en la descripción
    public List<ItemCotizacion> listarItems(String texto) {
        return itemCotizacionDAO.listar(texto);
    }

    // Método para actualizar un ItemCotizacion existente
    public boolean actualizarItem(int id, int cotizacionId, String descripcion, int cantidad, double costoUnitario) {
        // Calcular el nuevo subtotal
        double subtotal = cantidad * costoUnitario;

        // Crear un objeto ItemCotizacion con los nuevos datos
        ItemCotizacion item = new ItemCotizacion(id, cotizacionId, descripcion, cantidad, costoUnitario, subtotal);

        // Actualizar el ItemCotizacion en la base de datos usando el DAO
        return itemCotizacionDAO.actualizar(item);
    }

    // Método para eliminar (desactivar) un ItemCotizacion
    public boolean eliminarItem(int id) {
        return itemCotizacionDAO.desactivar(id);
    }

    // Método para reactivar un ItemCotizacion previamente eliminado
    public boolean reactivarItem(int id) {
        return itemCotizacionDAO.activar(id);
    }

    // Método para contar el número total de items en la tabla item_cotizacion
    public int contarItems() {
        return itemCotizacionDAO.total();
    }

    // Método para verificar la existencia de un ItemCotizacion por descripción
    public boolean verificarExistencia(String descripcion) {
        return itemCotizacionDAO.existencia(descripcion);
    }
}
