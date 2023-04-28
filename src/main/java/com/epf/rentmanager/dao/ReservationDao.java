package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ReservationDao {
	@Autowired
	VehicleService vehicleService;

	@Autowired
	ClientService clientService;
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS reservationsCount FROM Reservation;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id=?;";

	public void create(Reservation reservation) throws DaoException {
		Connection connexion = null;
		try {
			connexion = ConnectionManager.getConnection();

			PreparedStatement pstmt = connexion.prepareStatement(CREATE_RESERVATION_QUERY);

			pstmt.setLong(1, reservation.getClient().getId());
			pstmt.setLong(2, reservation.getVehicle().getId());
			pstmt.setDate(3, Date.valueOf(reservation.getStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getEnd()));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);

			pstmt.setLong(1, reservation.getId());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public List<Reservation> findByClient(Client client) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);

			pstmt.setLong(1, client.getId());

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> listReservation = new ArrayList<>();

			while (rs.next()) {

				Integer reservationId = rs.getInt("id");

				Vehicle vehicle = vehicleService.findById(rs.getLong("vehicle_id"));
				LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationFin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, client, vehicle, reservationDebut, reservationFin);

				listReservation.add(reservation);

			}

			return listReservation;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public List<Reservation> findResaByReservation(long vehicle) throws DaoException {
		return new ArrayList<Reservation>();
	}

	public List<Reservation> findAll() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			List<Reservation> reservations = new ArrayList<>();

			while (rs.next()) {
				long id = rs.getLong("id");
				Client client = clientService.findById(rs.getLong("client_id"));
				Vehicle vehicle = vehicleService.findById(rs.getLong("vehicle_id"));
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client, vehicle, start, end));
			}
			return reservations;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	public Reservation findById(long id) throws DaoException{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATION_QUERY);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			Client reservationClient = clientService.findById(rs.getLong("client_id"));
			Vehicle reservationVehicle = vehicleService.findById(rs.getLong("vehicle_id"));
			LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
			LocalDate reservationFin = rs.getDate("fin").toLocalDate();

			Reservation reservation = new Reservation(id, reservationClient, reservationVehicle, reservationDebut, reservationFin);

			return reservation;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
	}
	public long count() throws DaoException {
		int reservationsCount = 1;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationsCount = rs.getInt(reservationsCount);
			}

			return reservationsCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public long update(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESERVATION_QUERY);

			pstmt.setLong(1, reservation.getClient().getId());
			pstmt.setLong(2, reservation.getVehicle().getId());
			pstmt.setDate(3, Date.valueOf(reservation.getStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getEnd()));
			pstmt.setLong(5, reservation.getId());


			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
