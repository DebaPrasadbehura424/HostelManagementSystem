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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/Complain")
public class Complain extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String roomNumber = request.getParameter("roomNumber");

        out.println("<html>");
        out.println("<head><title>Complain</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #001f3f; color: #FFD700; margin: 0; padding: 20px; }");
        out.println("h2 { color: #FFD700; }");
        out.println("form { background-color: #003366; border: 1px solid #FFD700; border-radius: 8px; padding: 20px; max-width: 400px; margin: auto; }");
        out.println("label { display: block; margin: 10px 0 5px; }");
        out.println("input[type='text'], textarea { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'], input[type='button'] { background-color: #FFD700; color: #001f3f; border: none; border-radius: 4px; padding: 10px; cursor: pointer; margin-top: 10px; transition: background-color 0.3s; }");
        out.println("input[type='submit']:hover, input[type='button']:hover { background-color: #FFC107; }");
        out.println("@media (max-width: 480px) { form { padding: 15px; } }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Submit Your Complaint</h2>");
        out.println("<form action='Complain' method='post'>");
        out.println("<label for='roomNumber'>Room Number:</label>");
        out.println("<input type='text' id='roomNumber' name='roomNumber' value='" + roomNumber + "' readonly /><br/><br/>");
        out.println("<label for='complaint'>Your Complaint:</label><br/>");
        out.println("<textarea id='complaint' name='complaint' rows='5' required></textarea><br/><br/>");
        out.println("<input type='submit' value='Submit' />");
        out.println("<input type='button' value='Cancel' onclick='window.history.back();' />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomNumber = request.getParameter("roomNumber");
        String complaint = request.getParameter("complaint");
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body style='background-color: #001f3f; color: #FFD700;'>");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
                 PreparedStatement pw = conn.prepareStatement("INSERT INTO compTable (roomNo, complain, time) VALUES (?, ?, ?)")) {
                pw.setString(1, roomNumber);
                pw.setString(2, complaint);
                pw.setString(3, formattedDateTime);
                pw.executeUpdate();
                out.println("<h3>Your complaint has been submitted successfully!</h3>");
                out.println("<p>Room Number: " + roomNumber + "</p>");
                out.println("<p>Complaint: " + complaint + "</p>");
                out.println("<p>Submitted on: " + formattedDateTime + "</p>");
            }
        } catch (Exception e) {
            out.println("<h3>There was an error processing your complaint. Please try again.</h3>");
        }
        
        out.println("</body></html>");
    }
}
