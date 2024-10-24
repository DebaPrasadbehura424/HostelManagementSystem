package my.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OwnRoom")
public class OwnRoom extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String roomNumber = request.getParameter("roomNumber");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Room Details</title>");
        out.println("    <link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap' rel='stylesheet'>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            background-color: #003366;"); // Dark blue background
        out.println("            font-family: 'Roboto', sans-serif;");
        out.println("            color: #fff;");
        out.println("            margin: 0;");
        out.println("            padding: 0;");
        out.println("        }");
        out.println("        .container {");
        out.println("            max-width: 800px;");
        out.println("            margin: auto;");
        out.println("            text-align: center;");
        out.println("            padding: 40px;");
        out.println("            background: #ffffff;");
        out.println("            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);");
        out.println("            border-radius: 10px;");
        out.println("            animation: fadeIn 0.5s ease-in-out;");
        out.println("        }");
        out.println("        h1 {");
        out.println("            margin-bottom: 30px;");
        out.println("            color: #FFD700;"); // Dark golden color
        out.println("        }");
        out.println("        .room-number {");
        out.println("            background-color: #F7F7F7;");
        out.println("            border: 2px solid #FFD700;"); // Dark golden border
        out.println("            border-radius: 8px;");
        out.println("            margin: 15px 0;");
        out.println("            color:#1e1e1e;");
        out.println("            padding: 20px;");
        out.println("            transition: transform 0.3s, box-shadow 0.3s;");
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);");
        out.println("        }");
        out.println("        .room-number:hover {");
        out.println("            transform: scale(1.05);");
        out.println("            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);");
        out.println("        }");
        out.println("        @media (max-width: 600px) {");
        out.println("            .container {");
        out.println("                padding: 20px;");
        out.println("            }");
        out.println("            .room-number {");
        out.println("                padding: 15px;");
        out.println("                flex-direction: column;"); // Stack items vertically on smaller screens
        out.println("                text-align: center;"); // Center text
        out.println("            }");
        out.println("        }");
        out.println("        @keyframes fadeIn {");
        out.println("            from { opacity: 0; }");
        out.println("            to { opacity: 1; }");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>Room: " + roomNumber + "</h1>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            PreparedStatement pw = conn.prepareStatement("SELECT * FROM studentData WHERE RoomNo=?");
            pw.setString(1, roomNumber);
            
            ResultSet res = pw.executeQuery();
            
            if (res.next()) {
                out.println("<div class='room-number'>Student 1: " + res.getString("stud1") + " (" + res.getString("stream1") + ")</div>");
                out.println("<div class='room-number'>Student 2: " + res.getString("stud2") + " (" + res.getString("stream2") + ")</div>");
                out.println("<div class='room-number'>Student 3: " + res.getString("stud3") + " (" + res.getString("stream3") + ")</div>");
            } else {
                out.println("<div class='room-number'>No students found for this room.</div>");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<div class='room-number'>An error occurred while fetching data.</div>");
        }

        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}
