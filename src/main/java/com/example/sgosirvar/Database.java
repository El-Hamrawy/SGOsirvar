package com.example.sgosirvar;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/fieldtrain";
    private static final String UserName = "root";
    private static final String Password = "admin" ;

    public static Connection connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, UserName, Password);
            return con ;
        } catch (Exception e) {
            return null ;
        }
    }
    public static void InsertUserData ( Connection connection ,String userName , String Email , int PhoneNumber , String password ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into user (username , Email ,Phonenumber , password,status) values (? , ?, ? ,?,'O') ");

        System.out.println("inserting into database");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, Email);
        preparedStatement.setInt(3, PhoneNumber);
        preparedStatement.setString(4, password);

        preparedStatement.executeUpdate();
    }
    public static boolean loginConfirmation (Connection connection ,String userName , String password) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from user where username = ? and password = ? ");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()){
            return true ;
        }
        else {
            return false ;
        }
    }
    public static void UpdateUserStatus (Connection connection ,String userName , String status) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update user set status = ? where username = ? ");

        preparedStatement.setString(1, status);
        preparedStatement.setString(2, userName);
        preparedStatement.executeUpdate();
    }
    public static ResultSet getUsers (String Status , Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select username from user Where status = ? "
        );
        preparedStatement.setString(1, Status);
        ResultSet results = preparedStatement.executeQuery();
        return results ;
    }
}
