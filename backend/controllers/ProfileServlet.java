package controllers;
import java.io.IOException;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import utils.DBConnection;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("student_id") == null) {
            response.sendRedirect("pages/auth.html");
            return;
        }

        int studentId = (int) session.getAttribute("student_id");

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Student WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("name", rs.getString("name"));
                request.setAttribute("age", rs.getInt("age"));
                request.setAttribute("college", rs.getString("college"));
                request.setAttribute("department", rs.getString("department"));
                request.setAttribute("usn", rs.getString("usn"));
                request.setAttribute("cgpa", rs.getDouble("cgpa"));
                request.setAttribute("profile_image", rs.getString("profile_image"));
            }

            request.getRequestDispatcher("pages/profile.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}