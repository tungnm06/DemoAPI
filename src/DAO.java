/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DongHo
 */
public class DAO {

    private static final String CONNECTION_URL_PREFIX = "jdbc:mysql://";
    private static final String HOST = "localhost:3306/";
    private static final String DATABASE = "techcombank?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static String getCONNECTION_URL_PREFIX() {
        return CONNECTION_URL_PREFIX;

    }

    public static String getHOST() {
        return HOST;
    }

    public static String getDATABASE() {
        return DATABASE;
    }

    public static String getUSERNAME() {
        return USERNAME;

    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static Connection getConnection() throws SQLException {

        String connectionString = DAO.getCONNECTION_URL_PREFIX() + DAO.getHOST() + DAO.getDATABASE();
        return DriverManager.getConnection(connectionString, DAO.getUSERNAME(), DAO.getPASSWORD());
    }
}
