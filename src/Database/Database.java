package Database;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;

/**
 * Created by bobby on 09-05-2017.
 */
public class Database {

    private Connection connection;

    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://ihatethis.ddns.net:3306/metro", "", "");
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final class SessionIdentifierGenerator {
        private SecureRandom random = new SecureRandom();

        public String nextSessionId() {
            return new BigInteger(130, random).toString(32);
        }
    }

    public boolean serialExists(String serial) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result;
            String query = "Select Serie FROM Abonamente WHERE Serie = \"" + serial + "\"";
            result = statement.executeQuery(query);

            if (result.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
