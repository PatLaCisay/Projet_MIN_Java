package com.epf.rentmanager.servlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/users/create/error")

public class UserCreateErrorServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String error = "";
        switch (id){
            case 1 :
                error="Les utilisateurs doivent avoir au moins 18 ans.";
                break;
            case 2 :
                error="Cette adresse email est déjà utilisée par un autre utilisateur.";
                break;
        }
        request.setAttribute("error", error);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/error.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
