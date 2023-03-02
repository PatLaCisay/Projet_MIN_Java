package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

import static com.epf.rentmanager.persistence.ConnectionManager.getConnection;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS clientsCount FROM Client;";
	
	public long create(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public Vehicle findById(long id) throws DaoException {
		Vehicle vehicle = new Vehicle();

		try {
			Connection connection = getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				if (rs.getLong("id")==id){
					String constructeur = rs.getString("constructeur");
					int nb_places = rs.getInt("nb_places");
					vehicle =  new Vehicle(id, constructeur, nb_places);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (vehicle.getConstructeur().isEmpty())
		{
			throw new DaoException();
		}
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			Connection connection = getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				long id = rs.getLong("id");
				String constructeur = rs.getString("constructeur");
				int nb_places = rs.getInt("nb_places");

				vehicles.add( new Vehicle(id, constructeur, nb_places));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

		return vehicles;
	}
	public long count() throws DaoException {
		int vehiclesCount=1;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vehiclesCount = rs.getInt(vehiclesCount);
			}

			return vehiclesCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
