package demo_01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/helloNameServlet")
public class helloNameServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		if (name != null && name != "")  {
			out.println("Hello" + name);
		}   else   {
				out.println("Name is empty");
		}
	}

}
//http://localhost:8080/demo_01/helloName.html