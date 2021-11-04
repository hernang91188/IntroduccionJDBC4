package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.PersonaDTO;
import static datos.Conexion.*;

public class PersonaDaoJDBC implements PersonaDao{
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, mail, telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO test.persona (nombre,apellido,mail,telefono) values (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE test.persona set nombre=?,apellido=?,mail=?,telefono=? where id_persona>?";
    private static final String SQL_DELETE = "DELETE FROM test.persona where id_persona>?";
    private Connection conexioTransaccional;
    public PersonaDaoJDBC(){};
    public PersonaDaoJDBC(Connection conexioTransaccional){
        this.conexioTransaccional=conexioTransaccional;
    };

    public List<PersonaDTO> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO personaDTO = null;
        List<PersonaDTO> personaDTOS = new ArrayList<>();
        try {
            //si this.conexiontransaccional=!null entonces utiliza this.conexionTranaccional.
            //de lo contrario, obetenmos una nueva coneccion (getConnection_BD2())
            //en el bloque finally, preguntamos de donde obtuvimos la conexion.
            //si this.conexionTransaccional==null significa q se creo una nueva transaccion dentro de
            //este metodo por lo tanto se debe cerrar.si el onjeto conecion =!null entonces no se debe cerrar.
            conn = this.conexioTransaccional!=null ? this.conexioTransaccional:getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("mail");
                int telefono = rs.getInt("telefono");
                personaDTO = new PersonaDTO(id_persona, nombre, apellido, email, telefono);
                personaDTOS.add(personaDTO);
            }
            ;

        }  finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.conexioTransaccional==null){
                    Conexion.close(conn);
                };

            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return personaDTOS;
    }

    ;

    public int insertar(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexioTransaccional!=null ? this.conexioTransaccional:getConnection();
            stmt = conn.prepareStatement(SQL_INSERT,new String[] {"PRODUCT_ID"} );//la lista de PRODUCT_ID es para que me devuelva el id generado
            stmt.setString(1, personaDTO.getNombre());
            stmt.setString(2, personaDTO.getApellido());
            stmt.setString(3, personaDTO.getMail());
            stmt.setInt(4, personaDTO.getTelefono());
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

        }  finally {
            try {
                close(stmt);
                if(this.conexioTransaccional==null){
                    Conexion.close(conn);
                };
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
    public int actualizar(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexioTransaccional!=null ? this.conexioTransaccional:getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE );
            stmt.setString(1, personaDTO.getNombre());
            stmt.setString(2, personaDTO.getApellido());
            stmt.setString(3, personaDTO.getMail());
            stmt.setInt(4, personaDTO.getTelefono());
            stmt.setInt(5, personaDTO.getId_persona());
            System.out.println("script sql a ejecutar= "+stmt.toString());
            //stmt.getGeneratedKeys();
            //System.out.println("query: "+stmt.toString());
            registros = stmt.executeUpdate();


        } finally {
            try {
                close(stmt);
                if(this.conexioTransaccional==null){
                    Conexion.close(conn);
                };
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
    public int eliminar(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexioTransaccional!=null ? this.conexioTransaccional:getConnection();
            stmt = conn.prepareStatement(SQL_DELETE );
            stmt.setInt(1, personaDTO.getId_persona());
            System.out.println("script sql a ejecutar= "+stmt.toString());
            //stmt.getGeneratedKeys();
            //System.out.println("query: "+stmt.toString());
            registros = stmt.executeUpdate();



        } finally {
            try {
                close(stmt);
                if(this.conexioTransaccional==null){
                    Conexion.close(conn);
                };
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

}
