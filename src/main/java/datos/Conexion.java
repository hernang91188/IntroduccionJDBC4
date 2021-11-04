package datos;

//import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class Conexion {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASSWORD="admin";
    public static DataSource getDataSource(){
        BasicDataSource ds=new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        //definimos el tamano inicial del pool de conexiones
        ds.setInitialSize(5);
        return ds;

    };
    public static DataSource getDataSourceXJNDI(){
        DataSource ds=new BasicDataSource();
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("busca el jndi");
            ds = (DataSource) ctx.lookup("jdbc/JNDIMySQL");
        } catch (NamingException e) {
            System.out.println("error al buscar el jndo");
            e.printStackTrace();
        }
        //ds.setUrl(JDBC_URL);
        //ds.setUsername(JDBC_USER);
        //ds.setPassword(JDBC_PASSWORD);
        //definimos el tamano inicial del pool de conexiones
        //ds.setInitialSize(5);
        return ds;

    };
    public static Connection getConnection() throws SQLException {
        //Como se configuro el dataSource para la base, se utiliza el metodo getDataSource.
        return getDataSource().getConnection();
        //Si no hay datasource cnfigurado, se utiliza la linea de abajo para retornar la conexion a la base
        //return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    };
    public static Connection getConnectionXJNDI() throws SQLException {
        //Como se configuro el dataSource para la base, se utiliza el metodo getDataSource.
        return getDataSourceXJNDI().getConnection();
        //Si no hay datasource cnfigurado, se utiliza la linea de abajo para retornar la conexion a la base
        //return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    };

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }
    public static void close(PreparedStatement smtm) throws SQLException {
        smtm.close();
    };
    public static void close(Connection con) throws SQLException {
        con.close();
    };
}
