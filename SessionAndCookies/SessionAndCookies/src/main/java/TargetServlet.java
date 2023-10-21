import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/targetServlet")
public class TargetServlet extends HttpServlet {
	private Connection conn;
	@Override
	public void init() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101089_java_demo","e2101065","DZCtWC5pEC2");
			System.out.println(conn);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {		
			HttpSession session = req.getSession(false);
			String userName = req.getParameter("userName");
			String passWord = req.getParameter("passWord");
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			res.sendRedirect("login.html");
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				out.println("cookie name: " + cookies[i].getName() + "<br>");
				out.println("cookie value: " + cookies[i].getValue()+ "<br>");
			}
			}else {
				out.println("No cookies found");
			}
			if(session == null) {
				out.println("Session has ended");
			} else {
				userName = (String) session.getAttribute("user");
				out.println("Hello " + userName);
			}
		}
    		
	@Override
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
}

