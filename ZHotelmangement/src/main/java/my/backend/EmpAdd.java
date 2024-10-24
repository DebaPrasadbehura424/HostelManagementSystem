package my.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/EmpAdd")
public class EmpAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String status = request.getParameter("workingStatus");
        String dutyTime = request.getParameter("dutyTime");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            PreparedStatement pw = conn.prepareStatement("INSERT INTO empinfos (employee_name, working_status, duty_time) VALUES (?, ?, ?)");
            pw.setString(1, name);
            pw.setString(2, status);
            pw.setString(3, dutyTime);

            int result = pw.executeUpdate();
            if (result == 1) {
                out.print("<html><body>");
                out.print("<h1 style='color: green;'>Employee added successfully!</h1>");
             
                out.print("</body></html>");
            } else {
                out.print("<html><body>");
                out.print("<h1 style='color: red;'>Failed to add employee. Please try again.</h1>");
               
                out.print("</body></html>");
            }
        } catch (Exception e) {
            out.print("<html><body>");
            out.print("<h1 style='color: red;'>Try again some netwrok issue occuer " + e.getMessage() + "</h1>");
            
            out.print("</body></html>");
            e.printStackTrace();
        }
    }
}
