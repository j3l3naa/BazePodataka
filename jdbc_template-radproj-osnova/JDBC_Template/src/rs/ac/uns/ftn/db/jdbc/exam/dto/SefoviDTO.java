package rs.ac.uns.ftn.db.jdbc.exam.dto;

public class SefoviDTO {
	private int mbr;
	private String ime;
	private String prz;
	private int broj_podredjenih;
	public SefoviDTO(int mbr, String ime, String prz, int broj_podredjenih) {
		super();
		this.mbr = mbr;
		this.ime = ime;
		this.prz = prz;
		this.broj_podredjenih = broj_podredjenih;
	}
	public int getMbr() {
		return mbr;
	}
	public void setMbr(int mbr) {
		this.mbr = mbr;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrz() {
		return prz;
	}
	public void setPrz(String prz) {
		this.prz = prz;
	}
	public int getBroj_podredjenih() {
		return broj_podredjenih;
	}
	public void setBroj_podredjenih(int broj_podredjenih) {
		this.broj_podredjenih = broj_podredjenih;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%-4d %-10s %-10s %-4d", mbr, ime, prz, broj_podredjenih);
	}
	
	
}
