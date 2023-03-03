package com.epf.rentmanager.ui;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.model.Vehicle;

import java.util.List;

public class Test {
    public static void main(String[] arg)
    {
        try{
            List<Vehicle> clients = VehicleService.getInstance().findAll();
            System.out.println(clients);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}
