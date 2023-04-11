package tienda.persistencia;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
//data acces objet
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    private final String USER = "root";

    private final String PASSWORD = "34747662Loden";

    private final String DATABASE = "tienda";

    private final String DRIVER = "com.mysql.jdbc.Driver";


    protected void conectarBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://127.0.0.1:3306/" + DATABASE + "?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos,USER,PASSWORD);
            System.out.println("Conexión exitosa");
        }catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }


    }
    protected void desconectarBase() throws SQLException {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if(conexion != null){
                conexion.close();
            }
        } catch (Exception e){
            throw e;
        }
    }
protected void instarModificarEliminar(String sql) throws SQLException, ClassNotFoundException {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch (SQLException | ClassNotFoundException ex){
            throw ex;



        }finally {
            desconectarBase();
        }

}
protected void consultarBase(String sql) throws Exception{
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

        } catch (Exception ex){
            throw ex;
        }
}
    }






