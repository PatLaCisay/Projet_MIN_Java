package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/users/update")

public class UserUpdateServlet extends HttpServlet {

    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static final long serialVersionUID = 1L;

    int id = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            id = Integer.parseInt(request.getParameter("id"));

            Client client = this.clientService.findById(id);

            request.setAttribute("client", client);

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName;
        String lastName;
        String email;
        LocalDate birthDate;

        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        email = request.getParameter("email");
        birthDate = LocalDate.parse(request.getParameter("birthDate"));


        Client client = new Client(id, firstName, lastName, birthDate, email);

        try {
            this.clientService.update(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.sendRedirect("http://localhost:8080/rentmanager/users");

    }
}
