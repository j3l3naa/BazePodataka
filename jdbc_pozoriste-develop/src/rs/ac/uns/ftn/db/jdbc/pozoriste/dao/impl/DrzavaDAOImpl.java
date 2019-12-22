package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.DrzavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Drzava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Mesto;

public class DrzavaDAOImpl implements DrzavaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Drzava entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from drzava where idd = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}	
	}

	@Override
	public Iterable<Drzava> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Drzava> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drzava findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idd, nazivd from drzava where idd = ?";
		Drzava d;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				d = new Drzava(resultSet.getInt(1), resultSet.getString(2));
			}
		}
		return d;
	}

	@Override
	public void save(Drzava entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into drzava (nazivd, idd) values (?, ?)";
		String updateCommand = "update drzava set nazivd = ? where idd = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement((existsById(entity.getIdd()) ? updateCommand : insertCommand))) {

			preparedStatement.setString(1, entity.getNaziv());
			preparedStatement.setInt(2, entity.getIdd());
			
			preparedStatement.executeUpdate();
		}
				
		
	}

	@Override
	public void saveAll(Iterable<Drzava> entities) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection c, Drzava entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into drzava (nazivd, idd) values (?, ?)";
		String updateCommand = "update drzava set nazivd = ? where idd = ?";
		
		try (PreparedStatement preparedStatement = c.prepareStatement((existsById(entity.getIdd()) ? updateCommand : insertCommand))) {

			preparedStatement.setString(1, entity.getNaziv());
			preparedStatement.setInt(2, entity.getIdd());
			
			preparedStatement.executeUpdate();
		}
		
		
	}

}
