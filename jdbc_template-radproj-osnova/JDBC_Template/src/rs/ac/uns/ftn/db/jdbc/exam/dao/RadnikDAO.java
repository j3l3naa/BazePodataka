package rs.ac.uns.ftn.db.jdbc.exam.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dto.RadnikDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.SefoviBrcDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.SefoviDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public interface RadnikDAO extends CRUDDao<Radnik, Integer> {
	List<SefoviDTO> findSefovi() throws SQLException;
	List<RadnikDTO> findPodredjeni(Integer id) throws SQLException;
	List<RadnikDTO> findRukovodiociProjekata() throws SQLException;
	List<SefoviBrcDTO> findAngSef() throws SQLException;
}
