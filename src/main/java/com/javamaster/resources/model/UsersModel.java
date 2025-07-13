/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamaster.resources.model;

/**
 *
 * @author glodi mukomo
 */
public class UsersModel {
    private String name;
    private String email;
    private Integer age;
    
    public UsersModel() {
       
    }

    public UsersModel(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
