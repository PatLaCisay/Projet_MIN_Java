package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/update")

public class VehicleUpdateServlet extends HttpServlet{
    @Autowired
    VehicleService vehicleService;

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

            Vehicle vehicle = this.vehicleService.findById(id);

            request.setAttribute("vehicle", vehicle);

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String constructor;
        String model;
        Integer seats;

        constructor = request.getParameter("constructor");
        seats = Integer.valueOf(request.getParameter("seats"));
        model = request.getParameter("model");


        Vehicle vehicle = new Vehicle(id, constructor, seats, model);

        try {
            this.vehicleService.update(vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.sendRedirect("http://localhost:8080/rentmanager/cars");

    }
}