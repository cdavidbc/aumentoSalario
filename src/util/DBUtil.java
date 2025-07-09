package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static Properties props = new Properties();

    static {
        try {
            InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("No se encontró el archivo db.properties en resources.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("user"),
                props.getProperty("password")

        );
    }
}
