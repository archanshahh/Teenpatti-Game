/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenpatti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Player {
    
    static int bal;
    static int pot;
    static String pass;
    private int card1[]=new int[2];
    private int card2[]=new int[2];
    private int card3[]=new int[2];   
    static int amount=10;
    private String name;

    public Player(int bal,String name) {
        this.bal = bal;
        this.name = name;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public Player(int bal) {
        this.bal = bal;
    }
    public String getname()
    {
        return name;
    }
    public void add()
    {
        if(bal<amount)
        {
           JOptionPane.showMessageDialog(null,"Not Sufficient Amount","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
             bal=bal-amount;
             pot=pot+amount;
        }
    }
    public void setCard(int r1, int r2, int r3)
    {
        if(r1%13==0 )
        {
           card1[1]=13;
        }
        else
        {
            card1[1]=r1%13;
        }
         if(r2%13==0)
        {
            card2[1]=13;
        }
        else
        {
            card2[1]=r2%13;
        }
          if(r3%13==0)
        {
            card3[1]=13;
        }
        else
        {
            card3[1]=r3%13;
        }
        if(r1/13 == 0)
        {
            card1[0]=1;
        }
        else
        {
            card1[0]=r1/13;
        }
        if(r2/13 == 0)
        {
            card2[0]=1;
        }
        else
        {
            card2[0]=r2/13;
        }
        if(r3/13 == 0)
        {
            card3[0]=1;
        }
        else
        {
            card3[0]=r3/13;
        }   
    }
    public int[] getcard1()
    {
        return card1;
    }
    public int[] getcard2()
    {
        return card2;
    }
    public int[] getcard3()
    {
        return card3;
    }
    public void show(Player oponent)
    {
        int p1=0,p2=0;
        
        p1=samecards();
        p2=oponent.samecards();
          if(p2==0)
        {
            p2=oponent.samecolor();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
           if(p1==0)
        {
            p1=sequence();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
        if(p1==0)
        {
            p1=samecolor();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
        if(p2==0)
        {
            p2=oponent.sequence();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
           if(p1==0)
        {
            p1=twosame();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
        if(p2==0)
        {
            p2=oponent.twosame();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
           if(p1==0)
        {
            p1=highest();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
        if(p2==0)
        {
            p2=oponent.highest();
             System.out.println("p1="+p1+"p2="+p2+"");
        }
        System.out.println("p1="+p1+"p2="+p2+"");
        if(p1>p2)
        {
             bal=bal+pot;
             pot=0;
                Connection con = null;
                ResultSet rs = null;
                Statement stat = null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
               stat = con.createStatement();
               stat.executeUpdate("UPDATE turn SET win='"+this.name+"'");
               stat.executeUpdate("UPDATE  player SET  balance = "+Player.bal+" WHERE  pass='"+Player.pass+"'");
               
               stat.close();
               con.close();

           }
           catch(Exception e){
               System.out.println("exception catch...");
               e.printStackTrace();
           }
        }
        else
        {
             oponent.bal+=pot;
             pot=0;
              Connection con = null;
                ResultSet rs = null;
           Statement stat = null;
           try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://192.168.36.131:3306/test", "jay", "");
               stat = con.createStatement();
               stat.executeUpdate("UPDATE turn SET win='"+oponent.name+"'");
               stat.executeUpdate("UPDATE  player SET  balance = "+oponent.bal+" WHERE  name like '"+oponent.name+"'");
               stat.close();
               con.close();

           }
           catch(Exception e){
               System.out.println("exception catch...");
               e.printStackTrace();
           }
       }
    }
    private int samecards()
    { 
        int value=0;
        if(card1[1]==card2[1] && card2[1]==card3[1])
        {
            value=100+card1[1]+card2[1]+card3[1];
            return value;
        }
        return 0;
    }
    private int samecolor()
    {
        if(card1[0]==card2[0] && card2[0]==card3[0])
        {
            return 30;
        }
        return 0;
    }
    private int sequence()
    {
        int value=0;
        int a[] = new int[] {card1[1],card2[1],card3[1]};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(a[j]>a[j+1])
                {
                    int temp;
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        if(a[0]+1==a[1] && a[1]+1==a[2])
        {
            value=samecolor();
            return value+a[0]+a[1]+a[2]+50;
        }
        else
        {
            return 0; 
        }
    }
    private int twosame()
    {  
        int sum;
        if(card1[1]==card2[1])
        {
            sum=card1[1]+card2[1];
            return sum;
        }
        else if(card2[1]==card3[1])
        {
            sum=card2[1]+card3[1];
            return sum;
        }
        else if( card1[1]==card3[1])
        {
            sum=card1[1]+card3[1];
            return sum;
        }
        return 0;
    }
    private int highest()
    {
        if(card1[1]>card2[1] && card1[1]>card3[1])
        {
            return card1[1];
        }
        else if(card2[1]>card1[1] && card2[1]>card3[1])
        {
            return card2[1];
        }
        else if(card3[1]>card2[1] && card1[1]<card3[1])
        {
            return card3[1];
        }
        return 0;
    }

    public static void main(String[] args) {
 
    }
    
}
