package demo_02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/sumServlet")
public class SumServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        String num1Str = req.getParameter("num1");
        String num2Str = req.getParameter("num2");

        try {
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);

            int sum = num1 + num2;
            out.println(num1 + " + " + num2 + " = " + sum);
        } catch (NumberFormatException e) {
            out.println("Invalid input. Please enter two valid numbers.");
        }
    }
}