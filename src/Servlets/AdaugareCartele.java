package Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static Connectivity.Database.*;

/**
 * Created by bobby on 10-05-2017.
 */
public class AdaugareCartele extends HttpServlet {

    @Resource(name = "jdbc/metro")
    private DataSource database;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        Connection connection = null;
        try {
            connection = database.getConnection();

            if (request.getParameter("abonamentLunar") != null)
                insertAbonamenteLunare(connection, request.getParameter("nrAbonamentLunar"));
            if (request.getParameter("abonamentZi") != null)
                insertAbonamenteZi(connection, request.getParameter("nrAbonamenteZi"));
            if (request.getParameter("cartela") != null)
                insertCartela(connection, request.getParameter("nrCartela10"), request.getParameter("nrCartela2"));

            String message = "Done!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/Adauga%20Cartele.jsp").forward(request, response);

            connection.close();
        } catch (Exception e) {
            connection.close();
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

