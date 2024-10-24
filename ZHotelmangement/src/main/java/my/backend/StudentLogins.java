
package my.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentLogins")
public class StudentLogins extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String roomN = request.getParameter("RoomNumber");
        String passW = request.getParameter("Password");

        Connection con = null;
        PreparedStatement pw = null;
        ResultSet res = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            pw = con.prepareStatement("SELECT RoomNo, password FROM studentData WHERE RoomNo = ? AND password = ?");
            pw.setString(1, roomN);
            pw.setString(2, passW);
            
            res = pw.executeQuery();

            if (res.next()) {
            	request.setAttribute("roomNumber", roomN);
                RequestDispatcher rd = request.getRequestDispatcher("/StudentHome.jsp");
                rd.include(request, response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<script type='text/javascript'>");
                out.println("alert('Data not matched');");
              
                out.println("</script>");
                out.println("</body></html>");
                RequestDispatcher rd = request.getRequestDispatcher("/StudentLogin.jsp");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
         
            try {
                if (res != null) res.close();
                if (pw != null) pw.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
