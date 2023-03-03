package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

@WebServlet(name = "UserCreateServlet", value = "/users/create")
public class UserCreateServlet extends HttpServlet {
    ClientService clientService;
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

        Client client = new Client(firstName,lastName,birthDate,email);

        try {
            this.clientService.getInstance().create(client);
            response.sendRedirect("http://localhost:8080/rentmanager/users");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
