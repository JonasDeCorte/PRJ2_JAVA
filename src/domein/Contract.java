package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import domein.enumerations.CONTRACTSTATUS;
@Entity
public class Contract implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contractnummer;
	
	private String titel;
	private int doorlooptijd;
	private LocalDate startdatum;
	private LocalDate einddatum;
	
	private CONTRACTSTATUS contractstatus;
	@ManyToOne
	private ContractType contracttype;
	@ManyToOne
	private Klant klant;
	@OneToMany(mappedBy = "contract", cascade = CascadeType.PERSIST)
	private List<Ticket> tickets;
	
	public Contract() {
		contractstatus = CONTRACTSTATUS.IN_BEHANDELING;
	}

	public Contract(String titel, int doorlooptijd, LocalDate startdatum, LocalDate einddatum, 
			ContractType contracttype, Klant klant) {
		setTitel(titel);
		setDoorlooptijd(doorlooptijd);
		setStartdatum(startdatum);
		setEinddatum(einddatum);		
		setContracttype(contracttype);
		setKlant(klant);
		contractstatus = CONTRACTSTATUS.IN_BEHANDELING;
	}

	public int getContractnummer() {
		return contractnummer;
	}

	private void setContractnummer(int contractnummer) {
		this.contractnummer = contractnummer;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getDoorlooptijd() {
		return doorlooptijd;
	}

	private void setDoorlooptijd(int doorlooptijd) {
		this.doorlooptijd = doorlooptijd;
	}

	public LocalDate getStartdatum() {
		return startdatum;
	}

	private void setStartdatum(LocalDate startdatum) {
		if (startdatum != null) {
			this.startdatum = startdatum;
		} else {
			throw new IllegalArgumentException("startdatum kan niet leeg zijn.");
		}
	}

	public LocalDate getEinddatum() {
		return einddatum;
	}

	private void setEinddatum(LocalDate einddatum) {
		if (einddatum != null) {
			if (einddatum.isBefore(startdatum.plusYears(1))) {
				throw new IllegalArgumentException("De looptijd moet minimum 1 jaar zijn en de eindDatum mag ook niet kleiner zijn dan de startdatum.");
			}
			this.einddatum = einddatum;
		} else {
			throw new IllegalArgumentException("einddatum mag niet leeg zijn.");
		}
	}

	public CONTRACTSTATUS getContractstatus() {
		return contractstatus;
	}

	public void setContractstatus(CONTRACTSTATUS contractstatus) {
		if (contractstatus != null) {
			this.contractstatus = contractstatus;
		} else {
			throw new IllegalArgumentException("Contractstatus moet ingevuld zijn.");
		}
	}

	public ContractType getContracttype() {
		return contracttype;
	}

	private void setContracttype(ContractType contracttype) {
		if (contracttype != null) {
			this.contracttype = contracttype;
		} else {
			throw new IllegalArgumentException("een contract moet een type hebben!");
		}
	}
	

	public Klant getKlant() {
		return klant;
	}

	private void setKlant(Klant klant) {
		if (klant != null) {
			this.klant = klant;
		} else {
			throw new IllegalArgumentException("klant kan niet leeg zijn.");
		}
	}

	@Override
	public String toString() {
		return titel ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
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
		Contract other = (Contract) obj;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}
}
