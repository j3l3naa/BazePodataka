package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class GlumciHonorariDTO {
	
	private int mbg;
	private String ime;
	private double honorar;
	
	public GlumciHonorariDTO(int mbg, String ime, double honorar) {
		super();
		this.mbg = mbg;
		this.ime = ime;
		this.honorar = honorar;
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
	public double getHonorar() {
		return honorar;
	}
	public void setHonorar(double honorar) {
		this.honorar = honorar;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%4d %-10s %-8f", mbg, ime, honorar);
	}
	
}
