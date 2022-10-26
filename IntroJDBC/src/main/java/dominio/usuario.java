/*
*   OBJETO: usuario
 */
package dominio;

/**
 *
 * @author Alumno Mañana
 */
public class usuario {
    private  int idusuario;
    private  String nombre;
    private  String password;

    
    //constructor
    //vacio
    public usuario() {
    }
    
    //sin id
    public usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    
    //con id
    public usuario(int idusuario, String nombre, String password) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.password = password;
    }
    
    
    
    //getter and setter
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    //equals and hashcode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idusuario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final usuario other = (usuario) obj;
        if (this.idusuario != other.idusuario) {
            return false;
        }
        return true;
    }
    
    
    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("usuario{idusuario=").append(idusuario);
        sb.append(", nombre=").append(nombre);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
