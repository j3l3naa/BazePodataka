package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public class ScenaDAOImpl implements ScenaDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from scena";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		}
	}

	@Override
	public void delete(Scena entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from scena where idsce = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, entity.getIdsce());
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from scena where idsce = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from scena where idsce = ? ";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Scena> findAll() throws SQLException {
		String query = "select idsce, nazivsce, brojsed, pozoriste_idpoz from scena";
		List<Scena> scenaList = new ArrayList<Scena>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Scena scena = new Scena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getInt(4));
				scenaList.add(scena);
			}

		}
		return scenaList;
	}

	@Override
	public Iterable<Scena> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idsce, nazivsce, brojsed, pozoriste_idpoz from scena where idsce in (";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(query);
		List<Scena> scenaList = new ArrayList<>();

		
		for(Integer id : ids) {
			stringBuilder.append("?,");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString())) {
			int i = 1;
			for(Integer id : ids) {
				preparedStatement.setInt(i++, id);
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while(resultSet.next()) {
					scenaList.add(new Scena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
				}
			}
		}
		
		return scenaList;
	}

	@Override
	public Scena findById(Integer id) throws SQLException {
		String query = "select idsce, nazivsce, brojsed, pozoriste_idpoz from scena where idsce = ?";
		Scena scena = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					scena = new Scena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
							resultSet.getInt(4));
				}
			}
		}

		return scena;
	}

	@Override
	public void save(Scena entity) throws SQLException {
		// TODO Auto-generated method stub
		String updateCommand = "update scena set nazivsce = ?, brojsed = ?, pozoriste_idpoz = ? where idsce = ?";
		String insertCommand = "insert into scena (nazivsce, brojsed, pozoriste_idpoz, idsce) values (?, ?, ?, ?)";
		String query;
		
		if(existsById(entity.getIdsce())) {
			query = updateCommand;
		} else 
			query = insertCommand;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setString(1, entity.getNazivsce());
			preparedStatement.setInt(2, entity.getBrojsed());
			preparedStatement.setInt(3, entity.getIdpoz());
			preparedStatement.setInt(4, entity.getIdsce());
			
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public void saveAll(Iterable<Scena> entities) throws SQLException {
		// TODO Auto-generated method stub

		String updateCommand = "update scena set nazivsce = ?, brojsed = ?, pozoriste_idpoz = ? where idsce = ?";
		String insertCommand = "insert into scena (nazivsce, brojsed, pozoriste_idpoz, idsce) values (?, ?, ?, ?)";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateCommand);
				PreparedStatement preparedStatementInsert = connection.prepareStatement(insertCommand)) {
			connection.setAutoCommit(false);
		
			for(Scena s : entities) {
				int i = 1;
				PreparedStatement preparedStatement;
				if(existsById(s.getIdsce())) {
					preparedStatement = preparedStatementUpdate;
				} else {
					preparedStatement = preparedStatementInsert;
				}
				
				preparedStatement.setString(i++, s.getNazivsce());
				preparedStatement.setInt(i++, s.getBrojsed());
				preparedStatement.setInt(i++, s.getIdpoz());
				preparedStatement.setInt(i, s.getIdsce());
				
				preparedStatement.execute();
				
			}

			connection.commit();
		}
	
	}

	public List<Scena> findSceneByTheatre(Integer theatreId) throws SQLException {
		String query = "select idsce,nazivsce, brojsed ,pozoriste_idpoz from scena where pozoriste_idpoz = ?";
		List<Scena> scenaList = new ArrayList<Scena>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, theatreId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					Scena scena = new Scena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
							resultSet.getInt(4));
					scenaList.add(scena);
				}
			}
		}

		return scenaList;
	}

	@Override
	public List<Scena> findSceneForSpecificNumberOfSeats() throws SQLException {
		String query = "SELECT S.idsce,S.nazivsce, S.brojsed, S.pozoriste_idpoz "
				+ "FROM Scena S, Pozoriste P, Scena S1, Pozoriste P1 "
				+ "WHERE S.pozoriste_idpoz = P.idpoz AND S.brojsed >= S1.brojsed*0.8 AND "
				+ "S.brojsed <= S1.brojsed*1.2 AND S1.nazivsce = 'Scena Joakim Vujic' AND S1.pozoriste_idpoz = P1.idpoz AND "
				+ "P1.nazivpoz = 'Knjazevsko-srpski teatar Kragujevac'";
		List<Scena> scenaList = new ArrayList<Scena>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Scena scena = new Scena(resultSet.getInt(4), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getInt(4));
				scenaList.add(scena);
			}

		}
		return scenaList;
	}
	
	@Override
	public void save(Connection c, Scena s) throws SQLException {
		String insertCommand = "insert into scena (nazivsce, brojsed, pozoriste_idpoz, idsce) values (?, ?, ?, ?)";
		String updateCommand = "update scena set nazivsce = ?, brojsed = ?, pozoriste_idpoz = ? where idsce = ?";
		
		try (PreparedStatement preparedStatement = c.prepareStatement((existsById(s.getIdsce()) ? updateCommand : insertCommand))) {

			preparedStatement.setString(1, s.getNazivsce());
			preparedStatement.setInt(2, s.getBrojsed());
			preparedStatement.setInt(3, s.getIdpoz());
			preparedStatement.setInt(4, s.getIdsce());
			
			preparedStatement.executeUpdate();
		}
	}

}
