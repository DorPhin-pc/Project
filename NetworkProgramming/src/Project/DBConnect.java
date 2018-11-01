package Project;
import java.sql.*;
import java.util.ArrayList;


public class DBConnect {
      
      public static void main(String[] args)
      {       
          
      }
      public static ArrayList<String>Tour(String Loc,String Loc2){
    	  String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=지역관광지;user=pcy;password=1111";  
	       ArrayList<String> Loca = new ArrayList<String>();

    	          Connection con = null;  
    	          Statement stmt = null;  
    	          ResultSet rs = null;  

    	          try {  
    	             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
    	             con = DriverManager.getConnection(connectionUrl);  

    	             String SQL = "SELECT distinct 관광지명 FROM [전국$] where 지역명 = '" + Loc + "' and " + "관광지역 = '" + Loc2 + "'"  ;  
    	             stmt = con.createStatement();  
    	             rs = stmt.executeQuery(SQL);

    	             while (rs.next()) {   
    	                Loca.add(rs.getString("관광지명"));
    	             }  
    	          }  

    	          catch (Exception e) {  
    	             e.printStackTrace();  
    	          }  
    	          finally {  
    	             if (rs != null) try { rs.close(); } catch(Exception e) {}  
    	             if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
    	             if (con != null) try { con.close(); } catch(Exception e) {}  
    	          }  
    	          return Loca;
      }
      public static ArrayList<String>Location_2(String Loc){
    	  String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=지역관광지;user=pcy;password=1111";  
	       ArrayList<String> Loca = new ArrayList<String>();

    	          Connection con = null;  
    	          Statement stmt = null;  
    	          ResultSet rs = null;  

    	          try {  
    	             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
    	             con = DriverManager.getConnection(connectionUrl);  

    	             String SQL = "SELECT distinct 관광지역 FROM [전국$] where 지역명 = '" + Loc + "'";  
    	             stmt = con.createStatement();  
    	             rs = stmt.executeQuery(SQL);

    	             while (rs.next()) {   
    	                Loca.add(rs.getString("관광지역"));
    	             }  
    	          }  

    	          catch (Exception e) {  
    	             e.printStackTrace();  
    	          }  
    	          finally {  
    	             if (rs != null) try { rs.close(); } catch(Exception e) {}  
    	             if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
    	             if (con != null) try { con.close(); } catch(Exception e) {}  
    	          }  
    	          return Loca;
      }
      
      public static ArrayList<String>Location(){
		   String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=지역관광지;user=pcy;password=1111";  
	       ArrayList<String> Loc = new ArrayList<String>();
	       Connection con = null;  
	       Statement stmt = null;  
	       ResultSet rs = null;  
	
	       try {  
	          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	          con = DriverManager.getConnection(connectionUrl);  
	
	          String SQL = "SELECT distinct 지역명 FROM [전국$]";  
	          stmt = con.createStatement();  
	          rs = stmt.executeQuery(SQL);  
	
	          while (rs.next()) {  
	             Loc.add(rs.getString("지역명"));
	          }  
	       }  
	
	       catch (Exception e) {  
	          e.printStackTrace();  
	       }  
	       finally {  
	          if (rs != null) try { rs.close(); } catch(Exception e) {}  
	          if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	          if (con != null) try { con.close(); } catch(Exception e) {}  
	       }
	       return Loc;
	   }
   
}