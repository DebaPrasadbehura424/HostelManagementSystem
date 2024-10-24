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

@WebServlet("/StudInfo")
public class StudInfo extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myhotel";
    private static final String USER = "root";  
    private static final String PASS = "sitaram";  

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // HTML and CSS for the response
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Information</title>");
        out.println("<link rel='stylesheet' type='text/css' href='stylesStudent.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='table-container'>");
        out.println("<h2>Student Information</h2>");



        out.println("<table class='student-table'>");
        out.println("<tr><th>ID</th><th>Room No</th><th>Student 1</th><th>Student 2</th><th>Student 3</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, RoomNo, stud1, stud2, stud3,stream1,stream2,stream3 FROM studentData";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int roomNo = rs.getInt("RoomNo");
                String student1 = rs.getString("stud1");
                String stm1 = rs.getString("stream1");
                String student2 = rs.getString("stud2");
                String stm2 = rs.getString("stream2");
                String student3 = rs.getString("stud3");
                String stm3 = rs.getString("stream3");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + roomNo + "</td>");
                out.println("<td>" + student1 +"(" + stm1+ ")"+ "</td>");
                out.println("<td>" + student2 +"(" + stm2+ ")"+  "</td>");
                out.println("<td>" + student3 +"(" + stm3+ ")"+  "</td>");
                out.println("</tr>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='5'>Error retrieving data</td></tr>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
