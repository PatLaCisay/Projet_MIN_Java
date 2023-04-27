package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ReservationCreateServlet", value = "/rents/create")
public class ReservationCreateServlet extends HttpServlet {

    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("listClients", this.clientService.findAll());
            request.setAttribute("listVehicles", this.vehicleService.findAll());
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
            client = clientService.findById(Long.parseLong(request.getParameter("client")));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        try {
            vehicle = vehicleService.findById(Long.parseLong(request.getParameter("vehicle")));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        start = LocalDate.parse(request.getParameter("start"));
        end = LocalDate.parse(request.getParameter("end"));

        Reservation reservation = new Reservation(client,vehicle,end,start);

        try {
            this.reservationService.create(reservation);
            response.sendRedirect("http://localhost:8080/rentmanager/rents");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
