
package com.javamaster.resources.dao;

import com.javamaster.resources.config.DBConnectionConfigs;
import com.javamaster.resources.model.UsersModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAO {
    private static UsersDAO instance;
    
    private UsersDAO(){
        
    }
    
    public static synchronized UsersDAO getInstance(){
        if(instance == null){
            instance = new UsersDAO();
        }
        return instance;
    
    }
    public void save(UsersModel user){
        
        /*Connection connection = DBConnectionConfigs.getConnection();
        try {
            PreparedStatement pr = connection.prepareStatement("insert into users_table" + "(email, password, name) values (?, ?, ?)");
            pr.setString(1, user.getEmail());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getName());
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  */  
        
        try (Connection connection = DBConnectionConfigs.getConnection()) {
            String sql = "INSERT INTO users_table (email, password, name) VALUES (?, ?, ?)";
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setString(1, user.getEmail());
                pr.setString(2, user.getPassword());
                pr.setString(3, user.getName());

                int rowsAffected = pr.executeUpdate();
                // Optional: Commit only if you're manually managing transactions
                // connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("✅ User saved successfully: " + user.getEmail());
                } else {
                    System.out.println("⚠️ No user was saved.");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, "❌ SQLException during user save", e);
        } catch (Exception e) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, "❌ Unexpected error during user save", e);
        }
       
        
    }
    public UsersModel findByEmailAndPassword(String email, String password){
        /*Connection connection = DBConnectionConfigs.getConnection();
        UsersModel user = null;
        try {
            PreparedStatement pr = connection.prepareStatement("select * from users_table where email=? and password=?;");
            //PreparedStatement pr = connection.prepareStatement("select * from users_table where email=?;");
            pr.setString(1, email);
            pr.setString(2, password);
             
             ResultSet rs = pr.executeQuery();
             if(rs.next()){
                 user = new UsersModel();
                 user.setId(rs.getInt(1));
                 user.setEmail(rs.getString(2));
                 user.setName(rs.getString(3));
             }
            /*pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                String storedHashedPassword = rs.getString("password");
                if (storedHashedPassword.equals(hashPassword(password))) {
                    user = new UsersModel();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setName(rs.getString("name"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;*/
        
         UsersModel user = null;
        try (Connection connection = DBConnectionConfigs.getConnection()) {
            String sql = "SELECT * FROM users_table WHERE email = ? AND password = ?";
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setString(1, email);
                pr.setString(2, password);

                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    user = new UsersModel();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setName(rs.getString("name"));
                    // Set password and age if needed
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, "❌ SQLException during user authentication", e);
        }
        return user;
    
    }
    
       private String hashPassword(String password) {
          try {
              MessageDigest md = MessageDigest.getInstance("SHA-256");
              byte[] hash = md.digest(password.getBytes());
              StringBuilder hexString = new StringBuilder();
              for (byte b : hash) {
                   hexString.append(String.format("%02x", b));
              }
               return hexString.toString();
          } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error hashing password", e);
            }
       }
    
    // This check duplicates
        public boolean emailExists(String email) {
            Connection connection = DBConnectionConfigs.getConnection();
            try {
                PreparedStatement pr = connection.prepareStatement("SELECT 1 FROM users_table WHERE email = ?");
                pr.setString(1, email);
                ResultSet rs = pr.executeQuery();
                return rs.next();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

}
