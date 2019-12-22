package rs.ac.uns.ftn.db.jdbc.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.ScenaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public class ScenaUIHandler {
	
	private static final ScenaDAO scenaDAO = new ScenaDAOImpl();
	private static final PozoristeDAO pozoristeDAO = new PozoristeDAOImpl();
	public void handleScenaMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad scenama:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jedne scene");
			System.out.println("4 - Unos vise scena");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("7 - Prikaz po vise identifikatora");
			System.out.println("X - Izlazak iz rukovanja scenama");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				handleInsert();
				break;
			case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdateId();
				break;
			case "6":
				deleteById();
				break;
			case "7":
				showByMoreId();
			}

		} while (!answer.equalsIgnoreCase("X"));
	}
	
	private void handleUpdateId() {
		// TODO Auto-generated method stub
		System.out.println("Unesite id scene koje zelite da izmenite: ");
		int idsce = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
		if(!scenaDAO.existsById(idsce)) {
			System.out.println("Uneli ste nepostojecu scenu!");
		}
		
		System.out.println("Naziv scene: ");
		String naziv = MainUIHandler.sc.nextLine();
		
		System.out.println("Broj sedista: ");
		int brojs = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("ID pozorista: ");
		int idPoz = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Scena sc = new Scena(idsce, naziv, brojs, idPoz);
		scenaDAO.save(sc);
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleMultipleInserts() {
		// TODO Auto-generated method stub
		String answer;
		List<Scena> scenaList = new ArrayList<>();
		
		do {
			System.out.println("IDSCE: ");
			int id = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Naziv scene: ");
			String naziv = MainUIHandler.sc.nextLine();

			System.out.println("Broj sedista:: ");
			int brojsed = Integer.parseInt(MainUIHandler.sc.nextLine());
		
			System.out.println("ID pozorista:");
			int idpoz = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			scenaList.add(new Scena(id, naziv, brojsed, idpoz));

			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));
		
		System.out.println(scenaList.size());
		try {
			scenaDAO.saveAll(scenaList);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void deleteById() {
		// TODO Auto-generated method stub
		System.out.println("Id scene: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			if(!scenaDAO.existsById(id)) {
				System.out.println("Uneta vrednost ne postoji!");
			} else {
				scenaDAO.deleteById(id);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void handleInsert() {
		// TODO Auto-generated method stub
		System.out.println("IDSCE: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
	
		System.out.println("Naziv scene: ");
		String naziv = MainUIHandler.sc.nextLine();
			
		System.out.println("Broj sedista scene: ");
		int brojSed = Integer.parseInt(MainUIHandler.sc.nextLine());
			
		System.out.println("Id pozorista: ");
		int idpoz = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
		scenaDAO.save(new Scena(id, naziv, brojSed, idpoz));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void showAll() {
		System.out.println(Scena.getFormattedHeader());

		try {
			for (Scena scena : scenaDAO.findAll()) {
				System.out.println(scena);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void showById() {
		System.out.println("IDSCE: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			Scena scena = scenaDAO.findById(id);

			System.out.println(Scena.getFormattedHeader());
			System.out.println(scena);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	private void showByMoreId() {

		System.out.println("Unesite zeljene vrednosti ID scene koje zelite prikazati:");
		String answer;
		List<Integer> ids = new ArrayList<>();
		List<Scena> scenaList;
		do {
			System.out.println("ID: ");
			ids.add(Integer.parseInt(MainUIHandler.sc.nextLine()));
			
			System.out.println("Zelite izaci iz izbora scena? Unesite 'X'.");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));
			
		try {
			System.out.println(Scena.getFormattedHeader());
			for(Scena s : scenaDAO.findAllById(ids)) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
