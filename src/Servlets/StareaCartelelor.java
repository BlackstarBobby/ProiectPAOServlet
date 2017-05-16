package Servlets;

import utils.Data;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Connectivity.Database.*;

/**
 * Created by bobby on 15-05-2017.
 */
public class StareaCartelelor extends HttpServlet {

    @Resource(name = "jdbc/metro")
    private DataSource database;

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection connection = null;
        String tableAbL = "", tableAbZi="",tableCartele="";
        try{
            connection = database.getConnection();



            if (request.getParameter("abonamentLunar")!=null){
                ArrayList<Data> data = new ArrayList<>();
                getAbonamenteLunare(connection,data);
                request.setAttribute("tableAbonamenteLunare", data);
            }

            if (request.getParameter("abonamentZi")!=null){

            }

            if (request.getParameter("cartela")!=null){

            }

            connection.close();
        }catch (Exception e){
            connection.close();
        }
        request.getRequestDispatcher("/StareaCartelelor.jsp").forward(request, response);
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
