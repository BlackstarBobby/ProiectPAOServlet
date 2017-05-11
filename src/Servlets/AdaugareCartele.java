package Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bobby on 10-05-2017.
 */
public class AdaugareCartele extends HttpServlet {

    @Resource(name = "jdbc/metro")
    private DataSource database;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //String query = "INSERT into Abonamente(serie, abonament_lunar, abonament_zi, cartela, calatorii, calatorii_ramase) VALUES (?,?,?,?,?,?)";
            //SessionIdentifierGenerator sig = new SessionIdentifierGenerator();

            connection = database.getConnection();
            //preparedStatement = connection.prepareStatement(query);

            if(request.getParameter("abonamentLunar")!=null)
                insertAbonamenteLunare(connection,request.getParameter("nrAbonamentLunar"));


            //preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            connection.close();
            preparedStatement.close();
        }
    }

    public void insertAbonamenteLunare(Connection conn, String bucati) throws SQLException {
        PreparedStatement preparedStatement =null;
        try{
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

        }catch (Exception e){
            preparedStatement.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final class SessionIdentifierGenerator {
        private SecureRandom random = new SecureRandom();

        public String nextSessionId() {
            return new BigInteger(130, random).toString(32);
        }
    }

    private boolean checkUniqueSerial(String serial, Connection conn) throws SQLException {
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
