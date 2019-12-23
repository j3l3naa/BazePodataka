package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumacDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PredstavaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;

public class PredstavaDAOImpl implements PredstavaDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from predstava";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else return -1;
		}
	}

	@Override
	public void delete(Predstava entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from predstava where idpred = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, entity.getIdpred());
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from predstava";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from predstava where idpred = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from predstava where idpred = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Predstava> findAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idpred, nazivpred, trajanje, godinapre from predstava";
		List<Predstava> predstavaList = new ArrayList<Predstava>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query); 
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				Predstava predstava = new Predstava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
				predstavaList.add(predstava);
			}
		}
		
		return predstavaList;
	}

	@SuppressWarnings("unused")
	@Override
	public Iterable<Predstava> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		List<Predstava> predstavaList = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		String queryBegin = "select idpred, nazivpred, trajanje, godinapre from predstava where idpred = ?";
		stringBuilder.append(queryBegin);
		
		
		for(Integer id : ids) {
			stringBuilder.append("?,");
		}
		
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString()))  {
			int i = 0;
			for (Integer id : ids) {
				preparedStatement.setInt(++i, id);
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if(resultSet.isBeforeFirst()) {
					resultSet.next();
				}
				
				predstavaList.add(new Predstava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
			}
		}
		
		return predstavaList;
	}

	@Override
	public Predstava findById(Integer id) throws SQLException {
		String query = "select idpred, nazivpred, trajanje,godinapre from predstava where idpred = ?";
		Predstava predstava = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					predstava = new Predstava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getInt(4));
				}
			}
		}

		return predstava;

	}

	@Override
	public void save(Predstava entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into pozoriste (nazivpred, trajanje, godinapre, idpred) values (?, ?, ?, ?)";
		String updateCommand = "update predstava set nazivpred = ?, trajanje = ?, godinapre = ? where idpred = ?";
		int i = 1;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(existsById(entity.getIdpred()) ? updateCommand : insertCommand)) {
			
			preparedStatement.setString(i++, entity.getNazivpred());
			preparedStatement.setString(i++, entity.getTrajanje());
			preparedStatement.setInt(i++, entity.getGodinapre());
			preparedStatement.setInt(i, entity.getIdpred());
			
			preparedStatement.executeUpdate();
		}
	
	}

	@Override
	public void saveAll(Iterable<Predstava> entities) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into pozoriste (nazivpred, trajanje, godinapre, idpred) values (?, ?, ?, ?)";
		String updateCommand = "update predstava set nazivpred = ?, trajanje = ?, godinapre = ? where idpred = ?";
		int i = 1;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement(insertCommand);
				PreparedStatement updateStatement = connection.prepareStatement(updateCommand)) {
			
			connection.setAutoCommit(false);
			
			for(Predstava entity : entities) {
				
			PreparedStatement preparedStatement;
			if(existsById(entity.getIdpred())) {
				preparedStatement = updateStatement;
			} else {
				preparedStatement = insertStatement;
			}
			
			preparedStatement.setString(i++, entity.getNazivpred());
			preparedStatement.setString(i++, entity.getTrajanje());
			preparedStatement.setInt(i++, entity.getGodinapre());
			preparedStatement.setInt(i, entity.getIdpred());
			
			preparedStatement.executeUpdate();
			
			}
			connection.commit();
		}
	
	}

	
	@Override
	public int findCountOfRoles(int idPred) throws SQLException {
		String query = "select count(*) from uloga where predstava_idpred=?";
		int count = 0;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, idPred);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					count = resultSet.getInt(1);
				}

			}
		}
		
		return count;
	}

	@Override
	public List<PredstavaDTO> findMostVisitedShows() throws SQLException {
		String query = "SELECT idpred ,nazivpred, AVG(Pr.brojgled) FROM Predstava P, Prikazivanje Pr WHERE P.idpred = Pr.predstava_idpred GROUP BY P.idpred, nazivpred HAVING AVG(brojgled) >= ALL(SELECT AVG(brojgled)  FROM Predstava P, Prikazivanje Pr  WHERE P.idpred = Pr.predstava_idpred GROUP BY P.idpred)";
		List<PredstavaDTO> result = new ArrayList<PredstavaDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				PredstavaDTO predstava = new PredstavaDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
				result.add(predstava);
			}

		}
		
		
		return result;
	}


	
	/*
	public HashMap<Integer, Double> maxHonorar() throws SQLException {
		String query = "select glumac_mbg, max(honorar), uloga_idul from podela group by mbg";
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query); 
				ResultSet resultSet = preparedStatement.executeQuery()) {
			resultSet.next();
			
			result[resultSet.getInt(1)] = resultSet.getDoubl
		}
		
	}
	*/
	
}
