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

@WebServlet("/UpdateStud")
public class UpdateStud extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idx = request.getParameter("idx");
        String Roomx = request.getParameter("RoomNum");
        String stud1x = request.getParameter("std1");
        String stud2x = request.getParameter("std2");
        String stud3x = request.getParameter("std3");
        String stm1 = request.getParameter("stream1");
        String stm2 = request.getParameter("stream2");
        String stm3 = request.getParameter("stream3");

        PrintWriter out = response.getWriter();
        Connection con = null;
        PreparedStatement pw = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel", "root", "sitaram");

            // Start building the SQL statement
            StringBuilder sql = new StringBuilder("UPDATE studentData SET RoomNo = ?,stud1=?,stud2=?,stud3=? ");
         

            if (stm1 != null && !stm1.isEmpty()) {
                sql.append(", stream1 = ?");
            }
            if (stm2 != null && !stm2.isEmpty()) {
                sql.append(", stream2 = ?");
            }
            if (stm3 != null && !stm3.isEmpty()) {
                sql.append(", stream3 = ?");
            }
            sql.append(" WHERE id = ?");

         
            pw = con.prepareStatement(sql.toString());
            pw.setString(1, Roomx);  

     
              pw.setString(2, stud1x);
               pw.setString(3, stud2x);
              pw.setString(4, stud3x);
              
            if (stm1 != null && !stm1.isEmpty()) pw.setString(5, stm1);
            if (stm2 != null && !stm2.isEmpty()) pw.setString(6, stm2);
            if (stm3 != null && !stm3.isEmpty()) pw.setString(7, stm3);

            pw.setString(8, idx);

            if ( pw.executeUpdate() == 1) {
                out.print("Update successful");
            } else {
                out.print("Update not successful");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("An error occurred: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pw != null) pw.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
