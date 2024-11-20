package datos;

import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Proyecto;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProyectoDAO implements CrudSimpleInterface<Proyecto> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ProyectoDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Proyecto> listar(String texto) {
        List<Proyecto> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM proyecto WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Proyecto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_fin"),
                        rs.getInt("usuario_id"),
                        rs.getString("especificaciones"),
                        rs.getBytes("plano"),
                        rs.getBoolean("activo")
                ));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Proyecto obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "INSERT INTO proyecto (nombre, descripcion, fecha_inicio, fecha_fn, usuario_id, especificaciones, plano) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setDate(3, Date.valueOf(obj.getFechaInicio()));
            ps.setDate(4, Date.valueOf(obj.getFechaFin()));
            ps.setInt(5, obj.getUsuarioId());
            ps.setString(6, obj.getEspecificaciones());
            ps.setBytes(7, obj.getPlano());

            if (ps.executeUpdate() > 0) {
                resp = true;
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("datos.ProyectoDAO.insertar()"+ e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            JOptionPane.showMessageDialog(null,"Archivo subido correctamente");
            ps = null;
            CON.desconectar();
        }

        return resp;
    }

    @Override
    public boolean actualizar(Proyecto obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "UPDATE proyecto SET nombre=?, descripcion=?, fecha_inicio=?, fecha_fin=?, usuario_id=?, especificaciones=?, plano=? WHERE id=?"
            );
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setDate(3, Date.valueOf(obj.getFechaInicio()));
            ps.setDate(4, Date.valueOf(obj.getFechaFin()));
            ps.setInt(5, obj.getUsuarioId());
            ps.setString(6, obj.getEspecificaciones());
            ps.setBytes(7, obj.getPlano());
            ps.setInt(8, obj.getId());

            if (ps.executeUpdate() > 0) {
                resp = true;
            }

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }

        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE proyecto SET activo=0 WHERE id=?");
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                resp = true;
            }

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }

        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE proyecto SET activo=1 WHERE id=?");
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                resp = true;
            }

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }

        return resp;
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM proyecto");
            rs = ps.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt(1);
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }

        return totalRegistros;
    }

    @Override
    public boolean existencia(String nombre) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("SELECT nombre FROM proyecto WHERE nombre=?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                resp = true;
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }

        return resp;
    }
}
