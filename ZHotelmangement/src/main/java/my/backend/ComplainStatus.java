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

@WebServlet("/ComplainStatus")
public class ComplainStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ComplainStatus() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Complaint Status</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #e0f7fa; color: #333; margin: 0; padding: 20px; }");
        out.println("h2 { color: #00796b; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        out.println("th, td { padding: 10px; text-align: left; border: 1px solid #b0bec5; }");
        out.println("th { background-color: #90caf9; }");
        out.println("tr:nth-child(even) { background-color: #f0f4f8; }");
        out.println("@media (max-width: 600px) {");
        out.println("    table { font-size: 14px; }");
        out.println("    th, td { padding: 8px; }");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Complaints List</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            
        
            
        
            PreparedStatement storedata=conn.prepareStatement("SELECT roomNo, complain, time, visited_time FROM compTable");
            ResultSet rs = storedata.executeQuery();

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
           storedata.close();
           
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
