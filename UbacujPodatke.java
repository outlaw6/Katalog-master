import java.sql.*;
import java.util.*;
public class UbacujPodatke extends Bazasql
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
      for (int x=0; x<50; x++) {
      
          
      int zaliha;
      float cijena;
      String s, n;
 
      Scanner in = new Scanner(System.in);
 
      System.out.println("Unesi sifru: ");
      s = in.nextLine();
       
      System.out.println("Unesi naziv");
      n = in.nextLine();
      
      System.out.println("Unesite cijenu: ");
      cijena = in.nextFloat();
 
      System.out.println("Unesite zalihu: ");
      zaliha = in.nextInt();
               
          
      String sql = "INSERT INTO KATALOG (SIFRA,NAZIV,CIJENA,ZALIHA) " +
                   "VALUES ( '" + s + "', '" + n + "', "+ cijena + ", " + zaliha +  "  );"; 
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      
      System.out.println("Record created!");
    }
    c.close();
    } catch ( Exception e ) 
    {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
}