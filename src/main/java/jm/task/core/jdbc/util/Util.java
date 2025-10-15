package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://@localhost:3306/jdbc_hibernate";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2sWHfBZvLF.";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection conn;

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Не удалось загрузить класс драйвера: ", e);
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
