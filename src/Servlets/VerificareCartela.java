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
 * Created by bobby on 15-05-2017.
 */
public class VerificareCartela extends HttpServlet {

    @Resource(name = "jdbc/metro")
    private DataSource database;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection connection = null;
        String message = "Eroare! Incercati din nou.", message1, message2;
        try {
            String seria = request.getParameter("seria");
            connection = database.getConnection();

            if (!checkSerial(connection, seria)){
                request.setAttribute("message", message);
                request.getRequestDispatcher("/VerificareCartela.jsp").forward(request, response);
                return;
            }
            switch (checkType(connection, seria)) {
                case "abonament": {

                    double min = checkLastValidation(connection, seria);
                    if(min<15.0) {
                        message2 = String.valueOf(min);
                        request.setAttribute("message2", message2);
                    }else{
                        request.setAttribute("message2", "Au trecut mai mult de 15 min!");
                    }
                    break;
                }
                case "cartela": {
                    message1 = String.valueOf(checkCalatoriiRamase(connection, seria));
                    request.setAttribute("message1", message1);
                    break;
                }
            }
        } catch (Exception e) {
            connection.close();
        }

        request.getRequestDispatcher("/VerificareCartela.jsp").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
