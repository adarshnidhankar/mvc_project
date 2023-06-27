package Emp_data;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/search_id_new")
public class By_id extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("empid");
		PrintWriter pw = resp.getWriter();
		String str = "%D %M %Y";
		try {
			ps = con.prepareStatement("select emp_id,emp_name,emp_desig,salary,date_format(birthdate,'" + str
					+ "'),date_format(joining_date,'" + str + "') from employee where emp_id = ?");
			
			ps.setString(1, id);

			rs = ps.executeQuery();

			pw.print("<table border='1 solid'>");
			pw.print("<tr>");
			pw.print("<th> Employee Id          </th>");
			pw.print("<th> Employee Name        </th>");
			pw.print("<th> Employee Designation </th>");
			pw.print("<th> Employee Salary      </th>");
			pw.print("<th> Employee Birthday    </th>");
			pw.print("<th> Employee Joining Date</th>");
			pw.print("</tr>");

			while (rs.next()) {
				pw.print("<tr>");
				pw.print("<td>" + rs.getInt(1) + "</td>");
				pw.print("<td>" + rs.getString(2) + "</td>");
				pw.print("<td>" + rs.getString(3) + "</td>");
				pw.print("<td>" + rs.getDouble(4) + "</td>");
				pw.print("<td>" + rs.getString(5) + "</td>");
				pw.print("<td>" + rs.getString(6) + "</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
