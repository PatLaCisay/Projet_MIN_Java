package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "ReservationCreateServlet", value = "/rents/create")
public class ReservationCreateServlet extends HttpServlet {

    private static ReservationService reservationService;
    private static ClientService clientService;
    private static VehicleService vehicleService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("listClients", this.clientService.getInstance().findAll());
            request.setAttribute("listVehicles", this.vehicleService.getInstance().findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client;
        Vehicle vehicle;
        LocalDate start;
        LocalDate end;

        try {
            client = clientService.getInstance().findById(Long.parseLong(request.getParameter("client")));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        try {
            vehicle = vehicleService.getInstance().findById(Long.parseLong(request.getParameter("vehicle")));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        start = LocalDate.parse(request.getParameter("start"));
        end = LocalDate.parse(request.getParameter("end"));

        Reservation reservation = new Reservation(client,vehicle,end,start);

        try {
            this.reservationService.getInstance().create(reservation);
            response.sendRedirect("http://localhost:8080/rentmanager/rents");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
