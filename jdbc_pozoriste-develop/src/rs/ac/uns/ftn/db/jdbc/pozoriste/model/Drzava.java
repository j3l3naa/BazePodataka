package rs.ac.uns.ftn.db.jdbc.pozoriste.model;

public class Drzava {
	private int idd;
	private String naziv;
	
	public Drzava(int idd, String naziv) {
		super();
		this.idd = idd;
		this.naziv = naziv;
	}
	public int getIdd() {
		return idd;
	}
	public void setIdd(int idd) {
		this.idd = idd;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
