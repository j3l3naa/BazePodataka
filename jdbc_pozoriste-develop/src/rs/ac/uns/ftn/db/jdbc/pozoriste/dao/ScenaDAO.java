package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public interface ScenaDAO extends CRUDDao<Scena, Integer> {

	// metoda koja vraca sve scene za odredjeno pozoriste
	public List<Scena> findSceneByTheatre(Integer theatreId) throws SQLException;

	// metoda za 2 zadatak iz ComplexUIHandler
	public List<Scena> findSceneForSpecificNumberOfSeats() throws SQLException;
	void save(Connection c, Scena s) throws SQLException;
	
	Integer findTheatreByScene(Integer idsce) throws SQLException;
}
