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

@WebServlet("/Reciepe")
public class Reciepe extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myhotel";
    private static final String USER = "root";  
    private static final String PASS = "sitaram";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Daily Recipes</title>");
        out.println("<link rel='stylesheet' type='text/css' href='Rcp.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='table-container'>");
        out.println("<h2>Hostel Food Recipes</h2>");
        out.println("<table class='recipe-table'>");
        out.println("<tr><th>Day</th><th>Breakfast</th><th>Lunch</th><th>Dinner</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT day_name, breakfast_food, lunch_food, dinner_food FROM reciepes";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String dayName = rs.getString("day_name");
                String breakfastFood = rs.getString("breakfast_food");
                String lunchFood = rs.getString("lunch_food");
                String dinnerFood = rs.getString("dinner_food");

                out.println("<tr>");
                out.println("<td>" + dayName + "</td>");
                out.println("<td>" + breakfastFood + "</td>");
                out.println("<td>" + lunchFood + "</td>");
                out.println("<td>" + dinnerFood + "</td>");
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
        out.println("<div class='fixed-times'>");
        out.println("<p><strong>Breakfast Time:</strong> 9:00 AM to 10:00 AM</p>");
        out.println("<p><strong>Lunch Time:</strong> 1:00 PM to 2:00 PM </p>");
        out.println("<p><strong>Dinner Time:</strong> 9:00 PM  to 10:00 PM</p>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
