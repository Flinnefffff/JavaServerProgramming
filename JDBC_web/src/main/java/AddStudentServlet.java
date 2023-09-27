import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement ps;

    @Override
    public void init() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101065_java_demo", "e2101065", "DZCtWC5pEC2");
            ps = conn.prepareStatement("INSERT INTO student (number, lastname, firstname) VALUES (?, ?, ?)");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Handle GET requests here (e.g., show a form to enter student information)
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Add Student</title></head><body>");
        out.println("<h1>Add Student</h1>");

        // Check if the request contains form data
        String numberParam = req.getParameter("number");
        String lastnameParam = req.getParameter("lastname");
        String firstnameParam = req.getParameter("firstname");

        if (numberParam != null && lastnameParam != null && firstnameParam != null) {
            // Form data submitted via GET, process and insert into the database
            int number = Integer.parseInt(numberParam);
            String lastname = lastnameParam;
            String firstname = firstnameParam;
            try {
                ps.setInt(1, number);
                ps.setString(2, lastname);
                ps.setString(3, firstname);
                int result = ps.executeUpdate();
                out.println(result + " student created");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Display the form
            out.println("<form method=\"get\" action=\"addStudentServlet\">");
            out.println("Number: <input type=\"text\" name=\"number\"><br>");
            out.println("Last Name: <input type=\"text\" name=\"lastname\"><br>");
            out.println("First Name: <input type=\"text\" name=\"firstname\"><br>");
            out.println("<input type=\"submit\" value=\"Add Student\">");
            out.println("</form>");
        }

        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        try {
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
