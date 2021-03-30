package domein;

import java.util.List;

public class ContractType {
	private int contractTypeId;
	private String naam;
	private boolean status;
	private int maximaleAfhandelTijd;
	private int minimaleDoorloopTijd;
	private double prijs;
	
	private List<Contract> contracten;
	private List<ManierTicketAanmaken> manierTicketAanmaken;
	private MogelijkeTijdstippenAanmaken mogelijkeTijdsstippenAanmaken;
	
	
	protected ContractType(int contractTypeId, String naam, int maximaleAfhandelTijd, int minimaleDoorloopTijd, 
			double prijs, List<ManierTicketAanmaken> manierTicketAanmaken, MogelijkeTijdstippenAanmaken mogelijkeTijdsstippenAanmaken) {
		setContractTypeId(contractTypeId);
		setNaam(naam);
		status = true;
		setMaximaleAfhandelTijd(maximaleAfhandelTijd);
		setMinimaleDoorloopTijd(minimaleDoorloopTijd);
		setPrijs(prijs);
		setManierTicketAanmaken(manierTicketAanmaken);
		setMogelijkeTijdsstippenAanmaken(mogelijkeTijdsstippenAanmaken);
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
	
	public List<ManierTicketAanmaken> getManierTicketAanmaken() {
		return manierTicketAanmaken;
	}
	
	private void setManierTicketAanmaken(List<ManierTicketAanmaken> manierTicketAanmaken) {
		this.manierTicketAanmaken = manierTicketAanmaken;
	}
	
	public MogelijkeTijdstippenAanmaken getMogelijkeTijdsstippenAanmaken() {
		return mogelijkeTijdsstippenAanmaken;
	}
	
	private void setMogelijkeTijdsstippenAanmaken(MogelijkeTijdstippenAanmaken mogelijkeTijdsstippenAanmaken) {
		this.mogelijkeTijdsstippenAanmaken = mogelijkeTijdsstippenAanmaken;
	}	
}
