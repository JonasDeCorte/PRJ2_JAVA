package domein.controllers;

import domein.Bedrijf;
import domein.beheerders.BedrijfBeheerder;
import javafx.collections.ObservableList;

public class BedrijfsBeheerController {
	private BedrijfBeheerder bedrijfbeheerder;
	public BedrijfsBeheerController(BedrijfBeheerder bedrijfbeheerder) {
		// TODO Auto-generated constructor stub
		this.bedrijfbeheerder = bedrijfbeheerder;
	}
	public BedrijfsBeheerController() {
		// TODO Auto-generated constructor stub
		this(new BedrijfBeheerder());
	}
	public boolean bestaatBedrijf(String bedrijfsnaam) {
		return bedrijfbeheerder.bestaatBedrijf(bedrijfsnaam);
	}

	public void voegBedrijfToe(Bedrijf bedrijf) {
		bedrijfbeheerder.voegBedrijfToe(bedrijf);
	}

	public void wijzigBedrijf(Bedrijf bedrijf) {
		bedrijfbeheerder.wijzigBedrijf(bedrijf);
	}

	public void pasFilterAanBedrijf( String bedrijfsnaam,String land, String Gemeente)
	{
		bedrijfbeheerder.pasFilterAan(bedrijfsnaam, land,Gemeente);
	}
	public ObservableList<Bedrijf> getAllBedrijven() {
		return bedrijfbeheerder.haalBedrijvenOp();
	}
}
