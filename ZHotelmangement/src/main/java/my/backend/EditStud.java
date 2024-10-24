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

@WebServlet("/EditStud")
public class EditStud extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String ids = request.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            
            PreparedStatement pw = con.prepareStatement("SELECT * FROM studentData WHERE id=?");
            pw.setString(1, ids);
            ResultSet res = pw.executeQuery();
            
            out.print("<html><head>");
            out.print("<style>");
            out.print("body {");
            out.print("    background-color: #003366; /* Dark blue background */");
            out.print("    font-family: 'Roboto', sans-serif; /* Clean font */");
            out.print("    display: flex;");
            out.print("    justify-content: center;");
            out.print("    align-items: center;");
            out.print("    height: 100vh; /* Full viewport height */");
            out.print("    margin: 0;");
            out.print("}"); 
            out.print(".form-container {");
            out.print("    background-color: #ffffff; /* White background for the form */");
            out.print("    border-radius: 15px;");
            out.print("    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);");
            out.print("    max-width: 400px;");
            out.print("    margin: 20px;");
            out.print("    padding: 50px;");
            out.print("    display: flex;");
            out.print("    flex-direction: column;");
            out.print("    align-items: center;");
            out.print("    animation: fadeIn 0.5s ease-in-out;");
            out.print("}"); 
            out.print(".input-group {");
            out.print("    display: flex;");
            out.print("    justify-content: space-between;");
            out.print("    width: 100%;");
            out.print("    margin-bottom: 12px;");
            out.print("}"); 
            out.print("input[type='text'], select {");
            out.print("    flex: 1;");
            out.print("    padding: 12px;");
            out.print("    margin: 0 10px;");
            out.print("    border: 2px solid #FFD700; /* Dark golden border */");
            out.print("    border-radius: 10px;");
            out.print("    font-size: 16px;");
            out.print("    transition: border-color 0.3s, box-shadow 0.3s;");
            out.print("}"); 
            out.print("input[type='text']:focus, select:focus {");
            out.print("    border-color: #1de9b6; /* Teal border on focus */");
            out.print("    box-shadow: 0 0 5px rgba(29, 233, 182, 0.5); /* Teal glow on focus */");
            out.print("}"); 
            out.print("input[type='submit'], input[type='reset'] {");
            out.print("    background-color: #FFD700; /* Dark golden background for buttons */");
            out.print("    color: white;");
            out.print("    border: none;");
            out.print("    padding: 12px;");
            out.print("    margin: 12px 0;");
            out.print("    border-radius: 10px;");
            out.print("    cursor: pointer;");
            out.print("    width: 100%;");
            out.print("    font-size: 18px;");
            out.print("    transition: background-color 0.3s, transform 0.3s;");
            out.print("}"); 
            out.print("input[type='submit']:hover, input[type='reset']:hover {");
            out.print("    transform: scale(1.05); /* Scale on hover */");
            out.print("}"); 
            out.print("@media (max-width: 600px) {");
            out.print("    .form-container {");
            out.print("        padding: 20px;");
            out.print("    }");
            out.print("    .input-group {");
            out.print("        flex-direction: column;");
            out.print("    }");
            out.print("    input[type='text'], select {");
            out.print("        margin: 10px 0; /* Add vertical margin for stacked layout */");
            out.print("    }");
            out.print("}"); 
            out.print("@keyframes fadeIn {");
            out.print("    from { opacity: 0; }");
            out.print("    to { opacity: 1; }");
            out.print("}"); 
            out.print("</style></head><body>");

            out.print("<form action='UpdateStud' method='post' class='form-container'>");
            if (res.next()) {
                out.print("<input hidden type='text' name='idx' value='" + res.getInt(1) + "' />");
                out.print("<input type='text' name='RoomNum' value='" + res.getInt(2) + "' required disabled/>");
                
                out.print("<br/>");
                
                out.print("<div class='input-group'>");
                out.print("<input type='text' name='std1' value='" + res.getString(3) + "' />");
                out.print("<select name='stream1'>");
                out.print("<option value='" + res.getString(7) + "'>" + res.getString(7) + "</option>");
                out.print("<option value='BTech'>BTech</option>");
                out.print("<option value='Diploma'>Diploma</option>");
                out.print("<option value='BCA'>BCA</option>");
                out.print("<option value='MCA'>MCA</option>");
                out.print("<option value='BPharma'>BPharma</option>");
                out.print("</select>");
                out.print("</div>");
                
                out.print("<div class='input-group'>");
                out.print("<input type='text' name='std2' value='" + res.getString(4) + "' />");
                out.print("<select name='stream2'>");
                out.print("<option value='" + res.getString(8) + "'>" + res.getString(8) + "</option>");
                out.print("<option value='BTech'>BTech</option>");
                out.print("<option value='Diploma'>Diploma</option>");
                out.print("<option value='BCA'>BCA</option>");
                out.print("<option value='MCA'>MCA</option>");
                out.print("<option value='BPharma'>BPharma</option>");
                out.print("</select>");
                out.print("</div>");

                out.print("<div class='input-group'>");
                out.print("<input type='text' name='std3' value='" + res.getString(5) + "' />");
                out.print("<select name='stream3'>");
                out.print("<option value='" + res.getString(9) + "'>" + res.getString(9) + "</option>");
                out.print("<option value='BTech'>BTech</option>");
                out.print("<option value='Diploma'>Diploma</option>");
                out.print("<option value='BCA'>BCA</option>");
                out.print("<option value='MCA'>MCA</option>");
                out.print("<option value='BPharma'>BPharma</option>");
                out.print("</select>");
                out.print("</div>");

                out.print("<input type='submit' value='Update' />");
                out.print("<input type='reset' value='Cancel' />");
            }
            out.print("</form>");

            out.print("</body></html>");
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<div style='color: red;'>An error occurred while fetching data.</div>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
