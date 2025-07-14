
package com.javamaster.resources.service;

import com.javamaster.resources.dao.UsersDAO;
import com.javamaster.resources.exception.AuthException;
import com.javamaster.resources.model.UsersModel;

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
       user.setPassword(password);
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

    
}
