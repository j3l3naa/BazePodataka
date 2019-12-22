package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class GlumciHonorarUdeo {
	private int mbg;
	private String ime;
	private String udeo_honorara = "0";
	
	
	
	public GlumciHonorarUdeo(int mbg, String ime) {
		super();
		this.mbg = mbg;
		this.ime = ime;
		this.udeo_honorara = "0";
	}
	
	public int getMbg() {
		return mbg;
	}
	public void setMbg(int mbg) {
		this.mbg = mbg;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getUdeo_honorara() {
		return udeo_honorara;
	}
	public void setUdeo_honorara(String string) {
		this.udeo_honorara = string;
	}
	
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%4d %-10s %-8s", mbg, ime, udeo_honorara);
	}
	
}
