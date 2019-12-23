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
import rs.ac.uns.ftn.db.jdbc.exam.dao.RadnikDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.RadprojDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.RadnikDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.SefoviBrcDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.SefoviDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public class RadnikDAOImpl implements RadnikDAO {
	private static final RadprojDAO radprojDAO = new RadProjDAOImpl();
	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select count(*) from radnik";
		
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
	public void delete(Radnik entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from radnik where mbr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, entity.getMbr());
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from radnik";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
	String query = "delete from radnik where mbr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from radnik where mbr = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Radnik> findAll() throws SQLException {
		// TODO Auto-generated method stub
		//public Radnik(int mbr, String ime, String prz, int sef, double plt, double pre, Date god)
		String query = "select mbr, ime, prz, sef, plt, pre, god from radnik";
		List<Radnik> radniciList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				java.util.Date datum = new java.util.Date(resultSet.getDate(7).getTime());
				radniciList.add(new Radnik(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6), datum));
				
			}
		}
		
		return radniciList;
	}

	@Override
	public Iterable<Radnik> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select mbr, ime, prz, sef, plt, pre, god from radnik where mbr in (";
		StringBuilder stringBuilder = new StringBuilder();
		List<Radnik> radniciList = new ArrayList<>();
		
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
					java.util.Date datum = new java.util.Date(resultSet.getDate(7).getTime());
					radniciList.add(new Radnik(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6), datum));
				}
			}
		}
		
		
		return radniciList;
	}

	@Override
	public Radnik findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select mbr, ime, prz, sef, plt, pre, god from radnik where mbr = ?";
		Radnik r = null;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					java.util.Date datum = new java.util.Date(resultSet.getDate(7).getTime());
					r = new Radnik(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6), datum);
				}
			}
		}

		return r;
	}

	@Override
	public void save(Radnik entity) throws SQLException {
		// TODO Auto-generated method stub
		//public Radnik(int mbr, String ime, String prz, int sef, double plt, double pre, Date god)
		String insertCommand = "insert into radnik (ime, prz, sef, plt, pre, god, mbr) values (?,?,?,?,?,?,?)";
		String updateCommand = "update radnik set ime = ?, prz = ?, plt = ?, sef = ?, pre = ?, god = ? where mbr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatementU = connection.prepareStatement(updateCommand);
				PreparedStatement preparedStatementI = connection.prepareStatement(insertCommand)) {
			
			PreparedStatement preparedStatement;
			
			if(existsById(entity.getMbr())) {
				preparedStatement = preparedStatementU;
			} else {
				preparedStatement = preparedStatementI;
			}
			
			preparedStatement.setString(1, entity.getIme());
			preparedStatement.setString(2, entity.getPrz());
			preparedStatement.setInt(3, entity.getSef());
			preparedStatement.setDouble(4, entity.getPlt());
			preparedStatement.setDouble(5, entity.getPre());
			preparedStatement.setDate(6, new java.sql.Date (entity.getGod().getTime()));
			preparedStatement.setInt(7, entity.getMbr());
			
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void saveAll(Iterable<Radnik> entities) throws SQLException {
		// TODO Auto-generated method stub
		
		String insertCommand = "insert into radnik (ime, prz, sef, plt, pre, god, mbr) values (?,?,?,?,?,?,?)";
		String updateCommand = "update radnik set ime = ?, prz = ?, sef = ?, plt = ?,  pre = ?, god = ? where mbr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedInsert = connection.prepareStatement(insertCommand);
				PreparedStatement preparedUpdate = connection.prepareStatement(updateCommand)) {
				
			connection.setAutoCommit(false);
				PreparedStatement preparedStatement;
			for (Radnik entity : entities) {
				
				if(existsById(entity.getMbr())) {
					preparedStatement = preparedUpdate;
				} else {
					preparedStatement = preparedInsert;
				}
			
				preparedStatement.setString(1, entity.getIme());
				preparedStatement.setString(2, entity.getPrz());
				
				preparedStatement.setInt(3, entity.getSef());
				preparedStatement.setDouble(4, entity.getPlt());
				preparedStatement.setDouble(5, entity.getPre());
				
				
				preparedStatement.setDate(6, (java.sql.Date)entity.getGod());
				preparedStatement.setInt(7, entity.getMbr());
			
				preparedStatement.executeUpdate();
			}
			connection.commit();
		}
	}

	@Override
	public List<SefoviDTO> findSefovi() throws SQLException {
		// TODO Auto-generated method stub
		List<SefoviDTO> sefoviList = new ArrayList<>();
		
		String query = "select distinct r1.mbr, r1.ime, r1.prz, count(r.mbr) "
				+ "from radnik r, radnik r1 "
				+ "where r.sef = r1.mbr "
				+ "group by r1.mbr, r1.ime, r1.prz";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next()) {
				sefoviList.add(new SefoviDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
			}
		}
		return sefoviList;
	}

	@Override
	public List<RadnikDTO> findPodredjeni(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		List<RadnikDTO> radniciList = new ArrayList<>();
		String query = "select mbr, ime, prz from radnik where sef = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id); 
			
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				while(resultSet.next()) {
					radniciList.add(new RadnikDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
				}
			}
		}
		
		return radniciList;
	}

	@Override
	public List<RadnikDTO> findRukovodiociProjekata() throws SQLException {
		// TODO Auto-generated method stub
		List<RadnikDTO> radniciList = new ArrayList<>();
		String query = "select r.mbr, r.ime, r.prz, count(spr) from radnik r, projekat p where r.mbr = p.ruk group by r.mbr, r.ime, r.prz having count(spr) < ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setDouble(1, findAvgWithLastnameIc());
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while(resultSet.next()) {
					radniciList.add(new RadnikDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
				}
			}
		}
		
		
		
		return radniciList;
	}

	public double findAvgWithLastnameIc() throws SQLException {
		String query = "select avg(spr) from radproj, radnik where radproj.mbr = radnik.mbr and prz not like '%ic group by radnik.mbr'";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if(resultSet.isBeforeFirst()) {
				resultSet.next();
				return resultSet.getDouble(1);
			} else {
				return -1;
			}
		}
	}

	@Override
	public List<SefoviBrcDTO> findAngSef() throws SQLException {
		// TODO Auto-generated method stub
		List<SefoviBrcDTO> sefoviAng = new ArrayList<>();
		HashMap<Integer, Integer> angazovanja = radprojDAO.findAngazovanja();
		
		for (SefoviDTO s : findSefovi()) {
			sefoviAng.add(new SefoviBrcDTO(s.getMbr(), s.getIme(), s.getPrz(), angazovanja.get(s.getMbr())));
		}
		
		return sefoviAng;
	}
}
