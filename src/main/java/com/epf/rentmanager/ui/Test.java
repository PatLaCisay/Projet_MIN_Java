package com.epf.rentmanager.ui;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.model.Reservation;

import java.util.List;

public class Test {
    public static void main(String[] arg)
    {
        try{
            List<Reservation> clients = ReservationService.getInstance().findAll();
            System.out.println(clients);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}
