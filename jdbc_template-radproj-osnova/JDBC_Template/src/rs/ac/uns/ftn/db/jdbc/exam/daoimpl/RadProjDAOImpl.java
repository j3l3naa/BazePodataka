package rs.ac.uns.ftn.db.jdbc.exam.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.dao.RadprojDAO;
import rs.ac.uns.ftn.db.jdbc.exam.model.RadProj;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public class RadProjDAOImpl implements RadprojDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select count(*) from radproj";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
		
			if(resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
			
		}
	}

	@Override
	public void delete(RadProj entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from radnik where mbr = ? and spr = ?";
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, entity.getMbr());
			preparedStatement.setInt(2, entity.getSpr());
			preparedStatement.executeUpdate();
			
		}
		
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from radproj";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.executeUpdate();
		}
		
		
	}

	@Override
	public void deleteById(List<Integer> id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from radnik where mbr = ? and spr = ?";
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id.get(0));
			preparedStatement.setInt(2, id.get(1));
			preparedStatement.executeUpdate();
			
		}
		
	}

	@Override
	public boolean existsById(List<Integer> id) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select * from radproj where mbr = ? and spr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id.get(0));
			preparedStatement.setInt(2, id.get(1));
			
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<RadProj> findAll() throws SQLException {
		// TODO Auto-generated method stub
		List<RadProj> radprojList = new ArrayList<>();
		String query = "select mbr, spr, brc from radproj";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next()) {
				radprojList.add(new RadProj(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
			}
		}
		
		
		return radprojList;
	}

	public Iterable<RadProj> findAllById(Iterable<List<Integer>> ids) throws SQLException {
		// TODO Auto-generated method stub
		String query1 = "select mbr, spr, brc from radproj where mbr in (";
		String query2 = " and spr in (";
		
		List<RadProj> radprojList = new ArrayList<>();
		StringBuilder stringBuilderStart = new StringBuilder();
		stringBuilderStart.append(query1);
		
		StringBuilder stringBuilderEnd = new StringBuilder();
		stringBuilderEnd.append(query2);
		
		for (List<Integer> i : ids) {
			stringBuilderStart.append("?, ");
			stringBuilderEnd.append("?, ");
		}
		
		stringBuilderStart.deleteCharAt(stringBuilderStart.length() - 1);
		stringBuilderEnd.deleteCharAt(stringBuilderEnd.length() - 1);
		
		String query = "select * from radproj where mbr = ? and spr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			for (List<Integer> id : ids) {
			preparedStatement.setInt(1, id.get(1));
			preparedStatement.setInt(2, id.get(2));
			
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					while(resultSet.next()) {
						radprojList.add(new RadProj(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
					}
				}
			}
		}
		return radprojList;
	}

	@Override
	public RadProj findById(List<Integer> id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select mbr, spr, brc from radproj where mbr = ? and spr = ?";
		RadProj rp = null;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id.get(0));
			preparedStatement.setInt(2, id.get(1));
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if(resultSet.isBeforeFirst()) {
				resultSet.next();
				
				rp = new RadProj(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
			}
			
			}
		}
		return rp;
	}

	@Override
	public void save(RadProj entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into radproj (brc, spr, mbr) values (?, ?, ?)";
		String updateCommand = "update radproj set brc = ? where spr = ? and mbr = ?";
		List<Integer> ids = new ArrayList<>();
		ids.add(entity.getMbr());
		ids.add(entity.getSpr());
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement((existsById(ids) ? updateCommand : insertCommand))) {
					
				preparedStatement.setInt(1, entity.getBrc());
				preparedStatement.setInt(2, entity.getSpr());
				preparedStatement.setInt(3, entity.getMbr());
				
				preparedStatement.executeUpdate();
				}
	}

	@Override
	public void saveAll(Iterable<RadProj> entities) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Radnik> getPunishedWorkers() throws SQLException {
		// TODO Auto-generated method stub
		List<Radnik> radniciList = new ArrayList<>();
		String query = "select r.mbr, ime, prz, sef, plt, pre, god from radnik r, radproj rp where r.mbr = rp.mbr and spr = 10";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
		/*
		 * 
	public Radnik(int mbr, String ime, String prz, int sef, double plt, double pre, Date god)	
		 */
			while(resultSet.next()) {
				radniciList.add(new Radnik(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6), new java.util.Date(resultSet.getDate(7).getTime())));
			}
		}
		return radniciList;
	}

	@Override
	public void updatePunishedWorkers(List<Radnik> radnici) {
		// TODO Auto-generated method stub
		
		String update = "update radproj set brc = brc * 0.5 where mbr = ?";
		
	}

	@Override
	public void createTempTable() throws SQLException {
		// TODO Auto-generated method stub
		String createTableRadnik2Command = "create table radnik2 as select mbr, ime, prz, plt from radnik"
				+ " where mbr in(select mbr from radproj where spr=10)";
		String alterTableRadnik2Command = "alter table radnik2 add constraint CH_PLT check (plt > 5000)";
		String createTableRadProj2Command = "create table radproj2 as select mbr, spr, brc from radproj where spr=10";

		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement1 = connection.prepareStatement(createTableRadProj2Command);
				PreparedStatement preparedStatement2 = connection.prepareStatement(createTableRadnik2Command);
				PreparedStatement preparedStatement3 = connection.prepareStatement(alterTableRadnik2Command);
				) {
			
			preparedStatement1.execute();
			preparedStatement2.execute();
			preparedStatement3.execute();
		}
		
	}

	@Override
	public void executeTransactional() throws SQLException {
		// TODO Auto-generated method stub
		String updateBrc = "update radproj2 set brc = brc * 0.5";
		String updatePlt = "update radnik2 set plt = plt * 0.5";
	
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false);
	
			try (PreparedStatement preparedStatementU1 = connection.prepareStatement(updatePlt); 
				PreparedStatement preparedStatementU2 = connection.prepareStatement(updateBrc)) {
				preparedStatementU1.executeUpdate();
				preparedStatementU2.executeUpdate();
				
			} catch(SQLException e) {
				System.out.println(e.getMessage());
				connection.rollback();
			}
		
			connection.commit();
		}
	}

	@Override
	public HashMap<Integer, Integer> findAngazovanja() throws SQLException {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> angazovanja = new HashMap<>();
		String query = "select mbr, sum(brc) from radproj group by mbr";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
					
				while (resultSet.next()) {
					angazovanja.put(resultSet.getInt(1), resultSet.getInt(2));
				}
			}
		
		return angazovanja;
	} 

}
