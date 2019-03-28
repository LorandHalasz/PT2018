package dataAccess;

import java.sql.*;
import java.util.Properties;

/**
 * Clasa care creeaza legatura cu baza de date
 */
public class BazaDeDate {
    public Connection connect;
    public Statement st;
    public ResultSet rs;

    public BazaDeDate ()  {
        try {
            Properties properties = new Properties();
            properties.setProperty("user","root");
            properties.setProperty("password","pass");
            properties.setProperty("useSSL","false");
            properties.setProperty("autoReconnect","true");
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


