
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsertPettinger extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsertPettinger() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("userName");
      String email = request.getParameter("email");
      String address = request.getParameter("address");
      String phone = request.getParameter("phone");
      String lastvisit = request.getParameter("lastvisit");
      String importantinfo = request.getParameter("patientinfo");
      

      Connection connection = null;
      String insertSql = " INSERT INTO TechTablePettinger (id, NAME, EMAIL, ADDRESS, PHONE, LASTVISIT, IMPORTANTINFO) values (default, ?, ?, ?, ?, ?, ?)";

      try {
         DBConnectionPettinger.getDBConnection();
         connection = DBConnectionPettinger.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, userName);
         preparedStmt.setString(2, email);
         preparedStmt.setString(3, address);
         preparedStmt.setString(4, phone);
         preparedStmt.setString(5, lastvisit);
         preparedStmt.setString(6, importantinfo);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Data Inserted into Patient Database";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>User Name</b>: " + userName + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>Phone</b>: " + phone + "\n" + //
            "  <li><b>Address</b>: " + address + "\n" + //
            "  <li><b>LastVisit</b>: " + lastvisit + "\n" + //
            "  <li><b>ImportantInfo</b>: " + importantinfo + "\n" + //

            "</ul>\n");

      out.println("<a href=simpleFormSearch.html>Search patients by name</a> <br>");
      out.println("<a href=SearchByEmail.html>Search patients by email</a> <br>");
      out.println("<a href=SearchByAddress.html>Search patients by address</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
