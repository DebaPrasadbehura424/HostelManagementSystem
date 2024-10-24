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
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/AdminComplainStatus")
public class AdminComplainStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminComplainStatus() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Admin Complaint Status</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #e0f7fa;");
        out.println("    color: #333;");
        out.println("    margin: 0;");
        out.println("    padding: 20px;");
        out.println("    display: flex;");
        out.println("    flex-direction: column;");
        out.println("    align-items: center;");
        out.println("}");
        out.println("h2 {");
        out.println("    color: #00796b;");
        out.println("    animation: fadeIn 1s;");
        out.println("}");
        out.println("table {");
        out.println("    width: 100%;");
        out.println("    max-width: 800px;");
        out.println("    border-collapse: collapse;");
        out.println("    margin-top: 20px;");
        out.println("    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);");
        out.println("    border-radius: 8px;");
        out.println("    overflow: hidden;");
        out.println("}");
        out.println("th, td {");
        out.println("    padding: 12px;");
        out.println("    text-align: left;");
        out.println("    border: 1px solid #b0bec5;");
        out.println("}");
        out.println("th {");
        out.println("    background-color: #90caf9;");
        out.println("    color: #fff;");
        out.println("}");
        out.println("tr:nth-child(even) {");
        out.println("    background-color: #f0f4f8;");
        out.println("}");
        out.println("tr:hover {");
        out.println("    background-color: #e1f5fe;");
        out.println("    transition: background-color 0.3s;");
        out.println("}");
        out.println("@media (max-width: 600px) {");
        out.println("    body { padding: 10px; }");
        out.println("    table { width: 100%; }");
        out.println("}");
        out.println("@keyframes fadeIn {");
        out.println("    from { opacity: 0; }");
        out.println("    to { opacity: 1; }");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Complaints List</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");

            // Update visited_time for complaints that have not been visited
            PreparedStatement updateStmt = conn.prepareStatement("UPDATE compTable SET visited_time = NOW() WHERE visited_time IS NULL");
            updateStmt.executeUpdate();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT roomNo, complain, time, visited_time FROM compTable");

            out.println("<table>");
            out.println("<tr><th>Room Number</th><th>Complaint</th><th>Time</th><th>Visited Time</th></tr>");

            while (rs.next()) {
                String roomNo = rs.getString("roomNo");
                String complaint = rs.getString("complain");
                String time = rs.getString("time");
                String visitedTime = rs.getString("visited_time");

                out.println("<tr>");
                out.println("<td>" + roomNo + "</td>");
                out.println("<td>" + complaint + "</td>");
                out.println("<td>" + time + "</td>");
                out.println("<td>" + (visitedTime != null ? visitedTime : "Not visited") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error retrieving complaints: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
