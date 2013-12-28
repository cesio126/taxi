package com.wsts.taxi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.widget.Toast;

public class Conexao {  
    private static Connection con;  
      
    public static Connection getConnection() {    
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco,user,pass");  
        }catch(ClassNotFoundException ex){  
            Toast.makeText(null, ex.getLocalizedMessage(), Toast.LENGTH_SHORT);  
        }catch(SQLException ex){  
            Toast.makeText(null, ex.getLocalizedMessage(), Toast.LENGTH_SHORT);  
        }  
        return con;   
    }  
}
