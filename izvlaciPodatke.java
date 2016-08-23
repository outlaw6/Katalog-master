import java.sql.*;

public class izvlaciPodatke
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:KATALOG.db");
      c.setAutoCommit(false);
      
      
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM KATALOG where sifra = 970805 ;" );
      
      String sifra = rs.getString("SIFRA");
      String naziv = rs.getString("NAZIV");
      float cijena = rs.getFloat("CIJENA");
      int zaliha = rs.getInt("ZALIHA");
      
      System.out.println(sifra + " " + naziv + " "+ cijena + " "+ zaliha);
      
      rs.close();
      stmt.close();
      c.close();
      
    
    } catch ( Exception e ) 
    {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}