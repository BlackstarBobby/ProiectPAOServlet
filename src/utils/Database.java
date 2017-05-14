package utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Created by bobby on 14-05-2017.
 */
public class Database {


    //INSERT FUNCTIONS
    public static void insertAbonamenteLunare(Connection conn, String bucati) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into Abonamente(serie, abonament_lunar, abonament_zi, cartela, calatorii, calatorii_ramase) VALUES (?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            SessionIdentifierGenerator sig = new SessionIdentifierGenerator();

            for (int i = 0; i < Integer.parseInt(bucati); i++) {
                String serial = "";
                do {
                    serial = sig.nextSessionId();
                } while (checkUniqueSerial(serial, conn));
                preparedStatement.setString(1, serial);
                preparedStatement.setBoolean(2, true);
                preparedStatement.setNull(3, 0);
                preparedStatement.setNull(4, 0);
                preparedStatement.setNull(5, 0);
                preparedStatement.setNull(6, 0);
                preparedStatement.execute();
            }
            preparedStatement.close();

        } catch (Exception e) {
            preparedStatement.close();
        }
    }

    public static void insertAbonamenteZi(Connection conn, String bucati) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into Abonamente(serie, abonament_lunar, abonament_zi, cartela, calatorii, calatorii_ramase) VALUES (?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            SessionIdentifierGenerator sig = new SessionIdentifierGenerator();

            for (int i = 0; i < Integer.parseInt(bucati); i++) {
                String serial = "";
                do {
                    serial = sig.nextSessionId();
                } while (checkUniqueSerial(serial, conn));
                preparedStatement.setString(1, serial);
                preparedStatement.setBoolean(3, true);
                preparedStatement.setNull(2, 0);
                preparedStatement.setNull(4, 0);
                preparedStatement.setNull(5, 0);
                preparedStatement.setNull(6, 0);
                preparedStatement.execute();
            }
            preparedStatement.close();

        } catch (Exception e) {
            preparedStatement.close();
        }
    }

    public static void insertCartela(Connection conn, String bucati10, String bucati2) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into Abonamente(serie, abonament_lunar, abonament_zi, cartela, calatorii, calatorii_ramase) VALUES (?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            SessionIdentifierGenerator sig = new SessionIdentifierGenerator();

            for (int i = 0; i < Integer.parseInt(bucati10); i++) {
                String serial = "";
                do {
                    serial = sig.nextSessionId();
                } while (checkUniqueSerial(serial, conn));
                preparedStatement.setString(1, serial);
                preparedStatement.setBoolean(4, true);
                preparedStatement.setNull(2, 0);
                preparedStatement.setNull(3, 0);
                preparedStatement.setInt(5, 10);
                preparedStatement.setInt(6, 10);
                preparedStatement.execute();
            }

            for (int i = 0; i < Integer.parseInt(bucati2); i++) {
                String serial = "";
                do {
                    serial = sig.nextSessionId();
                } while (checkUniqueSerial(serial, conn));
                preparedStatement.setString(1, serial);
                preparedStatement.setBoolean(4, true);
                preparedStatement.setNull(2, 0);
                preparedStatement.setNull(3, 0);
                preparedStatement.setInt(5, 2);
                preparedStatement.setInt(6, 2);
                preparedStatement.execute();
            }
            preparedStatement.close();

        } catch (Exception e) {
            preparedStatement.close();
        }
    }


    //CHECK FUNCTIONS
    public static boolean checkSerial(Connection conn, String serial) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT * FROM Abonamente WHERE serie=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serial);
            if (preparedStatement.execute()) {
                preparedStatement.close();
                return true;
            }
        } catch (Exception w) {
            preparedStatement.close();
        }
        return false;
    }

    public static boolean checkExpirationDate(Connection conn, String serial) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT ultima_validare FROM Abonamente WHERE serie=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serial);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Timestamp expDate = resultSet.getTimestamp(1);
            Timestamp timeNow = Timestamp.valueOf(LocalDateTime.now());
            resultSet.close();
            preparedStatement.close();
            if (timeNow.compareTo(expDate) <= 0)
                return true;
        } catch (SQLException e) {
            preparedStatement.close();
        }
        return false;
    }

    public static double checkLastValidation(Connection conn, String serial) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT last_validated FROM Abonamente WHERE serie=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serial);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Timestamp expDate = resultSet.getTimestamp(1);
            Timestamp timeNow = Timestamp.valueOf(LocalDateTime.now());
            resultSet.close();
            preparedStatement.close();
            double minutes = ((double) (timeNow.getTime() - expDate.getTime()) / 60000.0);
            DecimalFormat df=new DecimalFormat("0.0");
            String formate = df.format(minutes);
            double finalValue = (Double)df.parse(formate) ;
            return finalValue; //returns the number of minutes since last use
        } catch (Exception e) {
            preparedStatement.close();
        }
        return 0;
    }

    public static int checkCalatoriiRamase(Connection conn, String serial) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT calatorii_ramase FROM Abonamente WHERE serie=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serial);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int calatoriiRamase = resultSet.getInt(1);
            resultSet.close();
            preparedStatement.close();
            return calatoriiRamase;
        } catch (Exception e) {
            preparedStatement.close();
        }
        return 0;
    }

    public static String checkType(Connection conn, String serial) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT abonament_lunar,abonament_zi,cartela FROM Abonamente WHERE serie=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serial);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getBoolean(1) || resultSet.getBoolean(2))
                    return "abonament";
//            if (resultSet.getBoolean(1))
//                return "abonament lunar";
//            if (resultSet.getBoolean(2))
//                return "abonament zi";
                if (resultSet.getBoolean(3))
                    return "cartela";
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            preparedStatement.close();
        }
        return "";
    }

    //UPDATE FUNCTIONS
    public static boolean updateLastValidated(Connection conn, String serial, String type) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            if (type.equals("abonament")) {
                String query = "UPDATE Abonamente SET last_validated =now() WHERE serie = ?";
                preparedStatement = conn.prepareStatement(query);
                //preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setString(1, serial);
            } else if (type.equals("cartela")) {
                String query = "UPDATE Abonamente SET calatorii_ramase = calatorii_ramase-1 WHERE serie = ?";
                preparedStatement = conn.prepareStatement(query);
                //preparedStatement.setInt(1, );
                preparedStatement.setString(1, serial);
            }
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
            preparedStatement.close();
        } catch (Exception e) {
            preparedStatement.close();
        }
        return false;
    }

    //UNIQUE SERIAL GENERATOR
    public static final class SessionIdentifierGenerator {
        private SecureRandom random = new SecureRandom();

        public String nextSessionId() {
            return new BigInteger(130, random).toString(32);
        }
    }

    private static boolean checkUniqueSerial(String serial, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abonamente WHERE serie=?");
        ps.setString(1, serial);
        if (ps.execute()) {
            ps.close();
            return false;
        }
        ps.close();
        return true;
    }

}

