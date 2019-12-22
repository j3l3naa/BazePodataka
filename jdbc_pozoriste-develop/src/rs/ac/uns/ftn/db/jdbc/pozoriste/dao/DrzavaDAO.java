package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.Connection;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Drzava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;

public interface DrzavaDAO extends CRUDDao<Drzava, Integer> {
	void save(Connection c, Drzava entity) throws SQLException;
}
