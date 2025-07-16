/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.javamaster.resources;

import com.javamaster.resources.exception.AuthException;
import com.javamaster.resources.model.UsersModel;
import com.javamaster.resources.service.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author glodi mukomo
 */
public class HomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // problem right now
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if(uri.equals("/main-page")){
            if(request.getSession().getAttribute("user") == null){
                request.setAttribute("errorMessage", "User is not authenticated");
                request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            }
            List<UsersModel> users = new ArrayList<>();
            users.add(new UsersModel("Glodi","mukomo29@gmail", 45));
            users.add(new UsersModel("Rudis","Rudis2@gmail", 35));
            users.add(new UsersModel("Abigail","Abigail25@gmail", 25));
            users.add(new UsersModel("Lolita","Lolita25@gmail", 15));
            String userName = request.getParameter("userName");
            request.setAttribute("users", users);
            request.setAttribute("welcomeMessage", "Hi user" + userName);
            request.getRequestDispatcher("/WEB-INF/view/main-page.jsp").forward(request, response);
        }else if(uri.equals("/register")){
            String method = request.getMethod();
            System.out.println(method);
            if(method.equals("GET")){
                request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            }else{
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                System.out.println("Registering: " + email + ", " + password + ", " + name);

                //UserServiceImpl.getInstance().register(email, password, name);
                //response.sendRedirect("/login");
                UserServiceImpl.getInstance().register(email, password, name);
                request.setAttribute("successMessage", "Registration successful. Please login.");
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

            }
            
        }else if(uri.equals("/login")){
            String method = request.getMethod();
            if(method.equals("GET")){
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }else{
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                
                try {
                    UsersModel user = UserServiceImpl.getInstance().auth(email, password);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("/main-page");
                } catch (AuthException ex) {
                    request.setAttribute("errorMessage", ex.getMessage());
                    request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
                }
                
                
            }    
        }
        else{
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
