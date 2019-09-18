/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenpatti;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Game extends javax.swing.JFrame {
AnimationClass ac = new AnimationClass();
public static String theme = "table_back.png";
    /**
     * Creates new form NewJFrame
     */
    
     
    public Game() throws IOException {
        initComponents();
        ImageIcon img6=new ImageIcon( new ImageIcon("C:\\Users\\jay\\Desktop\\TeenPatti\\user.png").getImage().getScaledInstance(90, 100, Image.SCALE_DEFAULT));
        lbluser.setIcon(img6);
        lbluser2.setIcon(img6);
         ImageIcon img7=new ImageIcon( new ImageIcon("C:\\Users\\jay\\Desktop\\TeenPatti\\coin.png").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT));
       jLabel11.setIcon(img7);
         Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/2;
        y=(screenSize.height-frameSize.height)/2;
        setLocation(x, y);
        int r1,r2,r3;
        jPanel1.setBackground(new Color(0,0,0,100));
        r1=random();
        r2=random();
        r3=random();
        while(r1 == r2 )
        {
            r2 = random();
        }
        while(r3 == r2 || r3 == r1)
        {
            r3 =random();
        }
        a.setCard(r1, r2, r3);
        int card1[]=new int[2];
        int card2[]=new int[2];
        int card3[]=new int[2];
        card1=a.getcard1();
        card2=a.getcard2();
        card3=a.getcard3();
        
         Connection con = null;
         ResultSet rs = null;
         Statement stat = null;
          
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
        stat = con.createStatement();
        String name;
        rs=stat.executeQuery("select username,name,balance from player where pass='"+Player.pass+"'");
        rs.next();
        name=rs.getString("username");
        String uname=rs.getString("name");
        a.setname(uname);
        Player.bal=rs.getInt("balance");
        System.out.println(""+Player.pass);
        stat.executeUpdate("INSERT into login(username,pass,card1,card2,card3) VALUES('"+name+"','"+Player.pass+"',"+r1 +","+r2+","+r3+")");
        stat.executeUpdate("update turn set player = '"+a.getname()+"'");
        stat.close();
        con.close();      
    }
    catch(Exception e){
        System.out.println("exception catch...");
        e.printStackTrace();
    }
        ImageIcon img1 =new ImageIcon( new ImageIcon("table_back.png").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(img1);   
          String img = String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",card1[0],card1[1]);
         
        ImageIcon img2 = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55,80,Image.SCALE_DEFAULT));
       jLabel5.setIcon(img2);
        String s2 = String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",card2[0],card2[1]);
         ImageIcon img3 = new ImageIcon(new ImageIcon(s2).getImage().getScaledInstance(55,80,Image.SCALE_DEFAULT));
       jLabel4.setIcon(img3);
         String s3 = String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",card3[0],card3[1]);
        ImageIcon img4 = new ImageIcon(new ImageIcon(s3).getImage().getScaledInstance(55,80,Image.SCALE_DEFAULT));
        jLabel3.setIcon(img4);
        ImageIcon imageicon1 = new ImageIcon(new ImageIcon("backd.png").getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
        jLabel7.setIcon(imageicon1);
        jLabel9.setIcon(imageicon1);
        jLabel8.setIcon(imageicon1);
        trd();
    }
      public static int random(){
      Random r = new Random();
      int ran = (r.nextInt(51)+1);
      return ran;
       
    }
     public ImageIcon img1(int a , int b ){
         
         String img3 = String.format("%d_%d.png",a,b);
         ImageIcon imgic = new ImageIcon(new ImageIcon(img3).getImage().getScaledInstance(50,65,Image.SCALE_DEFAULT));
         
         return imgic;
     }
     public void trd()
    {
         Thread k=new Thread() {
            @Override
            public void run() {
                try {
                    while(true)
                    {
                       Connection con = null;
                        ResultSet rs = null;
                        Statement stat = null;
                        try{
                       Class.forName("com.mysql.jdbc.Driver");
                       con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
                       stat = con.createStatement();
                       rs=stat.executeQuery("select player,win from turn");
                       rs.next();
                       String turn=rs.getString("player");
                       
                        lblturn.setText(turn+"'turn");
                                        
                       String win=rs.getString("win");
                       if(win.equalsIgnoreCase("null"))
                       {
                         
                       }
                      else
                        {

                 try{

                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
                stat = con.createStatement();
                String name;
                rs=stat.executeQuery("select username from login where pass not like '"+Player.pass+"'");
                rs.next();
                name=rs.getString("username");
                Player b=new Player(Player.bal,name);
                rs=stat.executeQuery("select card1,card2,card3 from login where pass not like '"+Player.pass+"'");
                rs.next();
                int c1=rs.getInt("card1"); 
                int c2=rs.getInt("card2"); 
                int c3=rs.getInt("card3"); 
               System.out.println("c1="+c1+"c2="+c2+"c3="+c1+"");
                System.out.println(""+Player.pass);
                b.setCard(c1,c2,c3);
                int[] b1 = new int[2];
                b1=b.getcard1();
                int[] b2 = new int[2];
                b2=b.getcard2();
                int[] b3 = new int[2];
                b3=b.getcard3();
                String s1= String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",b1[0],b1[1]);
                ImageIcon bi1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
                jLabel7.setIcon(bi1);
                      String s2= String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",b2[0],b2[1]);
                ImageIcon bi2 = new ImageIcon(new ImageIcon(s2).getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
                jLabel9.setIcon(bi2);
                      String s3= String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",b3[0],b3[1]);
                ImageIcon bi3 = new ImageIcon(new ImageIcon(s3).getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
                jLabel8.setIcon(bi3);
            }
            catch(Exception e)
             {
                     
             }
                           JOptionPane.showMessageDialog(jPanel1,win+ "WIns the Game");
                           stat.executeUpdate("UPDATE turn SET win='null'");
                        }
                      
                        if(!turn.equalsIgnoreCase(Player.pass))
                        {
                             // stat.executeUpdate("UPDATE player SET `turn'='"+Player.pass+"'");
                            btnadd.setEnabled(false);
                            btnchaal.setEnabled(false);
                            btnpack.setEnabled(false);
                            btnshow.setEnabled(false);
                        }
                        else
                        {
                             btnadd.setEnabled(true);
                            btnchaal.setEnabled(true);
                            btnpack.setEnabled(true);
                            btnshow.setEnabled(true);
                            
                        }
                       
                       rs=stat.executeQuery("select pot,amount from static_table");
                       rs.next();
                       int pot=rs.getInt("pot");
                       int amount=rs.getInt("amount");
                       lblpot.setText(""+pot);
                       lblamt.setText(""+amount);
                                             
                       
                       
                      stat.close();
                       con.close();           
                       }
                       catch(Exception e){
                       System.out.println("exception catch...");
                       e.printStackTrace();
                        }
                    }
                } 
                catch (Exception e) {

                }
            }
        };  k.start(); 
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbluser2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblpot = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblbal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        lblturn = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblsbal = new javax.swing.JLabel();
        btnchaal = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        lblamt = new javax.swing.JLabel();
        btnpack = new javax.swing.JButton();
        lbluser = new javax.swing.JLabel();
        btnshow = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(null);

        lbluser2.setText("jLabel6");
        lbluser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbluser2);
        lbluser2.setBounds(210, 30, 90, 100);

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));
        getContentPane().add(jLabel10);
        jLabel10.setBounds(270, 390, 60, 20);

        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("Rs.");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 400, 20, 16);

        lblpot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblpot.setForeground(new java.awt.Color(255, 204, 0));
        lblpot.setText("0");
        getContentPane().add(lblpot);
        lblpot.setBounds(360, 160, 70, 30);

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(320, 50, 40, 60);

        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(370, 50, 40, 60);

        lblbal.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblbal.setForeground(new java.awt.Color(255, 204, 0));
        lblbal.setText("Balance");
        getContentPane().add(lblbal);
        lblbal.setBounds(570, 420, 60, 30);

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(420, 50, 40, 60);
        getContentPane().add(jLabel11);
        jLabel11.setBounds(230, 140, 90, 80);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(240, 230, 55, 80);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(380, 240, 55, 80);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(470, 240, 55, 80);

        btnadd.setBackground(new java.awt.Color(255, 204, 0));
        btnadd.setText("+");
        btnadd.setBorder(null);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        getContentPane().add(btnadd);
        btnadd.setBounds(260, 351, 60, 30);

        lblturn.setFont(new java.awt.Font("Myanmar Text", 3, 18)); // NOI18N
        lblturn.setForeground(new java.awt.Color(204, 204, 0));
        lblturn.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)), "TURN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 204, 0))); // NOI18N
        getContentPane().add(lblturn);
        lblturn.setBounds(50, 220, 130, 90);

        jLabel12.setForeground(new java.awt.Color(255, 204, 0));
        jLabel12.setText("Rs.");
        jLabel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 1, true));
        getContentPane().add(jLabel12);
        jLabel12.setBounds(650, 420, 70, 20);

        lblsbal.setForeground(new java.awt.Color(255, 204, 0));
        getContentPane().add(lblsbal);
        lblsbal.setBounds(650, 430, 70, 20);

        btnchaal.setBackground(new java.awt.Color(0, 153, 51));
        btnchaal.setForeground(new java.awt.Color(255, 255, 255));
        btnchaal.setText("chaal");
        btnchaal.setBorder(null);
        btnchaal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchaalActionPerformed(evt);
            }
        });
        getContentPane().add(btnchaal);
        btnchaal.setBounds(260, 430, 60, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 0));
        jLabel6.setText("Rs.");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(320, 160, 40, 30);

        jButton5.setText("Theme");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(650, 30, 90, 25);

        lblamt.setForeground(new java.awt.Color(255, 204, 0));
        lblamt.setText("0");
        getContentPane().add(lblamt);
        lblamt.setBounds(270, 400, 60, 20);

        btnpack.setBackground(new java.awt.Color(204, 0, 0));
        btnpack.setForeground(new java.awt.Color(255, 255, 255));
        btnpack.setText("Pack");
        btnpack.setBorder(null);
        getContentPane().add(btnpack);
        btnpack.setBounds(460, 340, 60, 30);

        lbluser.setText("jLabel2");
        lbluser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbluser);
        lbluser.setBounds(350, 360, 90, 100);

        btnshow.setBackground(new java.awt.Color(255, 204, 0));
        btnshow.setText("Show");
        btnshow.setBorder(null);
        btnshow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnshowActionPerformed(evt);
            }
        });
        getContentPane().add(btnshow);
        btnshow.setBounds(450, 425, 60, 30);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(230, 330, 310, 150);

        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 1, true));
        jLabel1.setPreferredSize(getSize());
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1990, 690);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
       // jLabel1.resize(this.size());
        ImageIcon img1 =new ImageIcon( new ImageIcon(theme).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(img1);
        jLabel11.setLocation(((this.getWidth())/2-70), ((this.getHeight()/2)-50));
        jLabel8.setLocation(((this.getWidth())/2), (((this.getHeight()/2)-130)));
        jLabel9.setLocation(((this.getWidth()/2)-70), (((this.getHeight()/2)-130)));
        jLabel7.setLocation(((this.getWidth()/2)+70), (((this.getHeight()/2)-130)));
        lbluser.setLocation(((this.getWidth())/2-10), (((this.getHeight()/2)+140)));
        lblbal.setLocation(((this.getWidth())/2-45),(((this.getHeight())/2)+240));
        lblsbal.setLocation(((this.getWidth())/2+30),(((this.getHeight())/2)+245));
        jLabel12.setLocation(((this.getWidth())/2+10),(((this.getHeight())/2)+245));
        lblamt.setLocation(((this.getWidth())/2-80), (((this.getHeight()/2)+187)));
         jLabel10.setLocation(((this.getWidth())/2-98), (((this.getHeight()/2)+188)));
         jLabel2.setLocation(((this.getWidth())/2-98), (((this.getHeight()/2)+189)));
         btnchaal.setLocation(((this.getWidth())/2-100), (((this.getHeight()/2)+215)));
         btnadd.setLocation(((this.getWidth())/2-100), (((this.getHeight()/2)+150)));
        jLabel3.setLocation(((this.getWidth())/2), (((this.getHeight()/2)+20)));
        jLabel4.setLocation(((this.getWidth()/2)-70), (((this.getHeight()/2)+20)));
        jLabel5.setLocation(((this.getWidth()/2)+70), (((this.getHeight()/2)+20)));
        jPanel1.setLocation(((this.getWidth()/2)-120),((this.getHeight()/2))+120);
       lblpot.setLocation(((this.getWidth()/2+44)),((this.getHeight()/2))-20);
       jLabel6.setLocation(((this.getWidth()/2+10)),((this.getHeight()/2))-20);
       lblturn.setLocation(((this.getWidth()/2-300)),((this.getHeight()/2))-20);
        btnpack.setLocation(((this.getWidth())/2+110), (((this.getHeight()/2)+150)));
        btnshow.setLocation(((this.getWidth())/2+110), (((this.getHeight()/2)+215)));
        lbluser2.setLocation(((this.getWidth())/2-10), (((this.getHeight()/2)-250)));
        
    }//GEN-LAST:event_formComponentResized

    private void btnshowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnshowActionPerformed
         Connection con = null;
         ResultSet rs = null;
         Statement stat = null;
          
    try{
        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
        stat = con.createStatement();
        String name;
        rs=stat.executeQuery("select username from login where pass not like '"+Player.pass+"'");
        rs.next();
        name=rs.getString("username");
        Player b=new Player(Player.bal,name);
        rs=stat.executeQuery("select card1,card2,card3 from login where pass not like '"+Player.pass+"'");
        rs.next();
        int c1=rs.getInt("card1"); 
        int c2=rs.getInt("card2"); 
        int c3=rs.getInt("card3"); 
        //labe
        System.out.println("c1="+c1+"c2="+c2+"c3="+c1+"");
        System.out.println(""+Player.pass);
        b.setCard(c1,c2,c3);
        int[] b1 = new int[2];
        b1=b.getcard1();
        int[] b2 = new int[2];
        b2=b.getcard2();
        int[] b3 = new int[2];
        b3=b.getcard3();
        String s1= String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",b1[0],b1[1]);
        ImageIcon bi1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
        jLabel8.setIcon(bi1);
              String s2= String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",b2[0],b2[1]);
        ImageIcon bi2 = new ImageIcon(new ImageIcon(s2).getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
        jLabel7.setIcon(bi2);
              String s3= String.format("C:\\Users\\jay\\Desktop\\TeenPatti\\cards\\%d_%d.png",b3[0],b3[1]);
        ImageIcon bi3 = new ImageIcon(new ImageIcon(s3).getImage().getScaledInstance(40,60,Image.SCALE_DEFAULT));
        jLabel9.setIcon(bi3);
        a.show(b);
        stat.executeUpdate("DELETE FROM `test`.`login`");
        stat.executeUpdate("UPDATE `static_table` SET `pot`=0,`amount`=10");
        stat.close();
        con.close();      
    }
    catch(Exception e){
        System.out.println("exception catch...");
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnshowActionPerformed
    
    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
       
        Connection con = null;
       Statement stat = null;
         try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
         stat = con.createStatement();
        Player.amount=Integer.parseInt(lblamt.getText())+10;
        lblamt.setText(Player.amount+ "");
        stat.executeUpdate("UPDATE static_table SET amount="+Player.amount+"");
        stat.close();
        con.close();    
         }
         catch(Exception e){
        System.out.println("exception catch...");
        e.printStackTrace();
         }
       btnadd.setEnabled(false);
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnchaalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchaalActionPerformed
       // TODO add your handling code here
        btnadd.setEnabled(true);  
        lblsbal.setText(""+Player.bal);
         Connection con = null;
         ResultSet rs = null;
         Statement stat = null;
         try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
        stat = con.createStatement();
        rs=stat.executeQuery("select pot,amount from static_table");
        rs.next();
        a.pot=rs.getInt("pot");
        a.amount=rs.getInt("amount");
        a.add();
        stat.executeUpdate("UPDATE `static_table` SET `pot`="+a.pot+"");
        rs=stat.executeQuery("select username from login where username <> '"+a.getname()+"'");
        rs.next();
        String opo=rs.getString("username");
        stat.executeUpdate("UPDATE turn SET player='"+opo+"'");
        stat.close();
        con.close();           
        }
        catch(Exception e){
        System.out.println("exception catch...");
        e.printStackTrace();
         } 
    }//GEN-LAST:event_btnchaalActionPerformed
int value = 0;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        
         if(value == 0)
         {
             theme = "tb1.png";
             ImageIcon img =new ImageIcon( new ImageIcon(theme).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
             jLabel1.setIcon(img);
             value = 1;
         }
         else if(value == 1)
         {
            theme = "table.png";
        value= 2; 
         ImageIcon img1 =new ImageIcon( new ImageIcon(theme).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
         jLabel1.setIcon(img1);
         
         }
         else
         {
             theme = "table_back.png";
        value= 0; 
         ImageIcon img1 =new ImageIcon( new ImageIcon(theme).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
         jLabel1.setIcon(img1);
         }
         
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    Player a=new Player(100,"disha");
    public static void main(String args[]) throws Exception {
           
          System.out.println(InetAddress.getLocalHost());
      //  a.setCard(r1,r2,r3);
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Game().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnchaal;
    private javax.swing.JButton btnpack;
    private javax.swing.JButton btnshow;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblamt;
    private javax.swing.JLabel lblbal;
    private javax.swing.JLabel lblpot;
    private javax.swing.JLabel lblsbal;
    private javax.swing.JLabel lblturn;
    private javax.swing.JLabel lbluser;
    private javax.swing.JLabel lbluser2;
    // End of variables declaration//GEN-END:variables

    private void paintComponent(ImageIcon img4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PopupMenu jLabel3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
