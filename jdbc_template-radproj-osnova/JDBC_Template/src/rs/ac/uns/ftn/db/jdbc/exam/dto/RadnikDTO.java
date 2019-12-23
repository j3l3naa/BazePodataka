package rs.ac.uns.ftn.db.jdbc.exam.dto;

public class RadnikDTO {
	private int mbr;
	private String ime;
	private String prz;
	public RadnikDTO(int mbr, String ime, String prz) {
		super();
		this.mbr = mbr;
		this.ime = ime;
		this.prz = prz;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%-4d %-10s %-10s", mbr, ime, prz);
	}
}
