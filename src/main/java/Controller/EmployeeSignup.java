package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup_emp")
public class EmployeeSignup extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=adarsh@221");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		if (session != null) {

			String username = req.getParameter("username").toLowerCase();
			String pass = req.getParameter("pass").toLowerCase();

			try {
				ps = con.prepareStatement("insert into employee_login_info(username,password,emp_ref) values(?,?,?);");
				ps.setString(1, username);
				ps.setString(2, pass);
				ps.setInt(3, 1);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rDispatcher = req.getRequestDispatcher("display.jsp");
			rDispatcher.forward(req, resp);
		}
	}
}
