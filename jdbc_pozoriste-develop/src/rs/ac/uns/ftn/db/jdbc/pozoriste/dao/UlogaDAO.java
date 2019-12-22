package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.List;

import java.util.HashMap;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorarUdeo;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciUlogeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.OstaliGlumciDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;

public interface UlogaDAO extends CRUDDao<Uloga, Integer> {

	List<Uloga> findRoleByTheatreId(int idpred) throws SQLException;

	Integer findCountForRoleGender(int idpred, String gender) throws SQLException;
	
	List<Uloga> findRolesNotAssigned() throws SQLException;
	
	List<GlumciUlogeDTO> findGlumci() throws SQLException;
	
	List<OstaliGlumciDTO> findOtherGlumci(Integer mbg, String imeulo) throws SQLException;
	

}
