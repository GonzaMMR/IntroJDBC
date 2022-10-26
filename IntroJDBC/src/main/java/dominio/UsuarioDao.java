/*
*   usuarioDao
 */
package dominio;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioDao {
    
    private static final String SQL_SELECT = "SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (nombre, password) VALUES(?, ?)";
    private static final String SQL_UPDATE="UPDATE usuario SET nombre=?, password=? WHERE idusuario=?";
    private static final String SQL_DELETE= "DELETE FROM usuario WHERE idusuario=?";
    
    
    public List<usuario> seleccionar() throws SQLException{
        
        //inicializamos las varialbes
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Persona persona = null;
        List<usuario> usuarios = new ArrayList<>();
        
        conn = getConnection();
        stm = conn.prepareStatement(SQL_SELECT);
        rs = stm.executeQuery();
        
        while(rs.next()){
            int idusuario = rs.getInt("idusuario");
            String nombre = rs.getString("nombre");
            String password = rs.getString("password");
            
            //instancio un nuevo objeto
            usuarios.add(new usuario(idusuario, nombre, password));
        }
        close(rs);
        close(stm);
        close(conn);
        
        return usuarios;
    }
    
    //metodo que inserta un usuario en mi sistmea
    public int insertar(usuario usuario){
        //delcaro e inicializao mis variables 
        Connection conn = null;
        PreparedStatement stm = null;
        int registros = 0;
        
        try {
            //1: establecer la conexion
            conn = getConnection();
            
            //2. preparo mi instrucccion para ejecutar contra la base de datos
            stm = conn.prepareStatement(SQL_INSERT);
            //asignar los valores a los ? de la consulta
            stm.setString(1, usuario.getNombre());
            stm.setString(2, usuario.getPassword());
            
            //3. ejecuto el array
            registros = stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int actualizar(usuario usuario){
        //inicializo mis componentes
        Connection conn = null;
        PreparedStatement stm = null;
        int registros =0;
        
        try {
            conn = getConnection();
            stm = conn.prepareStatement(SQL_UPDATE);
            stm.setString(1, usuario.getNombre());
            stm.setString(2, usuario.getPassword());
            stm.setInt(3, usuario.getIdusuario());
            
            registros = stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int eliminar(usuario usuario){
        //inicializo mis componentes
        Connection conn = null;
        PreparedStatement stm = null;
        int registros =0;
        
        try {
            conn = getConnection();
            stm = conn.prepareStatement(SQL_DELETE);

            stm.setInt(1, usuario.getIdusuario());
            
            registros = stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
}
