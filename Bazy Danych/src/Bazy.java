import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
 
public class Bazy {
	
	private static long start, stop;
	 
	// start timing
    public void start() {
        start = System.currentTimeMillis();
    }
 
    // stop timing
    public void stop() {
        stop = System.currentTimeMillis();
    }
    
    public static void main(String[] args) {
    	
    	Bazy stopwatch = new Bazy();
        stopwatch.start();
        
    	try {
    		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "");
        		    
        	Statement myStmt = myConn.createStatement();
        		   
        	//ZAD 1
        	/**ResultSet result = myStmt.executeQuery("SELECT c.name, ci.Name AS nam FROM country c JOIN city ci ON ci.ID=c.Capital");
        		   
        	//results set
        	while (result.next()) {
        		System.out.println(result.getString("name")+ " , "+result.getString("nam"));
        	}*/        		   
        		   
        	//ZAD 2
        	/**ResultSet result = myStmt.executeQuery("SELECT country.Name, country.SurfaceArea\r\n" + 
        		"FROM `country`\r\n" + 
        		"WHERE SurfaceArea>300.00");
        		   
        	while (result.next()) {
        		System.out.println(result.getString("name")+ " , "+result.getString("SurfaceArea"));
        	}*/
        		   
        	//ZAD 3
        	/**ResultSet result = myStmt.executeQuery("SELECT c.name, ci.Name AS nam\r\n" + 
        		"FROM country c\r\n" + 
        		"JOIN city ci ON ci.CountryCode=c.Code\r\n" + 
        		"WHERE ci.Population>10000\r\n" + 
        		"ORDER BY ci.Name");
           		   
           	while (result.next()) {
           		System.out.println(result.getString("name")+ " , "+result.getString("nam"));
           	}*/
        		   
        	//ZAD4
        	ResultSet result = myStmt.executeQuery("SELECT country.Continent, COUNT(country.Continent) as \"number of countries\"\r\n" + 
        		"FROM country\r\n" + 
        		"GROUP BY country.Continent");
              		   
        	while (result.next()) {
              	System.out.println(result.getString("Continent")+ " , "+result.getString("number of countries"));
        	}
        }
        catch (Exception exc) {
        	exc.printStackTrace();
        }
    	stopwatch.stop();
    	System.out.println(stop - start + "ms");
   }       
}