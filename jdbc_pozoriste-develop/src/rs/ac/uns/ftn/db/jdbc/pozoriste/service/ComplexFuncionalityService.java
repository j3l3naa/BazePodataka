package rs.ac.uns.ftn.db.jdbc.pozoriste.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.DrzavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.MestoDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.UlogaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.DrzavaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.MestoDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PodelaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PredstavaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PrikazivanjeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.ScenaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.UlogaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumacDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorarUdeo;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciHonorariDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciPlate;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.GlumciUlogeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.OstaliGlumciDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PredstavaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDeleteDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeScenaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Drzava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Mesto;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;
import rs.ac.uns.ftn.db.jdbc.ui_handler.MainUIHandler;

public class ComplexFuncionalityService {

	private static final PozoristeDAO pozoristeDAO = new PozoristeDAOImpl();
	private static final ScenaDAO scenaDAO = new ScenaDAOImpl();
	private static final PrikazivanjeDAO prikazivanjeDAO = new PrikazivanjeDAOImpl();
	private static final PredstavaDAO predstavaDAO = new PredstavaDAOImpl();
	private static final UlogaDAO ulogaDAO = new UlogaDAOImpl();
	private static final PodelaDAO podelaDAO = new PodelaDAOImpl();
	private static final MestoDAO mestoDAO = new MestoDAOImpl();
	private static final DrzavaDAO drzavaDAO = new DrzavaDAOImpl();

	public void showSceneForTheatre() {
		
		System.out.println(Pozoriste.getFormattedHeader());
		
		try {
			for (Pozoriste p : pozoristeDAO.findAll()) {
				System.out.println(p);
				if(!scenaDAO.findSceneByTheatre(p.getId()).isEmpty()) {
					System.out.println(Scena.getFormattedHeader());
					for (Scena s : scenaDAO.findSceneByTheatre(p.getId())) {
						System.out.println("\t\t" + s);
					}
				} else {
					System.out.println("\t\tDato pozoriste nema scene!");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void showReportingForShowingShows() {
		
		System.out.println(Predstava.getFormattedHeader()); 
		
		try {
			HashMap<Integer, PrikazivanjeDTO> resultMap = prikazivanjeDAO.findSumAvgCountForShowingShow();
			
			for(Integer predstavaId : prikazivanjeDAO.findAllDistinctShowFromShowing()) {
				Predstava predstava = predstavaDAO.findById(predstavaId);
				System.out.println(predstava);
				System.out.println("\t\t" + Prikazivanje.getFormattedHeader()); 
				for(Prikazivanje p : prikazivanjeDAO.findShowingByShowId(predstava.getIdpred())) {
					System.out.println("\t\t" + p);
				}
				
				System.out.println("\t\t-------UKUPAN BROJ GLEDALACA ---- PROSECAN BROJ GLEDALACA ---- BROJ PRIKAZIVANA ----");
				System.out.println("\t\t\t" + resultMap.get(predstava.getIdpred()).toString());
				System.out.println("\n\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		 * 
		 *  MOJE RESENJE KOJE RADIIIIIIIIIII
		System.out.println(Predstava.getFormattedHeader());
		try {
			HashMap<Integer, PrikazivanjeDTO> evridz = prikazivanjeDAO.findSumAvgCountForShowingShow();
			
			for (Predstava p : predstavaDAO.findAll()) {
				System.out.println(p);
				
				System.out.println("\t\t" + Prikazivanje.getFormattedHeader());
				for (Prikazivanje pr : prikazivanjeDAO.findShowingByShowId(p.getIdpred())) {
					System.out.println("\t\t" + pr);					
				}
				PrikazivanjeDTO pdto = evridz.get(p.getIdpred());
				System.out.println("\t\t\tProsecan broj gledalaca: " + pdto.getProsecan_broj_gledalaca());
				System.out.println("\t\t\tUkupan broj gledalaca: " + pdto.getUkupan_broj_gledalaca());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		*/
	}

	public void showComplexQuery() {
		try {
			System.out.println(Scena.getFormattedHeader());
			for (Scena scena : scenaDAO.findSceneForSpecificNumberOfSeats()) {
				System.out.println(scena);
				if (prikazivanjeDAO.findBySceneId(scena.getIdsce()).size() != 0) {
					System.out.println("\t\t----------------------- PREDSTAVE -----------------------");
					System.out.println("\t\t" + Predstava.getFormattedHeader());
					for (PrikazivanjeScenaDTO prikazivanje : prikazivanjeDAO.findBySceneId(scena.getIdsce())) {
						if (prikazivanje.getSuma() > 700) {
							System.out.println("\t\t" + predstavaDAO.findById(prikazivanje.getIdpred()));
							System.out.println("\t\t----------------------UKUPNA SUMA-----------------------");
							System.out.println("\t\t" + prikazivanje.getSuma());
							System.out.println("\t\tUkupan broj uloga za predstavu: "
									+ predstavaDAO.findCountOfRoles(prikazivanje.getIdpred()));
							System.out.println();

						}else{
							System.out.println("\t\tNEMA PREDSTAVA ZA PRIKAZIVANJE NA OVOJ SCENI SA VISE OD 700 MESTA!");
						}
					}
					System.out.println();
				} else {
					System.out.println("\t\tNEMA PREDSTAVA ZA PRIKAZIVANJE NA OVOJ SCENI!");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showMostVisitedShow() {
		try {

			for (PredstavaDTO p : predstavaDAO.findMostVisitedShows()) {
				System.out.println("IDPRED \t NAZIV\t PROSECNO_TRAJANJE");
				System.out.println(p.toString());
				System.out.println("\t\t--------------------ULOGE------------------------");
				for (Uloga u : ulogaDAO.findRoleByTheatreId(p.getIdpred())) {
					System.out.println("\t\t" + Uloga.getFormattedHeader());
					System.out.println("\t\t" + u.toString());

				}

				System.out.println("\t\t-----------UKUPAN BROJ ZENSKIH ULOGA-------------");
				System.out.println("\t\t" + ulogaDAO.findCountForRoleGender(p.getIdpred(), "z"));
				System.out.println("\t\t-----------UKUPAN BROJ MUSKIH ULOGA--------------");
				System.out.println("\t\t" + ulogaDAO.findCountForRoleGender(p.getIdpred(), "m"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showShowingForDeleting() {

		try {
			for (PrikazivanjeDeleteDTO pd : prikazivanjeDAO.findShowingForDeleting()) {
				System.out.println(PrikazivanjeDeleteDTO.getFormattedHeader());
				System.out.println(pd);
				prikazivanjeDAO.deleteAndInsertIntoShowing(pd);
				System.out.println("--------------------Prikazivanje nakon dodavanja:---------------------");
				System.out.println(Prikazivanje.getFormattedHeader());
				for (Prikazivanje p : prikazivanjeDAO.findAll()) {
					System.out.println(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showActorsWithMaxHon() {
		// TODO Auto-generated method stub
		List<GlumacDTO> glumciList = new ArrayList<>();
		try {
			glumciList = podelaDAO.findActorMaxHonorar();
			
			for(GlumacDTO g : glumciList) {
				System.out.println(g);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void showActorsWithRoles() {
		List<GlumciUlogeDTO> glumciList = new ArrayList<>();
		List<OstaliGlumciDTO> ostaliGlumciList = new ArrayList<>();
		
		try {
			glumciList = ulogaDAO.findGlumci();
			System.out.println("GLUMCI:");
			for (GlumciUlogeDTO g : glumciList) {
				System.out.println(g);
				ostaliGlumciList = ulogaDAO.findOtherGlumci(g.getMbg(), g.getImeulo());
				
				System.out.println("\t\t--------------- OSTALI ---------------");
				for (OstaliGlumciDTO g2 : ostaliGlumciList) {
					System.out.println(g2);
				}
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void showActorsPartInRole() {
		// TODO Auto-generated method stub
		
		try {
			System.out.println(Uloga.getFormattedHeader());
			
			for (Uloga u : ulogaDAO.findAll()) {
				System.out.println(u); 
				System.out.println("\t\t-----GLUMCI-----");
				for(GlumciHonorarUdeo g : podelaDAO.findActorsPartHonorar(u.getIdulo())) { 
					System.out.println("\t\t\t" + g);
				}
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
		
	}

	public void showActorsWithMoreThanAverage() {
		
		try {
			System.out.println(Uloga.getFormattedHeader());
			
			for (Uloga u : podelaDAO.podeljeneUloge()) {
				System.out.println("ULOGA");
				System.out.println(u);

				
				if(podelaDAO.findActorsByRole(u.getIdulo()).isEmpty())
					System.out.println("\t\t\t\tNema glumaca sa honorarom vecim od prosecnog!");
				
				else {
						
						for (GlumciPlate gp : podelaDAO.findActorsByRole(u.getIdulo())) {
						System.out.println("\t\t\t --- GLUMCI ---");
						System.out.println(gp);
						
						if(podelaDAO.findOtherActors(u.getIdulo(), gp.getMbg()).isEmpty()) {
							System.out.println("\t\t\t\tNema ostalih glumaca sa honorarom vecim od prosecnog!");
							
						} else {
						
							System.out.println("\t\t\t\t--- OSTALI GLUMCI I HONORARI ----");
						
							for (GlumciHonorariDTO gh : podelaDAO.findOtherActors(u.getIdulo(), gp.getMbg())) {
						
								System.out.println(gh);
							}
						}
				}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void showComplexQueryZad11() {
		
		System.out.println("Glumci i suma njihovih honorara u prikazivanjima predstave koje se ne prikazuju u njihovom maticnom pozoristu");
		
		try {
			for(GlumciHonorariDTO g : podelaDAO.showActorsHonorar()) {
				System.out.println(g);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void addTheatreByWish() {
		Mesto m;
		Pozoriste p;
		Drzava d;
		Scena s;
		
		try {
			System.out.println("ID pozorista (mora biti nepostojeci - mozete izmeniti neki postojeci)");
			int idpoz = Integer.parseInt(MainUIHandler.sc.nextLine());
		
			System.out.println("Naziv pozorista: ");
			String naziv = MainUIHandler.sc.nextLine();
			
			System.out.println("Adresa pozorista: ");
			String adresa = MainUIHandler.sc.nextLine();
		
			System.out.println("Sajt pozorista: ");
			String sajt = MainUIHandler.sc.nextLine();
			
			System.out.println("ID mesta pozorista: ");
			int idmesto = Integer.parseInt(MainUIHandler.sc.nextLine());
		
				if (!mestoDAO.existsById(idmesto)) {
					System.out.println("Mesto ne postoji, morate uneti podatke vezano za mesto da bismo ga uneli u bazu podataka");
					System.out.println("Naziv mesta: ");
					String nazivm = MainUIHandler.sc.nextLine();
					
					System.out.println("Id drzave: ");
					int idd = Integer.parseInt(MainUIHandler.sc.nextLine());
					
					m = new Mesto(idmesto, nazivm, idd);
					
					if(!drzavaDAO.existsById(idd)) {
						System.out.println("Drzava ne postoji u nasoj bazi podataka, molim vas da unesete podatke potrebne za unos drzave: ");
						System.out.println("Naziv drzave: ");
						String nazivd = MainUIHandler.sc.nextLine();
						
						d = new Drzava(idd, nazivd);
						p = new Pozoriste(idpoz, naziv, adresa, sajt, nazivm);
					} else {
						
						d = drzavaDAO.findById(idd);
						p = new Pozoriste(idpoz, naziv, adresa, sajt, nazivm);
					}
				} else {
					m = mestoDAO.findById(idmesto);
					d = drzavaDAO.findById(m.getIdd());
					p = new Pozoriste(idpoz, naziv, adresa, sajt, m.getNaziv());
					
				}
				String answer;
			do {	
			System.out.println("Unesite scene koje zelite da ubacite u novo pozoriste: ");	
			
			System.out.println("Id scene: mora biti jedinstven: ");
			int idsce = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			System.out.println("Naziv scene: ");
			String nazivsce = MainUIHandler.sc.nextLine();
			
			System.out.println("Broj sedista scene: ");
			int brojsed = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			s = new Scena(idsce, nazivsce, brojsed, idpoz);
			
			

			System.out.println("Zelite li da prekinete unos scena? (X za DA)");
			answer = MainUIHandler.sc.nextLine();
			}while(!answer.equalsIgnoreCase("X"));
			
			pozoristeDAO.addTheatresInNewPlaces(p, m, d, s);
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void oldPeople() {
		// TODO Auto-generated method stub
		
		// nije zavrsen do kraja
		
		try {
			for (Glumac g : podelaDAO.findOldPeople()) {
				System.out.println(g);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
