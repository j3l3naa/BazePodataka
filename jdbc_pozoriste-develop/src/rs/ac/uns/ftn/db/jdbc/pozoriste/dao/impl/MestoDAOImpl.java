package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.MestoDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Mesto;

public class MestoDAOImpl implements MestoDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Mesto entity) throws SQLException {
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
		String query = "select * from mesto where idm = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}	
	}

	@Override
	public Iterable<Mesto> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Mesto> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mesto findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idm, nazivm, drzava_idd from mesto where idm = ?";
		Mesto m;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				m = new Mesto(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			}
		}
		return m;
	}

	@Override
	public void save(Mesto entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into mesto (nazivm, drzava_idd, idm) values (?, ?, ?)";
		String updateCommand = "update mesto set nazivm = ?, drzava_idd = ? where idm = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement((existsById(entity.getIdm()) ? updateCommand : insertCommand))) {

			preparedStatement.setString(1, entity.getNaziv());
			preparedStatement.setInt(2, entity.getIdd());
			preparedStatement.setInt(3, entity.getIdm());
			
			preparedStatement.executeUpdate();
		}
				
		
	}

	@Override
	public void saveAll(Iterable<Mesto> entities) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection c, Mesto entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into mesto (nazivm, drzava_idd, idm) values (?, ?, ?)";
		String updateCommand = "update mesto set nazivm = ?, drzava_idd = ? where idm = ?";
		
		try (PreparedStatement preparedStatement = c.prepareStatement((existsById(entity.getIdm()) ? updateCommand : insertCommand))) {

			preparedStatement.setString(1, entity.getNaziv());
			preparedStatement.setInt(2, entity.getIdd());
			preparedStatement.setInt(3, entity.getIdm());
			
			preparedStatement.executeUpdate();
		
		}

	}
}
