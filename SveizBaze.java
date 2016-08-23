import java.sql.*;

public class SveizBaze
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
      ResultSet rs = stmt.executeQuery( "SELECT * FROM KATALOG;" );
      while ( rs.next() ) {
         //int id = rs.getInt("id");
         String  sifra = rs.getString("SIFRA");
         int zaliha  = rs.getInt("ZALIHA");
         String  naziv = rs.getString("NAZIV");
         float cijena = rs.getFloat("CIJENA");
         System.out.println( "SIFRA =  " + sifra );
         System.out.println( "NAZIV = " + naziv );
         System.out.println( "CIJENA = " + cijena );
         System.out.println( "ZALIHA = " + zaliha );
        
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}