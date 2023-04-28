package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {

    private ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }



    public void create(Reservation reservation) throws ServiceException {
        try {
            this.reservationDao.create(reservation);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Reservation> findAll() throws ServiceException {
        try {
            return this.reservationDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Reservation> findByClient(Client client)throws ServiceException {
        try {
            return this.reservationDao.findByClient(client);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Reservation> findByVehicle(Vehicle vehicle)throws ServiceException {
        try {
            return this.reservationDao.findByVehicle(vehicle);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    public Reservation findById(long id) throws ServiceException {
        try {
            return this.reservationDao.findById(id);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    public long count() throws ServiceException {
        try {
            return this.reservationDao.count();
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long delete(Reservation reservation) throws ServiceException {
        try {
            return this.reservationDao.delete(reservation);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long update(Reservation reservation) throws ServiceException {
        try {
            return this.reservationDao.update(reservation);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
