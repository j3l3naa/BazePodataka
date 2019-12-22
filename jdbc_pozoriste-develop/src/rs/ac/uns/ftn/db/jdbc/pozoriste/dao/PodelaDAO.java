package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumacDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorarUdeo;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorariDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciPlate;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;

public interface PodelaDAO extends CRUDDao<Podela, Integer> {
	public List<GlumacDTO> findActorMaxHonorar() throws SQLException;
	
	List<GlumciHonorarUdeo> findActorsPartHonorar(Integer idul) throws SQLException;
	
	HashMap<Integer, Double> sumHonorarByRole() throws SQLException;
	
	HashMap<Integer, Double> findAverageHonorarByRole() throws SQLException;
	
	List<GlumciPlate> findActorsByRole(Integer idul) throws SQLException;
	
	List<GlumciHonorariDTO> findOtherActors(Integer idul, Integer mbg) throws SQLException;
	
	List<Uloga> podeljeneUloge() throws SQLException;
	
	List<GlumciHonorariDTO> showActorsHonorar() throws SQLException;
	
	List<Glumac> findOldPeople() throws SQLException;
}
