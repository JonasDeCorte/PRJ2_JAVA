"# 2021-java-g-18" 



## Prerequisites
### Installation

[JARS (Testen, JPA, SqlConnector).zip](https://github.com/HoGentProjectenII/2021-java-g-18/files/6221737/JARS.Testen.JPA.SqlConnector.zip)

[JARS (JavaFx).zip](https://github.com/HoGentProjectenII/2021-java-g-18/files/6221759/JARS.JavaFx.zip)

[Java SE installed](https://www.oracle.com/java/technologies/javase-downloads.html)

[Enkel indien GEEN JRE aanwezig op laptop: JRE 10 downloaden](https://www.oracle.com/java/technologies/java-archive-javase10-downloads.html)

[ECLIPSE](https://www.eclipse.org/downloads/packages/)

correct configuration refer to [OO_P 3 slides](https://chamilo.hogent.be/index.php?go=CourseViewer&application=Chamilo%5CApplication%5CWeblcms&course=38255&tool=Document&publication_category=243106&browser=Table&tool_action=Viewer&publication=1616608) 

correct configuration  [JAVAFX](https://gluonhq.com/products/javafx/) refer to [OO_P 3 slides](https://chamilo.hogent.be/index.php?go=CourseViewer&application=Chamilo%5CApplication%5CWeblcms&course=38255&tool=Document&publication_category=243106&browser=Table&tool_action=Viewer&publication=1616608)

- SQL Database installed on localhost 
  - If you're an apple fanboi, refer to Web III for running a SQL Server Database in a Docker container.

## Getting started -  Development

````
git clone this repository
````

## setting up the database
- Login to Microsoft SQL Server Management Studio 
- Lets first check something, right  click on your local sql server => properties => security 
  - Make sure SQL Server and Windows Authentication mode is selected! 
    
    
- navigate to the folder 'Security', open it
  - right click  'Logins' => new login 
    - Login name = test
- Activate SQL Server authentication
    - password: test
- other options: DEFAULT 


- Log in with the account you just created. login name = test , password = test
  - Create a new database called "Projecten2"
  - Owner -> Browse -> select 'test' 
  - ok ok ok ok ok ok ok ... until the DB is created. 
 - Open the java project you just cloned
 - navigate to the META-INF folder 
   - open persistence.xml 
     - change    
      ````  name="javax.persistence.schema-generation.database.action" value="none"````
     -  to       
      ````  name="javax.persistence.schema-generation.database.action" value="drop-and-create"````

- Navigate to the main folder => StartUp.java
  - uncomment the section```` /* DataInitializer dataInit = new DataInitializer(); dataInit.initializeData();*/ ```` so the datainitalize is called. 

- Run our project. 
- check if the database is created correctly in  Microsoft SQL Server Management Studio  
  -   comment the section```` /* DataInitializer dataInit = new DataInitializer(); dataInit.initializeData();*/ ````  again.
  -   navigate to the META-INF folder 
   - open persistence.xml 
     - change    
    ````  name="javax.persistence.schema-generation.database.action" value="drop-and-create"````
     - to       
       ````  name="javax.persistence.schema-generation.database.action" value="none"````
 
  
## Login 

### Admin
````
gebruikersnaam = admin
Wachtwoord = admin
````
### Tech
````
gebruikersnaam = tech
Wachtwoord = tech
````
### Supportmanager
````
gebruikersnaam = supp
Wachtwoord = supp
````



## Klassendiagram
![image](https://user-images.githubusercontent.com/47521716/118832503-f646d180-b8c0-11eb-8aa4-05eb3e7bb619.png)

