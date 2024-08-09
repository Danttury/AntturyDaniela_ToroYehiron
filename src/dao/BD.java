package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    private static final String DROP_CREATE = "DROP TABLE IF EXISTS ODONTOLOGO; CREATE TABLE " +
            "ODONTOLOGO (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR(100) NOT NULL," +
            "NUMERO_MATRICULA VARCHAR(100) NOT NULL UNIQUE)";

    public static void createTable() {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            statement.execute(DROP_CREATE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./Odontologos", "sa", "sa");
    }
}
