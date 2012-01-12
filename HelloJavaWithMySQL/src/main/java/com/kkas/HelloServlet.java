package com.kkas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.cloudfoundry.services.*;

import java.sql.*;
import javax.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
                response.setContentType("text/html; charset=Shift_JIS");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<head>");
                out.println("<title>データベーステスト</title>");
                out.println("</head>");
                out.println("<body>");

                Connection connection = null;

                try {
                          // establish connection to MySQL Service
                          ServiceManager services = ServiceManager.INSTANCE;
                          connection = (Connection) services.getInstance(CloudFoundryServices.MYSQL);

                          if (connection != null && !connection.isClosed()) {
                                   out.println("<p>Successfully connected to MySQL service</p>");
                          }

                          // creating a database table and populating some values
                          Statement s = connection.createStatement();
                          int count;
                          s.executeUpdate("DROP TABLE IF EXISTS animal");
                          s.executeUpdate("CREATE TABLE animal ("
                                + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                                + "PRIMARY KEY (id),"
                                + "name CHAR(40), category CHAR(40))");

                          out.println("<p>[1] Table successfully created.</p>");

                          count = s.executeUpdate("INSERT INTO animal (name, category)"
                                + " VALUES"
                                + "('snake', 'reptile'),"
                                + "('frog', 'amphibian'),"
                                + "('tuna', 'fish'),"
                                + "('racoon', 'mammal')");

                          out.println("<p>[2] " + count + " rows were inserted.</p>");

                          count = 0;
                          ResultSet rs = s.executeQuery("select * from animal");
                          while (rs.next()) {
                                count++;
                          }
                          out.println("<p>[3] " + count + " rows were fetched.</p>");
                }catch (Exception e){
                          out.println("Exception:" + e.getMessage());
                }finally{
                          try{
                                   if (connection != null && !connection.isClosed()) {
                                            connection.close();
                                   }
                          }catch(Exception e){
                                   out.println("Exception:" + e.getMessage());
                          }
                }

               out.println("</body>");
               out.println("</html>");

// This code prints out all environment variables that are assigned to the app.
/**
    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    out.println("System Environment:");
    //for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
    String key = null;
    for (Iterator i = System.getenv().keySet().iterator(); i.hasNext();) {
        //out.println(envvar.getKey() + ": " + envvar.getValue());
        key = (String)i.next();
        out.println(key + ": " + System.getenv(key));
    }
**/

	}
}
