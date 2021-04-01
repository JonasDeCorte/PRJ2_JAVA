package domein;

import java.util.List;

import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;

public class ContractType {
	private int contractTypeId;
	private String naam;
	private boolean status;
	private int maximaleAfhandelTijd;
	private int minimaleDoorloopTijd;
	private double prijs;
	
	private List<Contract> contracten;
	private List<TICKETAANMAAKMETHODE> ticketAanmaakMethode;
	private TICKETAANMAAKTIJD ticketAanmaakTijd;
		
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
		this.naam = naam;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	private void setStatus(boolean status) {
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
		this.ticketAanmaakMethode = ticketAanmaakMethode;
	}

	public TICKETAANMAAKTIJD getTicketAanmaakTijd() {
		return ticketAanmaakTijd;
	}

	private void setTicketAanmaakTijd(TICKETAANMAAKTIJD ticketAanmaakTijd) {
		this.ticketAanmaakTijd = ticketAanmaakTijd;
	}
}
