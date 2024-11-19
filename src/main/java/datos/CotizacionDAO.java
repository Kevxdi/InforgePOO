package datos;

import database.Conexion;
import entidades.Cotizacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CotizacionDAO {

    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CotizacionDAO(Connection conn) {
        this.conn = conn;
    }

    // Método para listar todas las cotizaciones
    public List<Cotizacion> listar(String texto) {
        List<Cotizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM cotizacion";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion obj = new Cotizacion(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getDouble("total"),
                        rs.getInt("id_cliente")
                );
                lista.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Error al listar cotizaciones: " + e.getMessage());
        }
        return lista;
    }

    // Método para insertar una nueva cotización
    public boolean insertar(Cotizacion obj) {
        String sql = "INSERT INTO cotizacion (fecha, total, id_cliente) VALUES (?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getFecha());
            ps.setDouble(2, obj.getTotal());
            ps.setInt(3, obj.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar cotización: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar una cotización
    public boolean actualizar(Cotizacion obj) {
        String sql = "UPDATE cotizacion SET fecha = ?, total = ?, id_cliente = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getFecha());
            ps.setDouble(2, obj.getTotal());
            ps.setInt(3, obj.getIdCliente());
            ps.setInt(4, obj.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar cotización: " + e.getMessage());
            return false;
        }
    }

    // Método para desactivar (eliminar lógicamente) una cotización
    public boolean desactivar(int id) {
        String sql = "UPDATE cotizacion SET activo = 0 WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al desactivar cotización: " + e.getMessage());
            return false;
        }
    }
}
