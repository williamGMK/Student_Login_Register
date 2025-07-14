
package com.javamaster.resources.service;

import com.javamaster.resources.exception.AuthException;
import com.javamaster.resources.model.UsersModel;


public interface UsersService {
    void register(String email, String password, String name);
    UsersModel auth(String email, String password) throws AuthException;
}
