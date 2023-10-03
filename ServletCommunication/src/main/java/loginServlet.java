import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/loginServlet")
public class loginServlet extends HttpServlet {
	private Connection conn;
	private PreparedStatement ps;
	
	@Override
	public void init() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101065_java_demo", "e2101065", "DZCtWC5pEC2");
			ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			System.out.println(conn);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		RequestDispatcher reqDis;
		
		if (email.equals("huy@email.com") && password.equals("password")) {
			//login success
			System.out.println("Demo");
			reqDis = req.getRequestDispatcher("homeServlet");
			req.setAttribute("message", "login successfully");
			reqDis.forward(req, res);
		} else {
			//login unsuccessful
			reqDis = req.getRequestDispatcher("login.html");
			reqDis.include(req, res);
		}
		try {
			ps.setString(1,  email);
			ps.setString(2,  password);
			int result = ps.executeUpdate();
			PrintWriter out = res.getWriter();
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


		
	
	