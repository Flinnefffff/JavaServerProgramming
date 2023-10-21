import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/sourceServlet")
public class SourceServlet extends HttpServlet {
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String userName = req.getParameter("userName");
			String passWord = req.getParameter("passWord");
			PrintWriter out = res.getWriter();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(60);
			session.setAttribute("user", userName);
			session.setAttribute("passWord", passWord);
	
			Cookie[] cookies = req.getCookies();
			for(int i = 0; i < cookies.length; i++) {
				out.println("cookie name: " + cookies[i].getName() + "<br>");
				out.println("cookie value: " + cookies[i].getValue()+ "<br>");
			}
			String sessionId = session.getId();
			res.addCookie(new Cookie("token", "sometoken"));
			String url ="targetServlet?sessionId="+sessionId;
			res.setContentType("text/html");
			out.println("<a href='"+url+"'> Click here to get the user name</a>");
		}
	}

