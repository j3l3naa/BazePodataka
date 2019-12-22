package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class OstaliGlumciDTO {
	private int mbg;
	private String imeg;
	public OstaliGlumciDTO(int mbg, String imeg) {
		super();
		this.mbg = mbg;
		this.imeg = imeg;
	}
	public int getMbg() {
		return mbg;
	}
	public void setMbg(int mbg) {
		this.mbg = mbg;
	}
	public String getImeg() {
		return imeg;
	}
	public void setImeg(String imeg) {
		this.imeg = imeg;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\t\t\tMBG: " + mbg + " Ime: " + imeg;
	}
}
