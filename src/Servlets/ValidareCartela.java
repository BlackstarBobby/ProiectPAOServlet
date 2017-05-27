package Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

import static Connectivity.Database.*;

/**
 * Created by bobby on 12-05-2017.
 */
public class ValidareCartela extends HttpServlet {

    @Resource(name = "jdbc/metro")
    private DataSource database;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String message = null;
        try {
            String seria = request.getParameter("seria");
            if (seria == null) return;
            connection = database.getConnection();

            if (!checkSerial(connection, seria))
                return;
            switch (checkType(connection, seria)) {
                case "abonament": {
                    if (checkExpirationDate(connection, seria) && checkLastValidation(connection, seria) >= 15.0) {
                        message = "You can pass!";
                        updateLastValidated(connection, seria, "abonament");
                    } else
                        message = "Refuzat!";
                    break;
                }
                case "cartela": {
                    if (checkExpirationDate(connection, seria) && checkCalatoriiRamase(connection, seria) != 0) {
                        message = "You can pass!";
                        updateLastValidated(connection, seria, "cartela");
                    } else
                        message = "Refuzat!";
                }
                break;
            }

            request.setAttribute("message", message);
            request.getRequestDispatcher("/ValidareCartela.jsp").forward(request, response);
            connection.close();
        } catch (Exception e) {
            connection.close();
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
}
