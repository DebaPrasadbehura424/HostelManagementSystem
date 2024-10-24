package my.backend;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/Logins")
public class AdminLogin extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String myname = request.getParameter("AdminName");
        String mypass = request.getParameter("AdminPass");
        
        PrintWriter out = response.getWriter();
       

        Connection con = null;
        PreparedStatement pw = null;
        ResultSet res = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");
            
           
            pw = con.prepareStatement("SELECT * FROM admindata WHERE AdmName=? AND AdmPass=?");
            pw.setString(1, myname);
            pw.setString(2, mypass);
            
            res = pw.executeQuery();
            
            if (res.next()) {
                RequestDispatcher rd = request.getRequestDispatcher("/AdminHome.jsp");
                rd.include(request, response);
            } else {
            	  out.print("<h1 style='position:absolute'>Not mathed </h1>");
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
