package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class GlumciPlate {
	private int mbg;
	private String ime;
	private double plata;
	private double honorar; 
	
	public GlumciPlate(int mbg, String ime, double plata, double honorar) {
		super();
		this.mbg = mbg;
		this.ime = ime;
		this.plata = plata;
		this.honorar = honorar;
		
	}
	public double getHonorar() {
		return honorar;
	}
	public void setHonorar(double honorar) {
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
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%4d %-10s %-8f %-8f", mbg, ime, plata, honorar);
	}
	
	
	
}
