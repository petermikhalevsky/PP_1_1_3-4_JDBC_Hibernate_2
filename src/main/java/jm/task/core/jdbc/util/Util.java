/* package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/ktdbusers";
    private static final String USERNAME = "rootroot";
    private static final String PASSWORD = "rootroot";
    Driver driver;

    public static Connection getConnection() {
        try {
            java.sql.Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            System.out.println("Не удалось загрузить драйвер");
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error!");
        }
        return connection;
    }
}

 */
package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/ktdbusers?useSSL=false";
    private static final String LOGIN = "rootroot";
    private static final String PASSWORD = "rootroot";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName(DRIVER);
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection error!");
        }
        return connection;
    }
}