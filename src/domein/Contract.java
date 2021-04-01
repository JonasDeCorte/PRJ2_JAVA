package domein;

import java.time.LocalDateTime;
import java.util.List;

import domein.enumerations.CONTRACTSTATUS;

public class Contract {
	private int contractnummer;
	private int doorlooptijd;
	private LocalDateTime startdatum;
	private LocalDateTime einddatum;
	
	private CONTRACTSTATUS contractstatus;
	private ContractType contracttype;
	private Klant klant;
	private List<Ticket> tickets;
	
	public Contract(int contractnummer, int doorlooptijd, LocalDateTime startdatum, LocalDateTime einddatum, 
			ContractType contracttype, Klant klant) {
		setContractnummer(contractnummer);
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

	public int getDoorlooptijd() {
		return doorlooptijd;
	}

	private void setDoorlooptijd(int doorlooptijd) {
		this.doorlooptijd = doorlooptijd;
	}

	public LocalDateTime getStartdatum() {
		return startdatum;
	}

	private void setStartdatum(LocalDateTime startdatum) {
		this.startdatum = startdatum;
	}

	public LocalDateTime getEinddatum() {
		return einddatum;
	}

	private void setEinddatum(LocalDateTime einddatum) {
		this.einddatum = einddatum;
	}

	public CONTRACTSTATUS getContractstatus() {
		return contractstatus;
	}

	private void setContractstatus(CONTRACTSTATUS contractstatus) {
		this.contractstatus = contractstatus;
	}

	public ContractType getContracttype() {
		return contracttype;
	}

	private void setContracttype(ContractType contracttype) {
		this.contracttype = contracttype;
	}

	public Klant getKlant() {
		return klant;
	}

	private void setKlant(Klant klant) {
		this.klant = klant;
	}	
}
