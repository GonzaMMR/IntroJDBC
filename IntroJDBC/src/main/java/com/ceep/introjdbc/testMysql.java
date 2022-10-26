/*
*   Programa de testeo de conexion base de datos MYSQL
 */
package com.ceep.introjdbc;

import dominio.Persona;
import dominio.PersonaDao;
import dominio.UsuarioDao;
import dominio.usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public class testMysql {
    
    
    public static void main(String[] args) {
        
        /*
        Persona p1 = new Persona("Luka", "Modric", "luka@gmail.com", "644875295");
        Persona p2 = new Persona(1, "Karim", "valverde", "karim@gmail.com", "544125487");
        
        PersonaDao personaDao = new PersonaDao();
        try {
            List<Persona> personas = personaDao.seleccionar();
            personas.forEach(Persona -> {
                System.out.println("persona = "+ Persona);
            }
            );
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        

        //metodo de insertar una nueva persona
        personaDao.actualizar(p2);
        personaDao.eliminar(p2);
        */
        
        usuario u1 = new usuario("david", "alaba");
        usuario u2 = new usuario(1, "david", "martinez");
        //usuario u2 = new usuario();
        
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            List<usuario> usuarios = usuarioDao.seleccionar();
            usuarios.forEach(usuario -> {
                System.out.println("usuario = "+ usuario);
            }
            );
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        //usuarioDao.insertar(u1);
        usuarioDao.actualizar(u2);
        usuarioDao.eliminar(u2);
        
    }
}
