
package com.javamaster.resources.service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.javamaster.resources.dao.UsersDAO;
import com.javamaster.resources.exception.AuthException;
import com.javamaster.resources.model.UsersModel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UsersService{
    
    private static UserServiceImpl instance;
    private UserServiceImpl(){
        
    }
    
    public static synchronized UserServiceImpl getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void register(String email, String password, String name) {
       UsersModel user = new UsersModel();
       user.setEmail(email);
       // Password hashing
       String hashedPassword = hashPassword(password);
       user.setPassword(hashedPassword);
       //user.setPassword(password);
       //This ensures duplicate emails are not allowed.
       if (UsersDAO.getInstance().emailExists(email)) {
           try {
               throw new AuthException("Email already exists!");
           } catch (AuthException ex) {
               Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       user.setName(name);
       UsersDAO.getInstance().save(user);
    }

    @Override
    public UsersModel auth(String email, String password) throws AuthException {
        UsersModel user = UsersDAO.getInstance().findByEmailAndPassword(email, password);
        if(user == null){
            throw new AuthException("Credentials are incorrect!");
        }
        return user;
    }
    
    // This hashes the password using SHA-256 before storing it in the database.
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


    
}
