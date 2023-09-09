/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ushan_Binusha
 */
public class DBConnect {
    
    
    public static Connection connect() throws SQLException{
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jayantha?useUnicode=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("Done");
        } catch (ClassNotFoundException e) {
            System.out.println("Fail");
            JOptionPane.showMessageDialog(null, e);
        }
    
      
        return conn;
        
        /*
        හෙල්ලෝඕඕඕඔක්ඩ්ජ්ක්ව්ඩ්ජ්ව්බ්ඩ්ජ්ව්බ්ජ්ජ්ව්ජ්ඩ්බ්ව්ජ්
        ක්ද්ක්ද්ච්ච්ච්ච්ච්ක්ද්ච්ද්ච්ච්ද්ච්ඩ්ක්
        
        */
}
 public static void main(String []args) throws SQLException{
        
         
    connect();
    
}
        

    }
   
