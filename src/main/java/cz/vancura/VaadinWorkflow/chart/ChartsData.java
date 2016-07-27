package cz.vancura.VaadinWorkflow.chart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChartsData {
	
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://wh19.farma.gigaserver.cz:3306/vancura_cz_";
	final String USER = "";
	final String PASS = "";
	Connection conn = null;
	Statement stmt = null;
    
    List<Spotreba> spotrebaPole = new ArrayList<Spotreba>();
  
	public ChartsData() {
    	   
 	   try {
     	   
 	       Class.forName("com.mysql.jdbc.Driver");
 		   conn = DriverManager.getConnection(DB_URL,USER,PASS);
 		   stmt = conn.createStatement();
 		   
 		   String sql = null;
 		   
 		   sql = "SELECT timestamp, ele, plyn, voda, temp FROM energie ORDER BY timestamp";
 		   System.out.println("Chart - " + sql);
 		   
 		   ResultSet rs = stmt.executeQuery(sql);
 		   
 		   int pocet=0;
 		   
 		   while(rs.next()){
		    
 			   	  pocet = pocet+1;
 			   	  
				  String db_date  = rs.getString("timestamp").substring(0,10) ; // 2016-06-03
				  DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
				  Date db_date2 = format.parse(db_date); // prevod na Date
				  long db_date3 = db_date2.getTime(); // prevod na milisec
				  // System.out.println("Datum prevod : " + db_date + " -> " + db_date2 + " -> " + db_date3);
			
				  int db_value1 = Integer.parseInt(rs.getString("ele"));
				  int db_value2 = Integer.parseInt(rs.getString("plyn"));
				  int db_value3 = Integer.parseInt(rs.getString("voda"));
				  int db_value4 = Integer.parseInt(rs.getString("temp"));
				       
				  spotrebaPole.add(new Spotreba(db_date3, db_value1, db_value2, db_value3, db_value4));
				  System.out.print("Table - From dB : date=" + db_date.substring(0, 10) + " ele=" + db_value1 + " plyn2=" + db_value2  + " voda=" + db_value3 + " temp=" + db_value4 + "\n");		       
			   }
 		   
 		   if (pocet ==  0 ) {
 			   System.out.println("POZOR : v dB nic nenalezeno !");
 		   }
 		   
 		   rs.close();
 		   stmt.close();
 		   conn.close();
        
        	  }catch(SQLException se){
 		      //Handle errors for JDBC
 			  se.printStackTrace();
 			  System.out.println("JDBC Error " + se);
 			  
 		  }catch(Exception e){
 		      //Handle errors for Class.forName
 		      e.printStackTrace();
 		      System.out.println("Error " + e);
 		      
 		  }finally{
 		  //finally block used to close resources
 		      try{
 		         if(stmt!=null)
 		            stmt.close();
 		      }catch(SQLException se2){
 		      }// nothing we can do
 		      try{
 		         if(conn!=null)
 		            conn.close();
 		      }catch(SQLException se){
 		         se.printStackTrace();
 		      }//end finally try
 		   }//end try
    

    }
      
 
	public List<Spotreba> getSpotrebaPole() {
		return spotrebaPole;
	}


	public void setSpotrebaPole(List<Spotreba> spotrebaPole) {
		this.spotrebaPole = spotrebaPole;
	}
    
}
