package domein;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Adres {
	@Column(nullable = false)
	private String land;
	@Column(nullable = false)
	private String gemeente;
	@Column(nullable = false)
	private String postcode;
	@Column(nullable = false)
	private String straat;
	@Column(nullable = false)
	private int huisnummer;
	private String busnummer;

	public Adres() {
		super();
	}

	public Adres(String land, String gemeente, String postcode, String straat, int huisnummer, String busnummer) {
		setLand(land);
		setGemeente(gemeente);
		setPostcode(postcode);
		setStraat(straat);
		setHuisnummer(huisnummer);
		setBusnummer(busnummer);
	}

	public String getLand() {
		return land;
	}

	private void setLand(String land) {
		if (land != null) {
			this.land = land;
		} else {
			throw new IllegalArgumentException("Land mag niet leeg zijn.");
		}

	}

	public String getGemeente() {
		return gemeente;
	}

	private void setGemeente(String gemeente) {
		if (gemeente != null && !gemeente.isBlank() && !gemeente.isEmpty()) {
			this.gemeente = gemeente;
		} else {
			throw new IllegalArgumentException("Gemeente kan niet leeg zijn.");
		}
	}

	public String getPostcode() {
		return postcode;
	}

	private void setPostcode(String postcode) {
		if (postcode != null && !postcode.isBlank() && !postcode.isEmpty()) {
			this.postcode = postcode;
		} else {
			throw new IllegalArgumentException("ongeldige postcode.");
		}
	}

	public String getStraat() {
		return straat;
	}

	private void setStraat(String straat) {
		if (straat != null && !straat.isBlank() && !straat.isEmpty()) {
			this.straat = straat;
		} else {
			throw new IllegalArgumentException("straat kan niet leeg zijn.");
		}
	}

	public int getHuisnummer() {
		return huisnummer;
	}

	private void setHuisnummer(int huisnummer) {
		if (huisnummer >= 1 && huisnummer <= 1000) {
			this.huisnummer = huisnummer;
		} else {
			throw new IllegalArgumentException("ongeldig huisnummer.");
		}
	}

	public String getBusnummer() {
		return busnummer;
	}

	private void setBusnummer(String busnummer) {
		if (busnummer == null) {
			this.busnummer = null;
		} else if (!busnummer.matches("[a-zA-Z0-9]*")) {
			throw new IllegalArgumentException("busnummer moet een aplhanumeric karakter zijn.");
		} else {
			this.busnummer = busnummer;
		}
	}
}
