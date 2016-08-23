import java.sql.*;

public class Bazasql extends izvlaciPodatke
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:KATALOG.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE KATALOG " +
                   
                   "(SIFRA          CHAR(20)    NOT NULL, " + 
                   " NAZIV          CHAR(50)    NOT NULL, " + 
                   " CIJENA         REAL        NOT NULL, " + 
                   " ZALIHA         INT         NOT NULL )"; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}