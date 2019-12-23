package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.RadprojDAO;
import rs.ac.uns.ftn.db.jdbc.exam.daoimpl.RadProjDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.model.RadProj;

public class RadProjUIHandler {
	private static final RadprojDAO radprojDAO = new RadProjDAOImpl();

	public void handleRadProjMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad sa radproj tabelom:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog");
			System.out.println("4 - Unos vise");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja radproj tabelom");

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
			case "7":
				showMoreById();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showMoreById() {
		String answer;
		List<Integer> mbrs = new ArrayList<>();
		List<Integer> sprs = new ArrayList<>();
		

		System.out.println("MBR: ");
		int mbr = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("SPR: ");
		int spr = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		mbrs.add(mbr);
		sprs.add(spr);
		
		do {
			System.out.println("MBR: ");
			mbr = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			System.out.println("Zelite li da prekinete unos? (X za da)");
			answer = MainUIHandler.sc.nextLine();
			
			System.out.println("SPR: ");
			spr = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			System.out.println("Zelite li da prekinete unos? (X za da)");
			answer = MainUIHandler.sc.nextLine();
			
			mbrs.add(mbr);
			sprs.add(spr);
			
		}while(!answer.equalsIgnoreCase("X"));
		
		System.out.println(RadProj.getFormattedHeader());
		try {
			for (RadProj rp : radprojDAO.findAllById(mbrs, sprs)) {
				System.out.println(rp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showAll() {
		try {
			System.out.println(RadProj.getFormattedHeader());
			for (RadProj rp : radprojDAO.findAll())
				System.out.println(rp);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showById() {
		System.out.println("MBR: ");
		int mbr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println("SPR: ");
		int spr = Integer.parseInt(MainUIHandler.sc.nextLine());
		List<Integer> kljucevi = new ArrayList<>();
		kljucevi.add(mbr);
		kljucevi.add(spr);
		try {
			System.out.println(RadProj.getFormattedHeader());
			RadProj rp = radprojDAO.findById(kljucevi);
			System.out.println(rp);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleSingleInsert() {
		System.out.println("MBR: ");
		int mbr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println("SPR: ");
		int spr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println("BRC: ");
		int brc = Integer.parseInt(MainUIHandler.sc.nextLine());
		RadProj rp = new RadProj(mbr, spr, brc);
		
		try {
			radprojDAO.save(rp);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void handleUpdate() {
		System.out.println("MBR: ");
		int mbr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println("SPR: ");
		int spr = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		List<Integer> kljucevi = new ArrayList<>();
		kljucevi.add(mbr);
		kljucevi.add(spr);
		try {  
			if(!radprojDAO.existsById(kljucevi)) {
				System.out.println("Uneta vrednost ne postoji!");
			}
			else {
		System.out.println("BRC: ");
		int brc = Integer.parseInt(MainUIHandler.sc.nextLine());
		RadProj rp = new RadProj(mbr, spr, brc);
		
		
			radprojDAO.save(rp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		
		}
	}

	private void handleDelete() {

	}

	private void handleMultipleInserts() {

	}

}
