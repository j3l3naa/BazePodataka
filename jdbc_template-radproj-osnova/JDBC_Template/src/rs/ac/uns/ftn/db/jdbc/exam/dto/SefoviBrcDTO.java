package rs.ac.uns.ftn.db.jdbc.exam.dto;

public class SefoviBrcDTO {
	private int mbr;
	private String ime;
	private String prz;
	private int brc;
	
	public SefoviBrcDTO(int mbr, String ime, String prz, int brc) {
		super();
		this.mbr = mbr;
		this.ime = ime;
		this.prz = prz;
		this.brc = brc;
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
	public int getBrc() {
		return brc;
	}
	public void setBrc(int brc) {
		this.brc = brc;
	}
	
	public static String getFormattedHeader() {
		return String.format("%-4s %-20s %-20s %-4s", "MBR", "IME", "PRZ", "BRC");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%-4d %-20s %-20s %-4d", mbr, ime, prz, brc);
	}
	
}
