package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class GlumacDTO {
	private int ime;
	private double max_honorar;
	private String nazivpred;
	private String imeulo;
	
	
	
	

	public GlumacDTO(int ime, double hon, String nazivpred, String imeulo) {
		
		this.ime = ime;
		this.max_honorar = hon;
		this.nazivpred = nazivpred;
		this.imeulo = imeulo;
	}
	public int getIme() {
		return ime;
	}
	public void setIme(int ime) {
		this.ime = ime;
	}
	public double getMax_honorar() {
		return max_honorar;
	}
	public void setMax_honorar(double max_honorar) {
		this.max_honorar = max_honorar;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%-6d %-6f %-30s %-30s", ime, max_honorar, nazivpred, imeulo);
	}
}
