package rs.ac.uns.ftn.db.jdbc.exam.service;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.ProjekatDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.RadnikDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.RadprojDAO;
import rs.ac.uns.ftn.db.jdbc.exam.daoimpl.ProjekatDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.daoimpl.RadProjDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.daoimpl.RadnikDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dto.RadnikDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.SefoviBrcDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.SefoviDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public class ComplexFunctionalityService {
	private static final RadnikDAO radnikDAO = new RadnikDAOImpl();
	private static final ProjekatDAO projekatDAO = new ProjekatDAOImpl();
	private static final RadprojDAO radprojDAO = new RadProjDAOImpl();
	
	
	
	
	public static void updatePunishedWorkers() {
		// TODO Auto-generated method stub
		
		try {
			if(radprojDAO.getPunishedWorkers().isEmpty()) {
				System.out.println("Nema radnika na projektu sa sifrom 10!");
			} else {
				System.out.println("Kaznjeni radnici");
				System.out.println(Radnik.getFormattedHeader());
				for (Radnik r : radprojDAO.getPunishedWorkers()) {
					System.out.println(r);
				}
				List<Radnik> radnici = radprojDAO.getPunishedWorkers();
				
				radprojDAO.createTempTable();
				radprojDAO.executeTransactional();
			}
		
			
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}




	public static void kaoKompleksniKveri() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Sefovi: ");
			
			for (SefoviDTO s : radnikDAO.findSefovi()) {
				System.out.println(s);
				
				System.out.println("\t\tPodredjeni radnici: ");
				
				for(RadnikDTO r : radnikDAO.findPodredjeni(s.getMbr())) {
					System.out.println("\t\t" + r);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}




	public static void rukovodioci() {
		// TODO Auto-generated method stub
		try {
			System.out.println(Radnik.getFormattedHeader());
			for (RadnikDTO r : radnikDAO.findRukovodiociProjekata()) {
				System.out.println(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}




	public static void sefovi() {
		// TODO Auto-generated method stub
		try {
			System.out.println(SefoviBrcDTO.getFormattedHeader());
			 for (SefoviBrcDTO s : radnikDAO.findAngSef()) {
				 System.out.println(s);
			 }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
