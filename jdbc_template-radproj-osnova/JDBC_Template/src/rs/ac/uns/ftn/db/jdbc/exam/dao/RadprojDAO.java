package rs.ac.uns.ftn.db.jdbc.exam.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.model.RadProj;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public interface RadprojDAO extends CRUDDao<RadProj, List<Integer>>{

	Iterable<RadProj> findAllById(Iterable<List<Integer>> ids) throws SQLException;

	boolean existsById(List<Integer> id) throws SQLException;
	
	List<Radnik> getPunishedWorkers() throws SQLException;

	void updatePunishedWorkers(List<Radnik> radnici);

	void createTempTable() throws SQLException;

	void executeTransactional() throws SQLException;
	
	HashMap<Integer, Integer> findAngazovanja() throws SQLException;
}
