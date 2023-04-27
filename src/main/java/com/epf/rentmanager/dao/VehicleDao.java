package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epf.rentmanager.persistence.ConnectionManager.getConnection;
@Repository
public class VehicleDao {
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places, modele) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places, modele FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places, modele FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS vehiclesCount FROM Vehicle;";

	public void create(Vehicle vehicle) throws DaoException {
		Connection connexion = null;
		try {
			connexion = ConnectionManager.getConnection();

			PreparedStatement pstmt = connexion.prepareStatement(CREATE_VEHICLE_QUERY);

			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setInt(2, vehicle.getSeats());
			pstmt.setString(3, vehicle.getModel());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

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
					String constructor = rs.getString("constructeur");
					int seats = rs.getInt("nb_places");
					String model = rs.getString("modele");
					vehicle =  new Vehicle(id, constructor, seats, model);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (vehicle.getConstructor().isEmpty())
		{
			throw new DaoException();
		}
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<>();
		try {
			Connection connection = getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				long id = rs.getLong("id");
				String constructor = rs.getString("constructeur");
				int seats = rs.getInt("nb_places");
				String model = rs.getString("modele");
				vehicles.add( new Vehicle(id, constructor, seats, model));
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
