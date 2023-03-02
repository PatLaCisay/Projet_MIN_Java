package com.epf.rentmanager.ui;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.model.Client;

import java.util.List;

public class Test {
    public static void main(String[] arg)
    {
        try{
            List<Client> clients = ClientService.getInstance().findAll();
            System.out.println(clients.size());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}
