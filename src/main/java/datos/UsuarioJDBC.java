package datos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Usuario;

import static datos.Conexion.*;

public class UsuarioJDBC {
    private static final String SQL_SELECT = "SELECT id_usuario,username,password FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario (username,password) values (?,?)";
    private static final String SQL_UPDATE = "UPDATE test.usuario set username=?,password=? where id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM test.usuario where id_usuario>?";

    public List<Usuario> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            //se crea un objeto de tipo Connection y se lo completa con el metodo Conexion.getConnection()
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario= rs.getInt("id_usuario");
                String username = rs.getString("username");
                String pass = rs.getString("password");
                usuario = new Usuario(username,pass);
                usuarios.add(usuario);
            }
            ;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return usuarios;
    }

    ;

    public int insertar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            //se crea un objeto de tipo Connection y se lo completa con el metodo Conexion.getConnection()
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT,new String[] {"PRODUCT_ID"} );//la lista de PRODUCT_ID es para que me devuelva el id generado
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            //stmt.getGeneratedKeys();
            //System.out.println("query: "+stmt.toString());
            registros = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long productId = rs.getLong(1);
                System.out.println("id insertado="+productId);
            }
            //java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
            //System.out.println("el id insertado es: "+stmt.getGeneratedKeys().toString());

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
    public int actualizar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            //se crea un objeto de tipo Connection y se lo completa con el metodo Conexion.getConnection()
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE );
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            System.out.println("script sql a ejecutar= "+stmt.toString());
            //stmt.getGeneratedKeys();
            //System.out.println("query: "+stmt.toString());
            registros = stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
    public int eliminar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE );
            stmt.setInt(1, usuario.getId_usuario());
            System.out.println("script sql a ejecutar= "+stmt.toString());
            //stmt.getGeneratedKeys();
            //System.out.println("query: "+stmt.toString());
            registros = stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

}

