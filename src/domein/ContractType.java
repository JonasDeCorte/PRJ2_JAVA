package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;
@Entity
@NamedQueries({
	@NamedQuery(name = "ContractType.bestaatContractType", query = "SELECT c FROM ContractType c where c.naam = :naam") })
public class ContractType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contractTypeId;
	private String naam;
	private boolean status;
	private int maximaleAfhandelTijd;
	private int minimaleDoorloopTijd;
	private double prijs;
	@OneToMany(mappedBy = "contracttype", cascade = CascadeType.PERSIST)
	private List<Contract> contracten;
	@ElementCollection
	private List<TICKETAANMAAKMETHODE> ticketAanmaakMethode;
	
	private TICKETAANMAAKTIJD ticketAanmaakTijd;
		
	public ContractType() {

	}

	public ContractType(int contractTypeId, String naam, int maximaleAfhandelTijd, int minimaleDoorloopTijd, 
			double prijs, List<TICKETAANMAAKMETHODE> ticketAanmaakMethode, TICKETAANMAAKTIJD ticketAanmaakTijd) {
		setContractTypeId(contractTypeId);
		setNaam(naam);
		status = true;
		setMaximaleAfhandelTijd(maximaleAfhandelTijd);
		setMinimaleDoorloopTijd(minimaleDoorloopTijd);
		setPrijs(prijs);
		setTicketAanmaakMethode(ticketAanmaakMethode);
		setTicketAanmaakTijd(ticketAanmaakTijd);
	}
	
	public int getContractTypeId() {
		return contractTypeId;
	}
	
	private void setContractTypeId(int contractTypeId) {
		this.contractTypeId = contractTypeId;
	}
	
	public String getNaam() {
		return naam;
	}
	
	private void setNaam(String naam) {
		if (naam == null || naam.isBlank()) {
			throw new IllegalArgumentException("contracttype mag niet leeg zijn.");
		}
			this.naam = naam;	
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getMaximaleAfhandelTijd() {
		return maximaleAfhandelTijd;
	}
	
	private void setMaximaleAfhandelTijd(int maximaleAfhandelTijd) {
		this.maximaleAfhandelTijd = maximaleAfhandelTijd;
	}
	
	public int getMinimaleDoorloopTijd() {
		return minimaleDoorloopTijd;
	}
	
	private void setMinimaleDoorloopTijd(int minimaleDoorloopTijd) {
		this.minimaleDoorloopTijd = minimaleDoorloopTijd;
	}
	
	public double getPrijs() {
		return prijs;
	}
	
	private void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	
	public List<Contract> getContracten() {
		return contracten;
	}
	
	private void setContracten(List<Contract> contracten) {
		this.contracten = contracten;
	}

	public List<TICKETAANMAAKMETHODE> getTicketAanmaakMethode() {
		return ticketAanmaakMethode;
	}

	private void setTicketAanmaakMethode(List<TICKETAANMAAKMETHODE> ticketAanmaakMethode) {
		if (ticketAanmaakMethode == null) {
			throw new IllegalArgumentException("ticket moet een aanmaak methode hebben");
		}			
		this.ticketAanmaakMethode = ticketAanmaakMethode;
	}

	public TICKETAANMAAKTIJD getTicketAanmaakTijd() {
		return ticketAanmaakTijd;
	}

	private void setTicketAanmaakTijd(TICKETAANMAAKTIJD ticketAanmaakTijd) {
		if (ticketAanmaakTijd == null)
			throw new IllegalArgumentException("ticket moet een aanmaaktijd hebben.");
		this.ticketAanmaakTijd = ticketAanmaakTijd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContractType other = (ContractType) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
}
