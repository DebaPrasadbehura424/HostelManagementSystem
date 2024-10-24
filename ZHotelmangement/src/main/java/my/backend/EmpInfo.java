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
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/EmpInfo")
public class EmpInfo extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myhotel";
    private static final String USER = "root";  
    private static final String PASS = "sitaram";  

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // HTML and CSS for the response
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee Information</title>");
        out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='table-container'>");
        out.println("<h2>Employee Information</h2>");
        out.println("<table class='emp-table'>");
        out.println("<tr><th>Employee Name</th><th>Working Status</th><th>Duty Time</th><th>Unique ID</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT employee_name, working_status, duty_time, unique_id FROM Empinfos";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String employeeName = rs.getString("employee_name");
                String workingStatus = rs.getString("working_status");
                String dutyTime = rs.getString("duty_time");
                String uniqueId = rs.getString("unique_id");

                out.println("<tr>");
                out.println("<td>" + employeeName + "</td>");
                out.println("<td>" + workingStatus + "</td>");
                out.println("<td>" + dutyTime + "</td>");
                out.println("<td>" + uniqueId + "</td>");
                out.println("</tr>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='4'>Error retrieving data</td></tr>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
