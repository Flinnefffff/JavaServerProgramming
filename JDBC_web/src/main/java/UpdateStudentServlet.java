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

@WebServlet("/updateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    private Connection connection;
    private PreparedStatement statement;

    @Override
    public void init() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101065_java_demo", "e2101065", "DZCtWC5pEC2");
            statement = connection.prepareStatement("UPDATE student SET firstname=? WHERE lastname=?");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        
        if (lastname != null && firstname != null) {
            try {
                statement.setString(1, firstname);
                statement.setString(2, lastname);
                int result = statement.executeUpdate();

                out.println("<html><head><title>Update Student</title></head><body>");
                out.println("<h1>Update Student</h1>");

                if (result > 0) {
                    out.println("<p>" + result + " student updated successfully.</p>");
                } else {
                    out.println("<p>No student found with the last name: " + lastname + "</p>");
                }

                out.println("</body></html>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Display a form to enter the last name and new first name for updating
            out.println("<html><head><title>Update Student</title></head><body>");
            out.println("<h1>Update Student</h1>");
            out.println("<form method=\"get\" action=\"updateStudentServlet\">");
            out.println("Last Name: <input type=\"text\" name=\"lastname\"><br>");
            out.println("New First Name: <input type=\"text\" name=\"firstname\"><br>");
            out.println("<input type=\"submit\" value=\"Update Student\">");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
