package de.fhkoeln.gm.wba2.phase2.jersey.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.Temperatur;


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
			  System.out.println("Connect nicht m�glich");
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
	 
	        // Ergebniss�tze durchfahren.
	        while (result.next()) {        
	          output = result.getString("RAUM") + ": " + result.getString("WERT") + " Grad Celsius";
	        }
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    return output;
	  }
	  
	  public static void putTemperatur(ArrayList<Temperatur> temperatur)
	  {
	    conn = getInstance();
	 
	    if(conn != null)
	    {
	      // Anfrage-Statement erzeugen.
	      Statement query;
	      try {
	        query = conn.createStatement();
	        Temperatur newTemperatur = temperatur.get(0);
	        // Ergebnistabelle erzeugen und abholen.
	        String sql = "INSERT INTO TEMPERATUR(id, raum, wert, einheit) VALUES(?, ?, ?, ?)";
	        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	        preparedStatement.setLong(1, newTemperatur.getId());
	        preparedStatement.setString(2, newTemperatur.getRaum());
	        preparedStatement.setString(3, newTemperatur.getWert());
	        preparedStatement.setString(4, newTemperatur.getEinheit());
	        preparedStatement.executeUpdate();
	        System.out.println("DEBUG: adding new temperatur to DB");
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
}
