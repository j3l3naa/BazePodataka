package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.UlogaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorarUdeo;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciUlogeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.OstaliGlumciDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;

public class UlogaDAOImpl implements UlogaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select count(*) from uloga";
	
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			return resultSet.getInt(1);
		}
	}
	

	@Override
	public void delete(Uloga entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from uloga where idul = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, entity.getIdulo());
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from uloga";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from uloga where idul = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select * from uloga where idul = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	/*
	 * 	private String imeulo;
	private String pol;
	private String vrstaulo;
	private int idpred;
	 */
	@Override
	public Iterable<Uloga> findAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idul, imeulo, pol, vrstaulo, predstava_idpred from uloga";
		
		List<Uloga> ulogaList = new ArrayList<>();
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				ulogaList.add(new Uloga(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
			}
		}
		return ulogaList;
	}
	/*
	 * 	private String imeulo;
	private String pol;
	private String vrstaulo;
	private int idpred;
	 */
	@Override
	public Iterable<Uloga> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		List<Uloga> ulogaList = new ArrayList<>();
		String query = "select idul, imeulo, pol, vrstaulo, predstava_idpred from uloga where idul in (";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(query);
		
		for (Integer id : ids) {
			stringBuilder.append("?, ");
		}
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			int i = 1;
			for (Integer id : ids) {
				preparedStatement.setInt(i++, id);
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while(resultSet.next()) {
					
					ulogaList.add(new Uloga(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
				}
			}
		}
		
		return ulogaList;
	}

	@Override
	public Uloga findById(Integer id) throws SQLException {

		return null;

	}

	@Override
	public void save(Uloga entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into uloga (idul, imeulo, pol, vrstaulo, predstava_idpred) values (?, ?, ?, ?, ?)";
		String updateCommand = "update uloga set imeulo = ?, pol = ?, vrstaulo = ?, predstava_idpred where idul = ?";

	}

	@Override
	public void saveAll(Iterable<Uloga> entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	public List<Uloga> findRoleByTheatreId(int idpred) throws SQLException {
		String query = "select imeulo, pol, vrstaulo,predstava_idpred, idulo from uloga where predstava_idpred = ?";
		List<Uloga> result = new ArrayList<Uloga>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, idpred);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					Uloga u = new Uloga(resultSet.getInt(5), resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getInt(4));
					result.add(u);
				}
			}
		}

		return result;
	}

	@Override
	public Integer findCountForRoleGender(int idpred, String gender) throws SQLException {
		String query = "select count(pol) from uloga where predstava_idpred=? and pol=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, idpred);
			preparedStatement.setString(2, gender);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1);
				} else
					return -1;
			}
		}
	}
	
	public List<Uloga> findRolesNotAssigned() throws SQLException {
		String query = "select idul, imeulo, vrstaulo, pol, predstava_idpred " + 
				"from uloga " + 
				"where idul not in (select uloga_idul from podela)";
		
		List<Uloga> ulogaList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

		//	public Uloga(String imeulo, String pol, String vrstaulo, int idpred) {
			while(resultSet.next()) {
				ulogaList.add(new Uloga(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getString(3), resultSet.getInt(5)));
			
			}
		}
		
		return ulogaList;
	}


	@Override
	public List<GlumciUlogeDTO> findGlumci() throws SQLException {
		// TODO Auto-generated method stub

		String query = "select distinct g.mbg, imeg, pr.nazivpred, u.imeulo " + 
				"from glumac g, predstava pr, uloga u, podela p " + 
				"where p.glumac_mbg = g.mbg and u.idul = p.uloga_idul " + 
				"and pr.idpred = u.predstava_idpred";
		List<GlumciUlogeDTO> glumciList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				glumciList.add(new GlumciUlogeDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
			}
		}
		
		return glumciList;
		
	}


	@Override
	public List<OstaliGlumciDTO> findOtherGlumci(Integer mbg, String imeulo) throws SQLException {
		String query = "select g.mbg, imeg from glumac g, podela p, uloga u where u.idul = p.uloga_idul and g.mbg = p.glumac_mbg and u.imeulo = ? and p.glumac_mbg != ?";
		List<OstaliGlumciDTO> ostaliList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, imeulo);
			preparedStatement.setInt(2, mbg);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while(resultSet.next()) {
					ostaliList.add(new OstaliGlumciDTO(resultSet.getInt(1), resultSet.getString(2)));
				}
			}
		}
		return ostaliList;
		
	}



}
