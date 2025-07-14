
package com.javamaster.resources.dao;

import com.javamaster.resources.config.DBConnectionConfigs;
import com.javamaster.resources.model.UsersModel;
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
        Connection connection = DBConnectionConfigs.getConnection();
        try {
            PreparedStatement pr = connection.prepareStatement("insert into users_table" + "(email, password, name) values (?, ?, ?)");
            pr.setString(1, user.getEmail());
            pr.setString(2, user.getPassword());
             pr.setString(3, user.getName());
             pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public UsersModel findByEmailAndPassword(String email, String password){
        Connection connection = DBConnectionConfigs.getConnection();
        UsersModel user = null;
        try {
            PreparedStatement pr = connection.prepareStatement("select * from users_table where email=? and password=?;");
            pr.setString(1, email);
            pr.setString(2, password);
             
             ResultSet rs = pr.executeQuery();
             if(rs.next()){
                 user = new UsersModel();
                 user.setId(rs.getInt(1));
                 user.setEmail(rs.getString(2));
                 user.setName(rs.getString(3));
             }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
