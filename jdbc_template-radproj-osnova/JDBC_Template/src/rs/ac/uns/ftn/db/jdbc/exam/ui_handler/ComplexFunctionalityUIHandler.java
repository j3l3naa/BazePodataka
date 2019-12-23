package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import rs.ac.uns.ftn.db.jdbc.exam.service.ComplexFunctionalityService;

public class ComplexFunctionalityUIHandler {

	public void handleComplexFunctionalityMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite funkcionalnost:");
			System.out.println("\n1 - Smanjiti angazovanje i plate radnicima koji rade na projektu sa sifrom 10");
			System.out.println("\n2 - Prikazati sve sefove, broj ljudi kojima rukovodi kao i njegove podredjenje radnike");
			System.out.println("\n3 - Za svakog radnika prikazati matični broj, ime, prezime, kao i broj\n" + 
					"projekata kojima rukovodi, pri čemu je potrebno prikazati isključivo\n" + 
					"one radnike koji su rukovodioci na manjem broju projekata od\n" + 
					"prosečnog broja projekata na kojima rade radnici čije se prezime\n" + 
					"ne završava na “ic”.");
			System.out.println("\n4 - Koliko je ukupno angažovanje svih šefova na\n" + 
					"projektima?");

			System.out.println("\nX - Izlazak iz kompleksnih funkcionalnosti");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				// TODO: zadatak
				ComplexFunctionalityService.updatePunishedWorkers();
				break;
			case "2":
				// TODO: zadatak
				ComplexFunctionalityService.kaoKompleksniKveri();
				break;
			case "3":
				// TODO: zadatak
				ComplexFunctionalityService.rukovodioci();
				break;
			case "4":
				ComplexFunctionalityService.sefovi();
				break;

			}

		} while (!answer.equalsIgnoreCase("X"));
	}

}
