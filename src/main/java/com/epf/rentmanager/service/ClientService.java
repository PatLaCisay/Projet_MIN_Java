package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public void create(Client client) throws ServiceException {
		try {
			ClientDao.getInstance().create(client);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
	}


	public List<Client> findAll() throws ServiceException {
		try {
			return ClientDao.getInstance().findAll();
		} catch (DaoException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Client findById(long id) throws ServiceException {
		try {
			return ClientDao.getInstance().findById(id);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public long count() throws ServiceException {
		try {
			return this.clientDao.count();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
