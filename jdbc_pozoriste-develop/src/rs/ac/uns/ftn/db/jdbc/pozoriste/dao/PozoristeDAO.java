package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Drzava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Mesto;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public interface PozoristeDAO extends CRUDDao<Pozoriste, Integer> {
	
	void addTheatresInNewPlaces(Pozoriste p, Mesto m, Drzava d, Scena s) throws SQLException;
	
}
