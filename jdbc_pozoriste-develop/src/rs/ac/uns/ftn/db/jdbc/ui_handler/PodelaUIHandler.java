package rs.ac.uns.ftn.db.jdbc.ui_handler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PodelaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;

public class PodelaUIHandler {
	
	private static final PodelaDAO podelaDAO = new PodelaDAOImpl();
	
	public void handlePodelaMenu() {

		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad podelama:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jedne podele");
			System.out.println("4 - Unos vise pozorista");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja pozoristima");

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
			/*case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdate();
				break;
			case "6":
				handleDelete();
				break;
*/
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void handleSingleInsert() {
		// TODO Auto-generated method stub
		System.out.println("ID PODELE: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("HONORAR: ");
		double honorar = Double.parseDouble(MainUIHandler.sc.nextLine());
		
		System.out.println("DATUM DODELE: ");
		Date datumd = parseDate(MainUIHandler.sc.nextLine());
		
		System.out.println("DATUM PRESTANKA: ");
		Date datump = parseDate(MainUIHandler.sc.nextLine());
		
		System.out.println("ULOGA IDUL: ");
		String idul = MainUIHandler.sc.nextLine();
		
		System.out.println("MBG: ");
		int mbg = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Podela p = new Podela( id, honorar, datumd, datump, idul, mbg);
		
		try {
			podelaDAO.save(p); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showById() {
		// TODO Auto-generated method stub
		System.out.println("ID PODELE: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try { 
			Podela p = podelaDAO.findById(id);
			System.out.println(p);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void showAll() {
		// TODO Auto-generated method stub
		System.out.println(Podela.getFormattedHeader());
		 
		try {
			for (Podela p : podelaDAO.findAll()) {
				System.out.println(p);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
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
