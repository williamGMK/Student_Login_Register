
package com.javamaster.resources.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionConfigs {
    
    public static Connection getConnection(){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnectionConfigs.class.getName()).log(Level.SEVERE, null, ex);
            }
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/User-Login","postgres", "Glodeson123");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionConfigs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }/*
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/User-Login",
                "postgres",
                "Glodeson123"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
