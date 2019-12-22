package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

import java.util.List;

public class GlumciUlogeDTO {
	private int mbg;
	private String imeg;
	private String nazivpred;
	private String imeulo;

	public GlumciUlogeDTO(int mbg, String imeg, String nazivpred, String imeulo) {
		super();
		this.mbg = mbg;
		this.imeg = imeg;
		this.nazivpred = nazivpred;
		this.imeulo = imeulo;
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
	public String getNazivpred() {
		return nazivpred;
	}
	public void setNazivpred(String nazivpred) {
		this.nazivpred = nazivpred;
	}
	public String getImeulo() {
		return imeulo;
	}
	public void setImeulo(String imeulo) {
		this.imeulo = imeulo;
	}

	
	@Override
	public String toString() {
		return "Mbg: " + mbg + " Ime: " + imeg + " Naziv predstave: " + nazivpred + " Ime uloge: " + imeulo + "\n";
	}
	
}
