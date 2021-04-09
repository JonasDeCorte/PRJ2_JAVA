package repository;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domein.Adres;
import domein.Werknemer;
import domein.enumerations.WERKNEMERROL;

public class TestJPA {

    public static void main(String args[]) {
        
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_details");
    	String[] telenummers = {"32"};
    	Werknemer test = new Werknemer("JensVH", "123", "Jens3", "Vanhee", "Jens@gmail.be", telenummers , WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Brugge", "8000", "Straat", 1, ""));
     	Werknemer test2 = new Werknemer("Test", "test", "test", "test", "test@gmail.be", telenummers , WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Brugge", "8000", "Straat", 1, ""));
        //vraag aan de factory een entityManager
        EntityManager entityManager = emf.createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        ////persisteer de 3 objecten
        entityManager.persist(test);
        entityManager.persist(test2);
     
        //commit
        entityManager.getTransaction().commit();
        
        //sluit de entityManager
        entityManager.close();
        
        //sluit de factory
        emf.close();
    }

}