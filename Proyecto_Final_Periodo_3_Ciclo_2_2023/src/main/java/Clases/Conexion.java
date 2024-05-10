package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


public class Conexion 
{
   private Connection conn;
   private Statement stm;
    
   public Conexion(String pIP, String pListener, String pUsuario, String pClave)  
   {
      try
      {
        Class.forName("oracle.jdbc.OracleDriver") ;
        this.conn = DriverManager.getConnection("jdbc:oracle:thin:@" +
                pIP +":1521:" + pListener, pUsuario, pClave);

        this.stm = this.conn.createStatement();
        
                if (this.conn != null) 
                {
                    this.stm = this.conn.createStatement();
                } 
                else 
                {
                    System.err.println("La conexión a la base de datos no se pudo establecer.");
                }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }
   
    public Connection getConnection()
    {
       return this.conn;
    }
}

