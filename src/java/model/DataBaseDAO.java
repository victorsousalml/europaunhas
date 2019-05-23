/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class DataBaseDAO {
    public final String user = "root";
    public final String pass = "";
    public final String url = "jdbc:mysql://localhost:3306/europaunhas";
    public Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    
    
    public void conectar()throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pass);
        System.out.println("Conexão aberta");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            System.out.println("Erro ao conectar ao Banco");
            
        }
    }
    
    public void desconectar()throws Exception{
        if(conn!= null){
            System.out.println("Conexão fechada");
            conn.close();
        }
    }
    
    
}
