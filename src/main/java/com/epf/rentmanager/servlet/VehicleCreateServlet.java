package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

@WebServlet(name = "VehicleCreateServlet", value = "/cars/create")
public class VehicleCreateServlet extends HttpServlet {

    VehicleService vehicleService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String constructor;
        int seats;
        String model;

        constructor = request.getParameter("constructor");
        seats = parseInt(request.getParameter("seats"));
        model = request.getParameter("model");

        Vehicle vehicle = new Vehicle(constructor, seats, model);
        try {
            this.vehicleService.getInstance().create(vehicle);
            response.sendRedirect("http://localhost:8080/rentmanager/cars");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
