package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	/*public long create(long id, String constructeur, int nb_places) throws ServiceException {
		// TODO: créer un véhicule
		return new Vehicle(id, constructeur, nb_places);
	}

	public Vehicle findById(long id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		Vehicle vehicle = new Vehicle();
		return vehicle.getId(id);
	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		
	}
	*/
	
}
