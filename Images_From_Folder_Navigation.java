import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import java.io.*;
import javax.swing.*;
import java.util.Date;
import java.awt.Color;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.sql.*;
//import javax.swing.*;

/**
 *
 * @author rNd Lab's MMXVI 
 */
public class Images_From_Folder_Navigation extends javax.swing.JFrame {

    /**
     * Creates new form Images_From_Folder_Navigation
     */
    public Images_From_Folder_Navigation() {
        initComponents();
       // display first image
       // showImage(pos);
    }
    

    int pos = 0;
    
    public String[] getImages()
    {
        //File file = new File( getClass().getResource("/Katalog/slike").getFile()   );
        File file = new File("C:\\Users\\User\\Desktop\\milan\\Katalog-master\\slike");
        String[] imagesList = file.list();
        return imagesList;
    }

// display the image by index
    public void showImage(int index)
    {
        String[] imagesList = getImages();
        String imageName = imagesList[index];
        //ImageIcon icon = new ImageIcon(getClass().getResource("/slike/"+imageName));
        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\milan\\Katalog-master\\slike\\" + imageName);
        Image image = icon.getImage().getScaledInstance(jLabel_Image.getWidth(), jLabel_Image.getHeight(), Image.SCALE_SMOOTH);
        jLabel_Image.setIcon(new ImageIcon(image));
       /**
        * Ovdje pocinje citanje FILE-a i setovanje LABEL-a
        */

        System.out.println(imageName);

        String fajl  = "C:\\Users\\User\\Desktop\\milan\\Katalog-master\\barcode\\" + imageName.substring(0, imageName.length() - 4 ) + ".txt";
        String db_sifra = imageName.substring(7, (imageName.length() - 4 ));
        
        
        System.out.println(db_sifra);

        // This will reference one line at a time
        String line = null;
       /**

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fajl);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

        
            line = bufferedReader.readLine();
            System.out.println(line);
            String parts[] = line.split(" ");
            System.out.println(parts[0]);
            System.out.println(parts[1]);
            System.out.println(parts[2]);
            System.out.println(parts[3]);
            jLabel_Sifra_sifra.setText(parts[0]);
            jLabel_Cijena_cijena.setText(parts[1]);
            jLabel_Naziv_naziv.setText(parts[2]);
            
            // Ovdje pocinje podesavanje boje zaliha
            // U odnosu na vrijednost (zelena - crvena)
            
            
            jLabel_trenutna_zaliha.setOpaque(true);
            
            int zaliha = Integer.parseInt(parts[3]);
            
            if (zaliha > 100) {
                        jLabel_trenutna_zaliha.setText(parts[3]);
                        jLabel_trenutna_zaliha.setForeground(java.awt.Color.red);
                        jLabel_trenutna_zaliha.setBackground(new java.awt.Color(70,230,76));
                        
                 }
            else {
                
                jLabel_trenutna_zaliha.setText(parts[3]);
                jLabel_trenutna_zaliha.setForeground(java.awt.Color.green);
                jLabel_trenutna_zaliha.setBackground(new java.awt.Color(255,0,0));
            }
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Ne mogu da otvorim fajl '" + 
                imageName.substring(0, imageName.length() - 4 ) + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Greska prilikom citanja '" 
                + imageName.substring(0, imageName.length() - 4 ) + "'");                  
            
        }
    **/
        
    Connection c = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:KATALOG.db");
      c.setAutoCommit(false);
      
      
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      
      if (db_sifra.charAt(0)  == '0') {
        System.out.println("Prvi char je s");
        System.out.println(db_sifra.substring(0, db_sifra.length()));
         ResultSet rs = stmt.executeQuery( "SELECT * FROM KATALOG where sifra ='s" + db_sifra + "';" );
         
        
        
        String sifra = rs.getString("SIFRA");
        String naziv = rs.getString("NAZIV");
        float cijena = rs.getFloat("CIJENA");
        int zaliha = rs.getInt("ZALIHA");
          
        System.out.println(sifra + " " + naziv + " "+ cijena + " "+ zaliha);
      
        
      rs.close();
      stmt.close();
      c.close();
      
            jLabel_Sifra_sifra.setText(sifra);
            jLabel_Cijena_cijena.setText(String.valueOf(cijena));
            jLabel_Naziv_naziv.setText(naziv);
            
            jLabel_trenutna_zaliha.setOpaque(true);
            
      if (zaliha > 100) {
                        jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                        jLabel_trenutna_zaliha.setForeground(java.awt.Color.red);
                        jLabel_trenutna_zaliha.setBackground(new java.awt.Color(70,230,76));
                        
                 }
            else {
                
                jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                jLabel_trenutna_zaliha.setForeground(java.awt.Color.green);
                jLabel_trenutna_zaliha.setBackground(new java.awt.Color(255,0,0));
            }            
    }
        
      ResultSet rs = stmt.executeQuery( "SELECT * FROM KATALOG where sifra = '" + db_sifra + "';" );
      
      String sifra = rs.getString("SIFRA");
      String naziv = rs.getString("NAZIV");
      float cijena = rs.getFloat("CIJENA");
      int zaliha = rs.getInt("ZALIHA");
      
      System.out.println(sifra + " " + naziv + " "+ cijena + " "+ zaliha);
      
      rs.close();
      stmt.close();
      c.close();
      
            jLabel_Sifra_sifra.setText(sifra);
            jLabel_Cijena_cijena.setText(String.valueOf(cijena));
            jLabel_Naziv_naziv.setText(naziv);
            
            jLabel_trenutna_zaliha.setOpaque(true);
            
      if (zaliha > 100) {
                        jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                        jLabel_trenutna_zaliha.setForeground(java.awt.Color.red);
                        jLabel_trenutna_zaliha.setBackground(new java.awt.Color(70,230,76));
                        
                       }
      else       {
                
                jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                jLabel_trenutna_zaliha.setForeground(java.awt.Color.green);
                jLabel_trenutna_zaliha.setBackground(new java.awt.Color(255,0,0));
                       }      
    
    } catch ( Exception e ) 
    {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.err.println(db_sifra);
      System.exit(0);
    }
        
    }
    
    

    @SuppressWarnings("unchecked")
                     
    private void initComponents() {

        jLabel_Image = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton(new javax.swing.ImageIcon("/pocetak.ico"));
        jButton_Next = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        
        
        jLabel_Sifra = new javax.swing.JLabel("SIFRA: "); // Tekst sifre
        jLabel_Sifra.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 14));
        
        jLabel_Cijena = new javax.swing.JLabel("CIJENA: ");
        jLabel_Cijena.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 14));
        
        jLabel_Naziv = new javax.swing.JLabel("NAZIV: ");
        jLabel_Naziv.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 14));
        
        jLabel_tr_zaliha = new javax.swing.JLabel("ZALIHA: ");
        jLabel_tr_zaliha.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 14));
        
        jLabel_trenutna_zaliha = new javax.swing.JLabel("null");
        jLabel_trenutna_zaliha.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 18));
        jLabel_trenutna_zaliha.setForeground(java.awt.Color.red);
        
        jLabel_Naziv_naziv = new javax.swing.JLabel("null");
        jLabel_Naziv_naziv.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 18));
        jLabel_Naziv_naziv.setForeground(java.awt.Color.red);
        
        jLabel_Sifra_sifra = new javax.swing.JLabel("null");
        jLabel_Sifra_sifra.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 18));
        jLabel_Sifra_sifra.setForeground(java.awt.Color.red);
        
        jLabel_Cijena_cijena = new javax.swing.JLabel("null");
        jLabel_Cijena_cijena.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 18));
        jLabel_Cijena_cijena.setForeground(java.awt.Color.red);
        
        jLabel_dodaj_komada  = new javax.swing.JLabel("komada");
        jLabel_dodaj_komada.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 12));
        
        
        trazi_sifru = new JTextField();
        trazi_sifru.setColumns(1);
        
        dodaj_u_fakturu = new JTextField();
        dodaj_u_fakturu.setColumns(1);
        
        
        
        jButton_Dodaj  = new javax.swing.JButton();
        jButton_Dodaj.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton_Dodaj.setText("Dodaj");
        jButton_Dodaj.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton_Dodaj.setIconTextGap(0);
        jButton_Dodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                jButton_Dodaj_u_fakturu(event);
            }
        });
        
        jButton_Trazi = new javax.swing.JButton();
        jButton_Trazi.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton_Trazi.setText("Trazi");
        jButton_Trazi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton_Trazi.setIconTextGap(0);
        jButton_Trazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                jButton_Trazi_akcijaTrazi(event);
            }
        });
        
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        //jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("ikone/pocetak.ico"))); // NOI18N/home/rnd/Documents/JAVA - Image/SimpleStereoC
        jButton1.setText("PRVA |<");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setIconTextGap(0);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_Next.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        //jButton_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/next.png"))); // NOI18N
        jButton_Next.setIcon(new javax.swing.ImageIcon("refresh.png"));
        jButton_Next.setText("Naredna ->");
        jButton_Next.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton_Next.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton_Next.setIconTextGap(0);
        jButton_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NextActionPerformed(evt);
            }
        });

        jButton_Previous.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        //jButton_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/previous.png"))); // NOI18N
        jButton_Previous.setText("Prethodna <-");
        jButton_Previous.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton_Previous.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton_Previous.setIconTextGap(0);
        jButton_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PreviousActionPerformed(evt);
            }
        });

        jButton_Last.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        //jButton_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/last.png"))); // NOI18N
        jButton_Last.setText("ZADNJA >|");
        jButton_Last.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton_Last.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton_Last.setIconTextGap(0);
        jButton_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            
            .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel_Sifra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            //.addGap(10,10,10)
            .addComponent(jLabel_Sifra_sifra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            
            .addComponent(jLabel_Cijena, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            //.addGap(10,10,10)
            .addComponent(jLabel_Cijena_cijena, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            
            .addComponent(jLabel_Naziv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10,10,10)   
            
            .addComponent(jLabel_Naziv_naziv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_tr_zaliha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_trenutna_zaliha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            
            //.addGap(10,10,10)
            //.addComponent(jLabel_tr_zaliha, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            //.addGap(10,10,10)
            )
            .addGroup(layout.createSequentialGroup()
            .addComponent(trazi_sifru, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
            
            //.addGap(10, 10, 10)
            .addComponent(jButton_Trazi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton_Dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dodaj_u_fakturu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_dodaj_komada, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            )
            
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton_Previous)
                .addGap(49, 49, 49)
                .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                
                //najvazniji dio za alignment LABEL-a
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_Sifra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Sifra_sifra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Cijena, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Cijena_cijena, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Naziv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Naziv_naziv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_tr_zaliha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_trenutna_zaliha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGap(18,18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    
                    
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Next, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jButton_Previous, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jButton_Last, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    )
                .addGap(7, 7, 7) 
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) 
                .addComponent(trazi_sifru, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_Trazi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_Dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dodaj_u_fakturu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_dodaj_komada, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                //.addGap(10 ,10,10) )
                
                )

        );

        pack();
    }                      

    private void jButton_Dodaj_u_fakturu(java.awt.event.ActionEvent event) {
        
        
        // Funkncija koja stvara pseudo fakturu
        // Treba dodati da pravi fakturu cijena x kolicina
        try {
            
            
            BufferedWriter output = null;
            Date date = new Date();
            String t_date = date.toString();
            File file = new File("Faktura.html");
            output = new BufferedWriter(new FileWriter(file, true));
            
            int counter = 1;
            
            String cijena, sifra, naziv, komada;
            
            cijena =  jLabel_Cijena_cijena.getText();
            sifra = jLabel_Sifra_sifra.getText();
            naziv = jLabel_Naziv_naziv.getText();
            komada = dodaj_u_fakturu.getText();
            
            float cijena_float = Float.valueOf(cijena);
            float komada_float = Float.valueOf(komada);
            
            float ukupno = cijena_float * komada_float;
            
            output.write("  <table width=\"100%\">\r\n " +
                              "<tr>\r\n" +
                                "<td width=\"5\" bgcolor=\"#D9E7FF\">Rb.</td>\r\n"      +
                                "<td width=\"15\" bgcolor=\"#D9E7FF\">Slika</td>\r\n"    +
                                "<td width=\"50\" bgcolor=\"#D9E7FF\">Naziv</td>\r\n"   +
                                "<td width=\"10\" bgcolor=\"#D9E7FF\">Kolicina</td>\r\n"+
                                "<td width=\"10\" bgcolor=\"#D9E7FF\">Cijena</td>\r\n"  + 
                                "<td width=\"10\" bgcolor=\"#D9E7FF\">UKUPNO</td>\r\n" +
                                
                                
                              "</tr>\r\n");
            
            output.write("   <tr>\r\n" +
                                "<td>" + counter + "</td>\r\n" +
                                "<td><img src=\"C:\\Users\\User\\Desktop\\milan\\Katalog-master\\slike2/" + sifra + ".jpg\" " + "width=\"150\" " +" \"height=\"150\" "+  "/></td> \r\n" +
                                "<td><strong> <span class=\"style1\">" + naziv + "</span>\'</strong></td>\r\n" +
                                "<td>" + komada + " </td>\r\n" +
                                "<td>" + cijena + "</td>\r\n" +
                                "<td>" + round3(ukupno,2) + "</td>\r\n" +
                              "</tr>\r\n");
            //output.write(sifra  + " " + naziv + " " + komada + " " +  cijena + "\r\n");
            counter += 1;
            System.out.println(cijena + " " + naziv + " " + sifra );
            System.out.println(t_date);
            output.close();
            
            
            
        } catch (Exception e) {
            
            // Ne cini nista zasad
            
        }
       
        
        
    }
    
    public static float round3(float d, int decimalPlace) {
    return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
} 
     
    private void jButton_Trazi_akcijaTrazi(java.awt.event.ActionEvent event) {
        try {
            
        String sifra_za_trazenje = trazi_sifru.getText();
        System.out.println(sifra_za_trazenje);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\milan\\Katalog-master\\slike2\\" + sifra_za_trazenje + ".jpg");
        Image image = icon.getImage().getScaledInstance(jLabel_Image.getWidth(), jLabel_Image.getHeight(), Image.SCALE_SMOOTH);
        jLabel_Image.setIcon(new ImageIcon(image));
        
        //String fajl  = "/home/rnd/Documents/slike/" + sifra_za_trazenje.substring(0, sifra_za_trazenje.length() - 4 ) + ".txt";
        /**
        String fajl  = "C:\\Users\\User\\Desktop\\milan\\Katalog-master\\barcode\\" + sifra_za_trazenje + ".txt";
        System.out.println(fajl);

        
        String line = null;
        

               
            FileReader fileReader = 
                new FileReader(fajl);

            
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            System.out.println(line);
            String parts[] = line.split(" ");
            System.out.println(parts[0]);
            System.out.println(parts[1]);
            System.out.println(parts[2]);
            jLabel_Sifra_sifra.setText(parts[0]);
            jLabel_Cijena_cijena.setText(parts[1]);
            jLabel_Naziv_naziv.setText(parts[2]);
            
            bufferedReader.close();         
        
        **/
    Connection c = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:KATALOG.db");
      c.setAutoCommit(false);
      
      
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      
      if (sifra_za_trazenje.charAt(0) == '0') {
        System.out.println("Prvi char je 0");
        
         ResultSet rs = stmt.executeQuery( "SELECT * FROM KATALOG where sifra = '" + sifra_za_trazenje + "';" );
         
        System.out.println(sifra_za_trazenje.substring(1, sifra_za_trazenje.length()));
        
        String sifra = rs.getString("SIFRA");
        String naziv = rs.getString("NAZIV");
        float cijena = rs.getFloat("CIJENA");
        int zaliha = rs.getInt("ZALIHA");
          
        System.out.println(sifra + " " + naziv + " "+ cijena + " "+ zaliha);
      
        
      rs.close();
      stmt.close();
      c.close();
      
            jLabel_Sifra_sifra.setText(sifra);
            jLabel_Cijena_cijena.setText(String.valueOf(cijena));
            jLabel_Naziv_naziv.setText(naziv);
            
            jLabel_trenutna_zaliha.setOpaque(true);
            
      if (zaliha > 100) {
                        jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                        jLabel_trenutna_zaliha.setForeground(java.awt.Color.red);
                        jLabel_trenutna_zaliha.setBackground(new java.awt.Color(70,230,76));
                        
                 }
            else {
                
                jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                jLabel_trenutna_zaliha.setForeground(java.awt.Color.green);
                jLabel_trenutna_zaliha.setBackground(new java.awt.Color(255,0,0));
            }            
    }
        
      ResultSet rs = stmt.executeQuery( "SELECT * FROM KATALOG where sifra = '" + sifra_za_trazenje + "';" );
      
      String sifra = rs.getString("SIFRA");
      String naziv = rs.getString("NAZIV");
      float cijena = rs.getFloat("CIJENA");
      int zaliha = rs.getInt("ZALIHA");
      
      System.out.println(sifra + " " + naziv + " "+ cijena + " "+ zaliha);
      
      rs.close();
      stmt.close();
      c.close();
      
            jLabel_Sifra_sifra.setText(sifra);
            jLabel_Cijena_cijena.setText(String.valueOf(cijena));
            jLabel_Naziv_naziv.setText(naziv);
            
            jLabel_trenutna_zaliha.setOpaque(true);
            
      if (zaliha > 100) {
                        jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                        jLabel_trenutna_zaliha.setForeground(java.awt.Color.red);
                        jLabel_trenutna_zaliha.setBackground(new java.awt.Color(70,230,76));
                        
                 }
            else {
                
                jLabel_trenutna_zaliha.setText(String.valueOf(zaliha));
                jLabel_trenutna_zaliha.setForeground(java.awt.Color.green);
                jLabel_trenutna_zaliha.setBackground(new java.awt.Color(255,0,0));
            }      
    
    } catch ( Exception e ) 
    {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.err.println(sifra_za_trazenje);
      System.exit(0);
    }
        
        
    } catch (Exception b)  { 
        System.out.println("Nesto nije ok " + b);
    }
        
        
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        pos = 0;
        showImage(pos);
        
    }                                        

    
    private void jButton_NextActionPerformed(java.awt.event.ActionEvent evt) {                                             
       pos = pos + 1;
       if(pos >= getImages().length)
       {
           pos  = getImages().length - 1;
       }
       showImage(pos);
    }                                            

    
    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {                                                 
       pos = pos - 1;
       if(pos < 0)
       {
           pos = 0;
       }
       showImage(pos);
    }                                                

     // Last
    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {                                             
       pos = getImages().length - 1;
       showImage(pos);
    }                                            

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Images_From_Folder_Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Images_From_Folder_Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Images_From_Folder_Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Images_From_Folder_Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Images_From_Folder_Navigation().setVisible(true);
            }
        });
    }

    // Varijable   ne znam ni sam zasto su na kraju...      
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JLabel jLabel_Image;
    
    private javax.swing.JLabel jLabel_Sifra;
    private javax.swing.JLabel jLabel_Cijena;
    private javax.swing.JLabel jLabel_Naziv_naziv;
    
    private javax.swing.JLabel jLabel_Sifra_sifra;
    private javax.swing.JLabel jLabel_Cijena_cijena;
    private javax.swing.JLabel jLabel_Naziv;
    
    private javax.swing.JTextField trazi_sifru;
    private javax.swing.JButton jButton_Trazi;
    
    private javax.swing.JButton jButton_Dodaj;
    private javax.swing.JTextField dodaj_u_fakturu;
    private javax.swing.JLabel  jLabel_dodaj_komada;
    
    private javax.swing.JLabel jLabel_tr_zaliha;
    private javax.swing.JLabel jLabel_trenutna_zaliha;
    
   
}
