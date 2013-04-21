package de.fhkoeln.gm.wba2.phase2.jersey.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnection {
	  private static Connection conn = null;
	 
	  // Hostname
	  private static String dbHost = "localhost";
	 
	  // Port -- Standard: 3306
	  private static String dbPort = "3306";
	 
	  // Datenbankname
	  private static String database = "smartbuilding";
	 
	  // Datenbankuser
	  private static String dbUser = "sqluser";
	 
	  // Datenbankpasswort
	  private static String dbPassword = "sqluserpw";
	  
	  private MySQLConnection() {
		  try{
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					  + dbPort + "/" + database + "?user=" + dbUser + "&" 
					  + "password=" + dbPassword);
		  } catch (ClassNotFoundException e) {
		      System.out.println("Treiber nicht gefunden");
		  } catch (SQLException e) {
			  System.out.println("Connect nicht möglich");
		  }
	  }
	  
	  private static Connection getInstance(){
		  if(conn == null)
			  new MySQLConnection();
		  return conn;
	  }
	  
	  public static String getTemperatur(String ort)
	  {
		String output = "";
	    conn = getInstance();
	 
	    if(conn != null)
	    {
	      // Anfrage-Statement erzeugen.
	      Statement query;
	      try {
	        query = conn.createStatement();
	 
	        // Ergebnistabelle erzeugen und abholen.
	        String sql = "SELECT WERT, RAUM FROM TEMPERATUR WHERE RAUM = '" + ort + "';";
	        ResultSet result = query.executeQuery(sql);
	 
	        // Ergebnissätze durchfahren.
	        while (result.next()) {        
	          output = result.getString("RAUM") + ": " + result.getString("WERT") + " Grad Celsius";
	        }
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    return output;
	  }
}
