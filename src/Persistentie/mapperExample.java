package Persistentie;

public class mapperExample {
	/*
	//Variabelen
	 private static final String INSERT_SPELER = "INSERT INTO ID222177_g34.speler (gebruikersnaam, wachtwoord, administrator, naam, voornaam)"
	            + "VALUES (?, ?, ?, ?, ?)";
	 
	 //Speler toevoegen aan db
	 public void voegToe(Speler speler) {
		 String admin = "false";
	        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
	             PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
	        	query.setString(1, speler.getGebruikersnaam());
	        	query.setString(2, speler.getWachtwoord());
	           	if(speler.isAdministrator()) {
	           		admin = "true";
	           	}
	           	query.setString(3, admin);
	           	query.setString(4, speler.getNaam());
	           	query.setString(5, speler.getVoornaam());
	           	query.executeUpdate();
	        }catch (SQLException ex) {
	        	throw new RuntimeException(ex);
	        }
	   }
	 
	 //Geeft speler terug uit db
	  public Speler geefSpeler(String gebruikersnaam) {
	       	Speler speler = null;
	        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
	                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g34.speler WHERE gebruikersnaam = ?")) 
	        {
	            query.setString(1, gebruikersnaam);
	            try (ResultSet rs = query.executeQuery()) 
	            {
	                if (rs.next()) {
	                    String username = rs.getString("gebruikersnaam");
	                    String wachtwoord = rs.getString("wachtwoord");
	                    String admin = rs.getString("administrator");
	                    
	                    if(admin.equalsIgnoreCase("true")) {
	                    	speler = new Speler(username, wachtwoord,true);	
	                    }else {
	                    	speler = new Speler(username, wachtwoord,false);	
	                    }
	                }
	            }
	        }catch (SQLException ex ) {
	            throw new RuntimeException(ex);
	        }
	        return speler;
	  }

	  public List<Speler> geefSpelers() {
		  List<Speler> spelers = new ArrayList<>();
		  try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				  PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g34.speler");
				  ResultSet rs = query.executeQuery()) {
			  while (rs.next()) {
				  String naam = rs.getString("naam");
	              String voornaam = rs.getString("voornaam");
	              String username = rs.getString("gebruikersnaam");                
	              String wachtwoord = rs.getString("wachtwoord");
	              String rechten = rs.getString("administrator");
	              if(rechten == "true") {
	            	  spelers.add(new Speler(naam, voornaam, wachtwoord, username, true));
	              }else {
	            	  spelers.add(new Speler(naam, voornaam, wachtwoord, username, false));
	              }
			  }
	       	  } catch (SQLException ex) {
	       		  throw new RuntimeException(ex);
	       	  }
		  return spelers;
	  }
	  */
}
