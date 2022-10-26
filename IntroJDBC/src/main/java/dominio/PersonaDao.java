/*
*   conjunto de opeaciones que voy a poder relaizar sobre una persona
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno Mañana
 */
public class PersonaDao {
    
    private static final String SQL_SELECT = "SELECT * FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellidos," +
            "email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE="UPDATE persona SET nombre=?, apellidos=?, email=?," 
            + "telefono=? WHERE idpersona=?";
    private static final String SQL_DELETE= "DELETE from persona WHERE idpersona=?";
    
    public List<Persona> seleccionar() throws SQLException{
        
        //inicializamos las varialbes
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        
        conn = getConnection();
        stm = conn.prepareStatement(SQL_SELECT);
        rs = stm.executeQuery();
        
        while(rs.next()){
            int idpersona = rs.getInt("idpersona");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            
            //instancio un nuevo objeto
            personas.add(new Persona(idpersona, nombre, apellidos, email, telefono));
        }
        close(rs);
        close(stm);
        close(conn);
        
        return personas;
    }
    
    //metodo que inserta una persona en mi sistmea
    public int insertar(Persona persona){
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
            stm.setString(1, persona.getNombre());
            stm.setString(2, persona.getApellidos());
            stm.setString(3, persona.getEmail());
            stm.setString(4, persona.getTelefono());
            
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
    
    public int actualizar(Persona persona){
        //inicializo mis componentes
        Connection conn = null;
        PreparedStatement stm = null;
        int registros =0;
        
        try {
            conn = getConnection();
            stm = conn.prepareStatement(SQL_UPDATE);
            stm.setString(1, persona.getNombre());
            stm.setString(2, persona.getApellidos());
            stm.setString(3, persona.getEmail());
            stm.setString(4, persona.getTelefono());
            stm.setInt(5, persona.getIdpersona());
            
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
    
    public int eliminar(Persona persona){
        //inicializo mis componentes
        Connection conn = null;
        PreparedStatement stm = null;
        int registros =0;
        
        try {
            conn = getConnection();
            stm = conn.prepareStatement(SQL_DELETE);

            stm.setInt(1, persona.getIdpersona());
            
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
