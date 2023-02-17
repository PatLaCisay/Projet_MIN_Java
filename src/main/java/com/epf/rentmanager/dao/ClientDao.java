package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

import static com.epf.rentmanager.persistence.ConnectionManager.getConnection;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		return 0;
	}
	
	public long delete(Client client) throws DaoException {
		return 0;
	}

	public Client findById(long id, String prenom, String nom, LocalDate naissance, String email) throws DaoException {
		return new Client(id, prenom, nom, naissance, email);
	}

	public List<Client> findAll() throws DaoException, SQLException {
		List<Client> clients = new ArrayList<Client>();
		try {
			Connection connection = getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				long id = rs.getInt("id");
				String firstName = rs.getString("prenom");
				String lastName = rs.getString("nom");
				String email = rs.getString("email");
				LocalDate date = rs.getDate("naissance").toLocalDate();


				clients.add( new Client(id, firstName, lastName, date, email));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

		return clients;
	}

}
