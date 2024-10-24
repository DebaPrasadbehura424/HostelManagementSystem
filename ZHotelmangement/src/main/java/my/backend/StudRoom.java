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

@WebServlet("/StudRoom")
public class StudRoom extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.print("<html><head><title>Student Room</title>");
        out.print("<style>");
        out.print("body { font-family: Arial, sans-serif; background-color: #003366; color: #fff; margin: 0; padding: 20px; }");
        out.print("h2 { text-align: center; color: #FFD700; margin-bottom: 20px; }");
        out.print(".button-container { display: flex; flex-wrap: wrap; justify-content: center; }");
        out.print("button {");
        out.print("    background-color: #28a745; /* Default green */");
        out.print("    color: white;");
        out.print("    border: none;");
        out.print("    padding: 15px 30px;");
        out.print("    font-size: 18px;");
        out.print("    margin: 10px;");
        out.print("    cursor: pointer;");
        out.print("    transition: background-color 0.3s, transform 0.2s, box-shadow 0.3s;");
        out.print("    border-radius: 5px;");
        out.print("    animation: fadeIn 0.5s ease-in-out;");
        out.print("}");
        out.print("button:hover {");
        out.print("    background-color: #218838; /* Darker green on hover */");
        out.print("    transform: scale(1.05);");
        out.print("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);");
        out.print("}");
        out.print("@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }");
        out.print("</style></head><body>");
        
        out.print("<h2>Student Room</h2>");
        out.print("<div class='button-container'>");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            PreparedStatement pw = con.prepareStatement("SELECT id, stud1, stud2, stud3 FROM studentData");
            ResultSet res = pw.executeQuery();
            while (res.next()) {
                String buttonColor = ((res.getString(2).isEmpty()) || (res.getString(3).isEmpty()) || (res.getString(4).isEmpty())) ? "red" : "green";
                out.print("<a href='EditStud?id=" + res.getInt(1) + "'><button style='background-color:" + buttonColor + ";'>" + res.getInt(1) + "</button></a>");
            }
            
            res.close();
            pw.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<p>Error: " + e.getMessage() + "</p>");
        }
        
        out.print("</div>");
        out.print("</body></html>");
    }
}
