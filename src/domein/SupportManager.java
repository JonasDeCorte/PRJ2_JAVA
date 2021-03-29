package domein;

import java.util.List;

public class SupportManager extends Werknemer {

	public SupportManager(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres,
			int personeelsnummer, String land, String gemeente, String straat) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, personeelsnummer, land, gemeente, straat);
	}
	
	public void klantAanmaken() {
		
	}
	
	public void voegContractTypeToe() {
		
	}
	
	public void wijzigContractType() {
		
	}
	
	public ContractType haalContractTypeOp() {
		return null;
	}
	
	public List<Rapport> raadplegenRapporten(){
		return null;
	}
	
	/*
	public void raaplegenKPI(){
	
	}
	*/

}
