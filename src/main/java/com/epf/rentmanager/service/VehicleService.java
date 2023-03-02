package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
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

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return VehicleDao.getInstance().findAll();
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return VehicleDao.getInstance().findById(id);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
	}
	public long count() throws ServiceException {
		try {
			return this.vehicleDao.count();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
