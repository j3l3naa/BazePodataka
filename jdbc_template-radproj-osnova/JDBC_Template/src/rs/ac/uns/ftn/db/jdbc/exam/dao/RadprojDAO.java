package rs.ac.uns.ftn.db.jdbc.exam.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dto.RadnikDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.RadProj;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public interface RadprojDAO extends CRUDDao<RadProj, List<Integer>>{

	Iterable<RadProj> findAllById(List<Integer> ids1, List<Integer> ids2) throws SQLException;

	boolean existsById(Integer id1, Integer id2) throws SQLException;
	
	List<Radnik> getPunishedWorkers() throws SQLException;

	void updatePunishedWorkers(List<Radnik> radnici);

	void createTempTable() throws SQLException;

	void executeTransactional() throws SQLException;
	
	HashMap<Integer, Integer> findAngazovanja() throws SQLException;
	HashMap<Integer, Double> findAvgAngazovanja() throws SQLException;
	List<RadnikDTO> findWorkersMoreBrcThanAverage() throws SQLException;
}
