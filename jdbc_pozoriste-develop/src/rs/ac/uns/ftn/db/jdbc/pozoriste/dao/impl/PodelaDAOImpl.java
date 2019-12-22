package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumacDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorarUdeo;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorariDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciPlate;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;

public class PodelaDAOImpl implements PodelaDAO{

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select count(*) from podela";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			return resultSet.getInt(1);
		}
	}

	@Override
	public void delete(Podela entity) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from podela where idPod = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, entity.getIdPod());
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from podela";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from podela where idPod = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from podela where idPod = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Podela> findAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idPod, honorar, datumd, datump, glumac_mbg, uloga_idul from podela";
		List<Podela> podelaList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				podelaList.add(new Podela(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(6), resultSet.getInt(5)));
			}
		}
		return podelaList;
	}

	@Override
	public Iterable<Podela> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select idPod, honorar, datumd, datump, uloga_idul, glumac_mbg from podela where idPod in (";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(query);
		List<Podela> podelaList = new ArrayList<>();
		
		for (Integer id : ids) {
			stringBuilder.append("?, ");
		}
		
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString())) {
			int i = 1;
			for (Integer id : ids) {
				preparedStatement.setInt(i++, id);
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				podelaList.add(new Podela(resultSet.getInt(1), resultSet.getDouble(2), (java.util.Date)resultSet.getDate(3), (java.util.Date)resultSet.getDate(4), resultSet.getString(5), resultSet.getInt(6)));
			}
		}
		return podelaList;
	}

	@Override
	public Podela findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select idPod, honorar, datumd, datump, uloga_idul, glumac_mbg from podela where idPod = ? ";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				return new Podela(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getInt(6));
			}
		}
	}

	@Override
	public void save(Podela entity) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into podela (honorar, datumd, datump, uloga_idul, glumac_mbg, idPod) values (?, ?, ?, ?, ?, ?)";
		String updateCommand = "update podela set honorar = ?, datumd = ?, datump = ?, uloga_idul = ?, glumac_mbg = ? where idPod = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(existsById(entity.getIdPod()) ? updateCommand : insertCommand)) {
			int i = 1;
			preparedStatement.setDouble(i++, entity.getHonorar());
			java.util.Date datumd = entity.getDatumd();
			java.util.Date datump = entity.getDatump();
			
			
			preparedStatement.setDate(i++, new java.sql.Date(entity.getDatumd().getTime()));
			preparedStatement.setDate(i++, new java.sql.Date(entity.getDatump().getTime()));
			preparedStatement.setString(i++, entity.getUloga_idul());
			preparedStatement.setInt(i++, entity.getGlumac_mbg());
			preparedStatement.setInt(i++, entity.getIdPod());
			
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public void saveAll(Iterable<Podela> entities) throws SQLException {
		// TODO Auto-generated method stub
		String insertCommand = "insert into podela (honorar, datumd, datump, uloga_idul, glumac_mbg, idPod) values (?, ?, ?, ?, ?, ?)";
		String updateCommand = "update podela set honorar = ?, datumd = ?, datump = ?, uloga_idul = ?, glumac_mbg = ? where idPod = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateCommand);
				PreparedStatement preparedStatementInsert = connection.prepareStatement(insertCommand)) {
			PreparedStatement preparedStatement;
			
			for (Podela p : entities) {
				if(existsById(p.getIdPod())) {
					preparedStatement = preparedStatementUpdate;
				} else {
					preparedStatement = preparedStatementInsert;
				}
				
				preparedStatement.executeUpdate();
			}
			
		}
	}
	
	
	public List<GlumacDTO> findActorMaxHonorar() throws SQLException {
		String query = "select glumac_mbg, max(honorar), nazivpred, imeulo " + 
				"from podela p, predstava pred, uloga u " + 
				"where u.predstava_idpred = pred.idpred and p.uloga_idul = u.idul " + 
				"group by glumac_mbg, nazivpred, imeulo";
		List<GlumacDTO> glumciList = new ArrayList<>();
		
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				glumciList.add(new GlumacDTO(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getString(3), resultSet.getString(4)));
			}
		}
		
		return glumciList;
	}
	
	public Date parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy.");
		try {
			Date ret = format.parse(date);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<Integer, Double> sumHonorarByRole() throws SQLException {
		String query = "select idul, sum(honorar) from uloga, podela where idul = uloga_idul group by idul";
		
		HashMap<Integer, Double> sumaHonorara = new HashMap<Integer, Double>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareCall(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				
				sumaHonorara.put(resultSet.getInt(1), resultSet.getDouble(2));
			
			}
		}
		
		
		return sumaHonorara;
	}
	
	public List<GlumciHonorarUdeo> findActorsPartHonorar(Integer idul) throws SQLException {
		List<GlumciHonorarUdeo> glumciList = new ArrayList<>();
		HashMap<Integer, Double> honorari = new HashMap<Integer, Double>();
		String query = "select g.mbg, imeg, nazivpred, honorar, idul from glumac g, podela p, uloga u, predstava pr where"
				+ " u.idul = p.uloga_idul and g.mbg = p.glumac_mbg and u.predstava_idpred = pr.idpred and u.idul = ? ";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, idul);
			
			honorari = sumHonorarByRole();
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while(resultSet.next()) {
					DecimalFormat df = new DecimalFormat("0.00");
					GlumciHonorarUdeo g = new GlumciHonorarUdeo(resultSet.getInt(1), resultSet.getString(2));
					double udeo = 100*resultSet.getDouble(4)/honorari.get(resultSet.getInt(5));
					g.setUdeo_honorara(df.format(udeo));
					
					glumciList.add(g);
					
				}
			}
		}
		
		return glumciList;
	}

	@Override
	public HashMap<Integer, Double> findAverageHonorarByRole() throws SQLException {
		String query = "select uloga_idul, avg(honorar) from podela group by uloga_idul";
		HashMap<Integer, Double> prosecniHonorari = new HashMap<Integer, Double>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next()) {
				prosecniHonorari.put(resultSet.getInt(1), resultSet.getDouble(2));
			}
		}
		
		
		return prosecniHonorari;
	}

	@Override
	public List<GlumciPlate> findActorsByRole(Integer idul) throws SQLException {
		// TODO Auto-generated method stub
		List<GlumciPlate> glumciList = new ArrayList<>();
		String query = "select g.mbg, imeg, plata, honorar from glumac g, podela p where p.uloga_idul = ? and honorar > ?";
		HashMap<Integer, Double> honorariAvg = new HashMap<Integer, Double>();
		honorariAvg = findAverageHonorarByRole();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, idul);
			preparedStatement.setDouble(2, honorariAvg.get(idul));
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				
				while(resultSet.next()) {
					glumciList.add(new GlumciPlate(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(4)));
				}
			}
		}
		
		
		return glumciList;
		
	}

	@Override
	public List<GlumciHonorariDTO> findOtherActors(Integer idul, Integer mbg) throws SQLException {
		// TODO Auto-generated method stub
		List<GlumciHonorariDTO> glumciList = new ArrayList<>();
		String query = "select distinct mbg, imeg, honorar "
				+ "from glumac, podela "
				+ "where glumac_mbg = mbg and mbg != ? "
				+ "and uloga_idul = ? and honorar > ?";
		HashMap<Integer, Double> honorariAvg = new HashMap<Integer, Double>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, mbg);
			preparedStatement.setInt(2, idul);
			honorariAvg = findAverageHonorarByRole();
			preparedStatement.setDouble(3, honorariAvg.get(idul));
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while(resultSet.next()) {
					glumciList.add(new GlumciHonorariDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3)));
				}
			}
		}
		
		return glumciList;
	}
	
	public List<Uloga> podeljeneUloge() throws SQLException {
		String query = "select idul, imeulo, pol, vrstaulo, predstava_idpred from uloga, podela where idul = uloga_idul";
		
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

	@Override
	public List<GlumciHonorariDTO> showActorsHonorar() throws SQLException {
		
		List<GlumciHonorariDTO> glumciList = new ArrayList<>();
		String query = "select g.mbg, g.imeg, sum(po.honorar) \r\n" + 
				"from podela po, glumac g, prikazivanje pr, scena s, pozoriste poz\r\n" + 
				"where po.glumac_mbg = g.mbg and pr.scena_idsce = s.idsce \r\n" + 
				"and s.pozoriste_idpoz = poz.idpoz and poz.idpoz != g.pozoriste_idpoz\r\n" + 
				"group by g.mbg, g.imeg";
				
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next())
				glumciList.add(new GlumciHonorariDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3)));
		}
				
		
		return glumciList;
	
	}

	@Override
	public List<Glumac> findOldPeople() throws SQLException {
		
		List<Glumac> glumciList = new ArrayList<>();
		String query = "select mbg, imeg, datumr, status, plata, dodatak, pozoriste_idpoz, count(*) " + 
				"from glumac, podela " + 
				"where glumac_mbg = mbg and " + 
				"datumr < '01-JAN-1960' " + 
				"group by  mbg, imeg, datumr, status, plata, dodatak, pozoriste_idpoz ";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			// public Glumac(int mbg, String imeg, Date datumr, boolean status, double plata, double dodatak, int idpoz)
			while(resultSet.next()) {
				
				java.util.Date utilDate = new java.util.Date(resultSet.getDate(3).getTime());
				Boolean flag = false;
				if(resultSet.getString(4).equals('z')) {
					flag = true;
				}
				
				glumciList.add(new Glumac(resultSet.getInt(1), resultSet.getString(2), utilDate, flag , resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getInt(7)));
			}
		}
		
		
		return glumciList;
	}
}
