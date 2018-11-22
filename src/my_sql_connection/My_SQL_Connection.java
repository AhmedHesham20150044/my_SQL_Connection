/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_sql_connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Ahmed_Hesham
 */
public class My_SQL_Connection {
    
    public static void main(String[] args) {

        
       My_SQL_Connection.getdata(20150044);
       int x = My_SQL_Connection.updateID(20150044, (float)2.69);
      System.out.println(x);
       
        
        }
        
    public static Connection getConnection(){
        Connection conn ;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setDatabaseName("faculty");
            dataSource.setServerName("localhost");
            conn = dataSource.getConnection();
        } catch (SQLException ex) {
            return null;
        }
        return conn;
    }

    public static void getdata(int ID){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM faculty.` student` where faculty.` student`.id =" +  ID);
            System.out.println("query is done");
            while (rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getFloat(4));
            }   } catch (SQLException ex) {
            Logger.getLogger(My_SQL_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int addStudent(int ID , String name, String address, float gpa){
        int rs ;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeUpdate("INSERT INTO `faculty`.` student`"
                    + "(`id`, `name`, `address`, `gpa`)"
                    + "VALUES"
                    + "(" + ID + ",'" + name + "','" + address + "',"+ gpa + ");");
        } catch (SQLException ex) {
            Logger.getLogger(My_SQL_Connection.class.getName()).log(Level.SEVERE, null, ex);
            return 0 ;
        }
        return rs;
    }
    
    public static int updateID(int ID , float gpa){
        int rs ;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeUpdate("UPDATE `faculty`.` student`"
                    + "SET"
                    + "`gpa` = " + gpa
                    + "where faculty.` student`.id =" + ID + ";");
        } catch (SQLException ex) {
            Logger.getLogger(My_SQL_Connection.class.getName()).log(Level.SEVERE, null, ex);
            return 0 ;
        }
        return rs;
    }
    
}
















































