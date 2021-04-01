package persistentie;
import domein.TicketType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class mapperExample {
	
	  public List<TicketType> geefTicketTypes() {
		  List<TicketType> types = new ArrayList<TicketType>();
		  try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				 
				  PreparedStatement query = conn.prepareStatement("SELECT * FROM Projecten2.dbo.TicketType"); 
				 
				  ResultSet rs = query.executeQuery()) {
			  while (rs.next()) {
				  int ticketTypeId = rs.getInt("id");
				  String naam = rs.getString("Naam");
	              String omschrijving = rs.getString("Omschrijving");                        
	            	  types.add(new TicketType(ticketTypeId,naam,omschrijving));
			  
			   }
			  System.out.println("connection");
	       	  } catch (SQLException ex) {
	       		  throw new RuntimeException(ex);
	       	  }
		  return types;
	  }
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
	 
	 /

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
