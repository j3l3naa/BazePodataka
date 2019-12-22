package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.Connection;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Mesto;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;

public interface MestoDAO extends CRUDDao<Mesto, Integer> {
	void save(Connection c, Mesto entity) throws SQLException;
}
