package rs.ac.uns.ftn.db.jdbc.pozoriste.model;

public class Mesto {
	private int idm;
	private String naziv;
	private int idd;
	
	public Mesto() {}
	
	public Mesto(int idm, String naziv, int idd) {
		super();
		this.idm = idm;
		this.naziv = naziv;
		this.idd = idd;
	}
	public int getIdm() {
		return idm;
	}
	public void setIdm(int idm) {
		this.idm = idm;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getIdd() {
		return idd;
	}
	public void setIdd(int idd) {
		this.idd = idd;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%4d %-10s %4d", idm, naziv, idd);
	}
}
