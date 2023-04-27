package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epf.rentmanager.persistence.ConnectionManager.getConnection;
@Repository
public class ClientDao {

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS clientsCount FROM Client;";
	
	public void create(Client client) throws DaoException {
		Connection connexion = null;
		try {
			connexion = ConnectionManager.getConnection();

			PreparedStatement pstmt = connexion.prepareStatement(CREATE_CLIENT_QUERY);

			pstmt.setString(1, client.getLastName());
			pstmt.setString(2, client.getFirstName());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthDate()));

			pstmt.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public long delete(Client client) throws DaoException {
		return 0;
	}

	public List<Client> findAll() throws DaoException, SQLException {
		List<Client> clients = new ArrayList<Client>();
		try {
			Connection connexionection = getConnection();

			Statement statement = connexionection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				long id = rs.getLong("id");
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

	public Client findById(long id) throws DaoException, SQLException {

		Client client = new Client();

		try {
			Connection connexionection = getConnection();

			Statement statement = connexionection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				if (rs.getLong("id")==id){
					String firstName = rs.getString("prenom");
					String lastName = rs.getString("nom");
					String email = rs.getString("email");
					LocalDate date = rs.getDate("naissance").toLocalDate();
					client =  new Client(id, firstName, lastName, date, email);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (client.getFirstName().isEmpty())
		{
			throw new DaoException();
		}
		return client;
	}
	public long count() throws DaoException {
		int clientsCount = 1;
		try {

			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(COUNT_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				clientsCount = rs.getInt(clientsCount);
			}

			return clientsCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
