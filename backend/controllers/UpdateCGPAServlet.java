package controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import utils.DBConnection;

@WebServlet("/updateCGPA")
public class UpdateCGPAServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("student_id") == null) {
            response.sendRedirect("pages/auth.html");
            return;
        }

        int studentId = (int) session.getAttribute("student_id");
        String cgpaStr = request.getParameter("cgpa");

        try {
            double cgpa = Double.parseDouble(cgpaStr);

            Connection con = DBConnection.getConnection();

            String query = "UPDATE Student SET cgpa=? WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, cgpa);
            ps.setInt(2, studentId);

            ps.executeUpdate();

            response.sendRedirect("/profile");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/profile");
        }
    }
}