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

@WebServlet(urlPatterns="/addStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private Connection conn;
	private PreparedStatement ps;
	
	@Override
	public void init() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101065_java_demo", "e2101065", "DZCtWC5pEC2");
			ps = conn.prepareStatement("INSERT INTO student VALUE(?, ?, ?)");
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int number = Integer.parseInt(req.getParameter("number"));
		String lastname = req.getParameter("lastName");
		String firstname = req.getParameter("firstName");
		try {
			ps.setInt(1,  number);
			ps.setString(2,  lastname);
			ps.setString(3,  firstname);
			int result = ps.executeUpdate();
			PrintWriter out = res.getWriter();
			out.println(result + " student created");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void destroy( ) {
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
