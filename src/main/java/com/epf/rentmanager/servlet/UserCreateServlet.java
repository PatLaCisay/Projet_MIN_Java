package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lastName;
        String firstName;
        String email;
        LocalDate birthDate;


        lastName = request.getParameter("first_name");
        firstName = request.getParameter("last_name");
        email = request.getParameter("email");
        birthDate = LocalDate.parse(request.getParameter("birthdate"));
        if( Period.between(birthDate, LocalDate.now()).getYears()<18){
            response.sendRedirect("http://localhost:8080/rentmanager/users/create/error?id=1");
            return;
        }
        Client client = new Client(firstName,lastName,birthDate,email);

        try {
            List<Client> clients = this.clientService.findAll();
            if(clients.contains(client)){
                response.sendRedirect("http://localhost:8080/rentmanager/users/create/error?id=2");
                return;
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        try {
            this.clientService.create(client);
            response.sendRedirect("http://localhost:8080/rentmanager/users");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
