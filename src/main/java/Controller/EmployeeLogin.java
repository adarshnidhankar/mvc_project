package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login_app_new")
public class EmployeeLogin extends HttpServlet {
	Connection con;
	Statement stmt;
	ResultSet rs;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja9?user=root&password=adarsh@221");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username").toLowerCase();
		int pass = Integer.parseInt(req.getParameter("pass"));

		int temp = 0;
		try {

			stmt = con.createStatement();
			rs = stmt.executeQuery("select username,password from employee_login;");
			while (rs.next()) {
				if (username.equals(rs.getString(1)) && pass == rs.getInt(2)) {
					HttpSession session = req.getSession();
					temp++;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (temp == 1) {
			RequestDispatcher rDispatcher = req.getRequestDispatcher("operations_Emp.html");
			rDispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("index.html");
		}
	}

}
