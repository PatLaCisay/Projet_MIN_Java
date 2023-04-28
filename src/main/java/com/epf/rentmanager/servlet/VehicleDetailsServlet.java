package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cars/details")

public class VehicleDetailsServlet extends HttpServlet {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        List<Reservation> listReservations = new ArrayList<>();

        Vehicle vehicle = new Vehicle();

        try {
            vehicle = vehicleService.findById(id);
            listReservations = reservationService.findByVehicle(vehicle);
            int nbReservations = listReservations.size();
            request.setAttribute("vehicle", vehicle);
            request.setAttribute("listReservations", listReservations);
            request.setAttribute("nbReservations", nbReservations);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {

    }
}
