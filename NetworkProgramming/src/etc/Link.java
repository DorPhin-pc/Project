package etc;
import java.sql.*;


public class Link {
      
      public static void main(String[] args)
      {
       
         // Create a variable for the connection string.  
          String connectionUrl = "jdbc:sqlserver://10.50.229.59:1433;" +  
             "databaseName=Project3;user=sql;password=1234";  

          // Declare the JDBC objects.  
          Connection con = null;  
          Statement stmt = null;  
          ResultSet rs = null;  

          try {  
             // Establish the connection.  
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
             con = DriverManager.getConnection(connectionUrl);  

             // Create and execute an SQL statement that returns some data.  
             String SQL = "SELECT 설치장소,전화번호 FROM ['강원도 자동심장충격기목록']";  
             stmt = con.createStatement();  
             rs = stmt.executeQuery(SQL);  

             // Iterate through the data in the result set and display it.  
             while (rs.next()) {  
                System.out.println(rs.getString("설치장소") + " " + rs.getString("전화번호"));  
             }  
          }  

          // Handle any errors that may have occurred.  
          catch (Exception e) {  
             e.printStackTrace();  
          }  
          finally {  
             if (rs != null) try { rs.close(); } catch(Exception e) {}  
             if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
             if (con != null) try { con.close(); } catch(Exception e) {}  
          }  
         /*Connection conn = null;
         Statement stmt = null;
         
         try
         {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String connectionString = "jdbc:sqlserver://10.50.229.59:1433;" + "DatabaseName=Project3;user=sql;password=1234";
            
            conn = DriverManager.getConnection(connectionString);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select 설치장소,전화번호 from ['강원도 자동심장충격기목록']");
            System.out.println("설치장소   전화번호");
            System.out.println("-------------");
            
            while(rs.next());
            {
              System.out.printf("%s %s %n",rs.getString("설치장소") , rs.getString("전화번호"));
            }
         }
         catch(ClassNotFoundException cnfe)
         {
            System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
         }
         catch(SQLException se)
         {
            System.out.println(se.getMessage());
         }
         finally
         {
            try
            {
               stmt.close();
               conn.close();
            }
            catch(Exception e)
            {
               
            }
         }*/
      }
   
}