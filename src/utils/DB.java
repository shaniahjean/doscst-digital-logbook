/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    public static void main(String[] args) throws SQLException {
        getConnection();
    }

    private static final String DB_IP = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "doscst_visitor_log_app";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static Connection con;

    public static Connection getConnection() throws SQLException {

        if (con != null && !con.isClosed()) {
            return con;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASSWORD);
                System.out.println("Connected");
                return con;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Can't Connect To Server");
        return null;
    }

    public static ResultSet query(String query, boolean isUpdate) throws SQLException {
        Statement stmnt = getConnection().createStatement();
        System.out.println("Executing query: " + query);
        if (isUpdate) {
            stmnt.executeUpdate(query);
        } else {
            return stmnt.executeQuery(query);
        }

        stmnt.close();
        close();

        return null;
    }

    public static ResultSet query(String query) throws SQLException {
        return query(query, false);
    }

    public static String queryJoinParams(String porfName, Object[] params) {
        String query = porfName;

        query += "(" + params[0];
        for (int i = 1; i < params.length; i++) {
            query += "," + params[i];
        }

        return query + ")";

    }

    public static String getOneStringFromResultSet(ResultSet rs) throws SQLException {
        rs.next();
        return rs.getString(1);
    }

    public static int getOneIntFromResultSet(ResultSet rs) throws SQLException {
        rs.next();
        return rs.getInt(1);
    }

    public static void close() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

}
