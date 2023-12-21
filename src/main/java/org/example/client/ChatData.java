package org.example.client;

import java.sql.*;

public class ChatData {
    Connection con;
    Statement stmt;

    public ChatData(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test","root","Giannis34!");
            Statement stmt=con.createStatement();
            System.out.println("Connect successfully");
            ResultSet rs = stmt.executeQuery("select * from Persons");
            while(rs.next()){
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void getUserData(){
        try{
            ResultSet rs = stmt.executeQuery("select * from Persons");
            while(rs.next()){
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void setUserData(String email, String userName, String password, String message){

    }
}
