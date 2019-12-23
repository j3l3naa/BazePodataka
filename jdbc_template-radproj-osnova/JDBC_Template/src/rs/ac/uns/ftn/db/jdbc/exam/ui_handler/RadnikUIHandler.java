package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rs.ac.uns.ftn.db.jdbc.exam.dao.RadnikDAO;
import rs.ac.uns.ftn.db.jdbc.exam.daoimpl.RadnikDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.model.Radnik;

public class RadnikUIHandler {
	private static final RadnikDAO radnikDAO = new RadnikDAOImpl();

	public void handleRadnikMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad sa radnicima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog");
			System.out.println("4 - Unos vise");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja radnicima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				handleSingleInsert();
				break;
			case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdate();
				break;
			case "6":
				handleDelete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		System.out.println(Radnik.getFormattedHeader());
		
		try {
			for (Radnik r : radnikDAO.findAll()) {
				System.out.println(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showById() {
		System.out.println("ID: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println(Radnik.getFormattedHeader());
		
		try {
			Radnik r = radnikDAO.findById(id);
			System.out.println(r);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleSingleInsert() {
		System.out.println("ID: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("IME: ");
		String ime = MainUIHandler.sc.nextLine();
		
		System.out.println("PREZIME: ");
		String prezime = MainUIHandler.sc.nextLine();
		
		System.out.println("SEF: ");
		int idsef = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("PLATA: ");
		double plt = Double.parseDouble(MainUIHandler.sc.nextLine());
		
		System.out.println("PRE: ");
		double pre = Double.parseDouble(MainUIHandler.sc.nextLine());
		
		System.out.println("GOD: ");
		java.util.Date datum = parseDate(MainUIHandler.sc.nextLine());
		//public Radnik(int mbr, String ime, String prz, int sef, double plt, double pre, Date god)
		Radnik r = new Radnik(id, ime, prezime, idsef, plt, pre, datum);
		System.out.println(r);
		try {
			System.out.println("Adding worker...");
			radnikDAO.save(r);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void handleUpdate() {

	}

	private void handleDelete() {
		
		try {
			System.out.println("MBR: ");
			int mbr = Integer.parseInt(MainUIHandler.sc.nextLine());
			radnikDAO.deleteById(mbr);
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleMultipleInserts() {

	}

	
	public Date parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date ret = format.parse(date);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
