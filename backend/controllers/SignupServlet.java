package controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import utils.DBConnection;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String college = request.getParameter("college");
        String department = request.getParameter("department");
        String usn = request.getParameter("usn");

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO Student (name, email, password, age, college, department, usn) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, Integer.parseInt(age));
            ps.setString(5, college);
            ps.setString(6, department);
            ps.setString(7, usn);

            ps.executeUpdate();

            response.sendRedirect("pages/auth.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}