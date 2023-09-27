import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import jakarta.resource.cci.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewStudentServlet")
public class ViewStudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement ps;
	
	@Override
	public void init() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101065_java_demo", "e2101065", "DZCtWC5pEC2");
			ps = conn.prepareStatement("SELECT * FROM student");
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		try {
			ResultSet rs = ps.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("<h1> Student </h1>");
			out.print("<table>");
			out.print("<tr>");
			out.print("<th> Number </th>");
			out.print("<th> Last Name </th>");
			out.print("<th> First Name </th>");
			out.print("</tr>");
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.print(rs.getInt(1));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(3));
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
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