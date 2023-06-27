package Emp_data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addEmp_new")
public class Add_Employee extends HttpServlet {
	static Connection con;
	static PreparedStatement ps;
	static ResultSet resultSet;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			
			String name = req.getParameter("empname");
			String mail = req.getParameter("mail");
			String desg = req.getParameter("desg");
			double salary = Double.parseDouble(req.getParameter("salary"));
			String birthdaydate = req.getParameter("birthday");
			String joinDate = req.getParameter("joiningdate");

			try {
				ps = con.prepareStatement("insert into employee_master values(?,?,?,?,?,?,?)");
				ps.setInt(1, 0);
				ps.setString(2, name);
				ps.setString(3, mail);
				ps.setString(4, desg);
				ps.setDouble(5, salary);
				ps.setString(6, birthdaydate);
				ps.setString(7, joinDate);

				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void checkEmp(String name) {
		try {
			ps = con.prepareStatement("select ");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
