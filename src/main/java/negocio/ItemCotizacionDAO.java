
package negocio;

import datos.interfaces.CrudSimpleInterface;
import entidades.ItemCotizacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCotizacionDAO implements CrudSimpleInterface<ItemCotizacion> {

    private final Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public ItemCotizacionDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo ItemCotizacion
    @Override
    public boolean insertar(ItemCotizacion item) {
        String sql = "INSERT INTO item_cotizacion (cotizacion_id, descripcion, cantidad, costo_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, item.getCotizacionId());
            statement.setString(2, item.getDescripcion());
            statement.setInt(3, item.getCantidad());
            statement.setDouble(4, item.getCostoUnitario());
            statement.setDouble(5, item.getSubtotal());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos los ItemCotizacion con un filtro de texto en la descripción
    @Override
    public List<ItemCotizacion> listar(String texto) {
        List<ItemCotizacion> items = new ArrayList<>();
        String sql = "SELECT * FROM item_cotizacion WHERE descripcion LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + texto + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ItemCotizacion item = new ItemCotizacion(
                        resultSet.getInt("id"),
                        resultSet.getInt("cotizacion_id"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("costo_unitario"),
                        resultSet.getDouble("subtotal")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Método para contar el total de registros en la tabla item_cotizacion
    @Override
    public int total() {
        String sql = "SELECT COUNT(*) AS total FROM item_cotizacion";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Método para verificar la existencia de un ItemCotizacion basado en su descripción
    @Override
    public boolean existencia(String descripcion) {
        String sql = "SELECT COUNT(*) AS total FROM item_cotizacion WHERE descripcion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, descripcion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("total") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar un ItemCotizacion existente
    @Override
    public boolean actualizar(ItemCotizacion item) {
        String sql = "UPDATE item_cotizacion SET cotizacion_id = ?, descripcion = ?, cantidad = ?, costo_unitario = ?, subtotal = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, item.getCotizacionId());
            statement.setString(2, item.getDescripcion());
            statement.setInt(3, item.getCantidad());
            statement.setDouble(4, item.getCostoUnitario());
            statement.setDouble(5, item.getSubtotal());
            statement.setInt(6, item.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para desactivar un ItemCotizacion (marcarlo como inactivo)
    @Override
    public boolean desactivar(int id) {
        String sql = "UPDATE item_cotizacion SET activo = false WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para activar un ItemCotizacion (marcarlo como activo)
    @Override
    public boolean activar(int id) {
        String sql = "UPDATE item_cotizacion SET activo = true WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
