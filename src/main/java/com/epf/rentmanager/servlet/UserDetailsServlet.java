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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));

        Client client;
        try {
            client = clientService.findById(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Vehicle> listVehicles = new ArrayList<>();
            client = clientService.findById(id);
            List<Reservation> listRents = reservationService.findByClient(client);
            Vehicle vehicle;
            for (Reservation rent : listRents) {
                vehicle=rent.getVehicle();
                if(!listVehicles.contains(vehicle)){
                    listVehicles.add(vehicle);
                }
            }
            int nbRents = listRents.size();
            int nbVehicle = listVehicles.size();

            request.setAttribute("client", client);
            request.setAttribute("listRents", listRents);
            request.setAttribute("listVehicles", listVehicles);
            request.setAttribute("nbRents", nbRents);
            request.setAttribute("nbVehicle", nbVehicle);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
