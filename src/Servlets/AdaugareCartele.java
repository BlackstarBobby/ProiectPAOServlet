package Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bobby on 10-05-2017.
 */
public class AdaugareCartele extends HttpServlet {

    @Resource(name="jdbc/metro")
    private DataSource dbRes;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try(Connection conn = dbRes.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abonamente");
            ResultSet rs = ps.executeQuery();){
            while (rs.next()){
                response.getWriter().append(rs.getString("Serie"));
            }

        }

//        Connection conn = dbRes.getConnection();
//        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abonamente");
//        ResultSet rs = ps.executeQuery();
//        while (rs.next())
//            response.getWriter().append(rs.getString("Serie"));
//        conn.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
