package rs.ac.uns.ftn.db.jdbc.exam.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.dao.ProjekatDAO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Projekat;

public class ProjekatDAOImpl implements ProjekatDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select count(*) from projekat";
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			if(resultSet.next())
				return resultSet.getInt(1);
			else 
				return -1;
		}
	}

	@Override
	public void delete(Projekat entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from projekat where spr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, entity.getSpr());
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from projekat";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.executeUpdate();
		}
		
		
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
	String query = "delete from projekat where spr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from projekat where spr = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
		
	}

	@Override
	public Iterable<Projekat> findAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select spr, nap, ruk, nar from projekat";
		List<Projekat> projektiList = new ArrayList<>();
		//public Projekat(int spr, int ruk, String nap, String nar)
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				projektiList.add(new Projekat(resultSet.getInt(1), resultSet.getInt(3), resultSet.getString(2), resultSet.getString(4)));
			}
		}
		
		return projektiList;
	}

	@Override
	public Iterable<Projekat> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select spr, nap, ruk, nar from projekat where spr in(";
		List<Projekat> projektiList = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		
		
		stringBuilder.append(query);
		
		for (Integer id : ids) {
			stringBuilder.append("?, ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			int i = 1;
			for (Integer id : ids) {
				preparedStatement.setInt(i++, id);
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while(resultSet.next()) {
					projektiList.add(new Projekat(resultSet.getInt(1), resultSet.getInt(3), resultSet.getString(2), resultSet.getString(4)));
				}
			}
		}
		
		
		return projektiList;
	}

	@Override
	public Projekat findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select spr, nap, ruk, nar from projekat where spr = ?";
		Projekat p = null;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					p = new Projekat(resultSet.getInt(1), resultSet.getInt(3), resultSet.getString(2), resultSet.getString(4));
				}
			}
		}
		return p;
	}
	@Override
	public void save(Projekat entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into projekat (ruk, nap, nar, spr) values (?, ?, ?, ?)";
		String updateCommand = "update projekat set ruk = ?, nap = ?, nar = ? where spr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement((existsById(entity.getSpr()) ? updateCommand : insertCommand))) {
					
				preparedStatement.setInt(1, entity.getRuk());
				preparedStatement.setString(2, entity.getNap());
				preparedStatement.setString(3, entity.getNar());
				preparedStatement.setInt(4, entity.getSpr());
				
				
				
				preparedStatement.executeUpdate();
				}
		
		
	}

	@Override
	public void saveAll(Iterable<Projekat> entities) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into projekat (ruk, nap, nar, spr) values (?, ?, ?, ?)";
		String updateCommand = "update projekat set ruk = ?, nap = ?, nar = ? where spr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatementU = connection.prepareStatement(updateCommand);
				PreparedStatement preparedStatementI = connection.prepareStatement(insertCommand)) {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement;
			for (Projekat entity : entities) {
			
				if(existsById(entity.getSpr())) {
					preparedStatement = preparedStatementU; 
				} else {
					preparedStatement = preparedStatementI;
				}
				
				preparedStatement.setInt(1, entity.getRuk());
				preparedStatement.setString(2, entity.getNap());
				preparedStatement.setString(3, entity.getNar());
				preparedStatement.setInt(4, entity.getSpr());
			
				preparedStatement.executeUpdate();
			}
		}	
	
	}
}