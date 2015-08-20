import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class GradeBook
 */
@WebServlet("/GradeBook")
public class GradeBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GradeBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/GradeBook.jsp").forward(
				request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		getServletContext().getRequestDispatcher("/GradeBook.jsp").forward(
				request, response);
	}

	private void addToDatabase(String assign, int grade) {

		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, props);
			Statement st = con.createStatement();
			System.out.println("connection established successfully...!!");
			// let's prepare SQL query: 'insert into...'
			String insertQuery = "insert into GradeBook values ( '" + assign + "', "
					+ grade + " )";
			// print it for debug (appears in server log files)
			System.out.println("sql:" + insertQuery);
			// let's execute
			st.executeQuery(insertQuery);
			// that's all folks...
			con.close();
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> errorMessages = new ArrayList<String>();
		String assignment;
		String tempStr;
		int grade = 0;

		// basic validation
		// for country name
		assignment = (String) request.getParameter("assign");
		if (assignment == null) {
			errorMessages
					.add("Assignment parameter is missing (check input form!)");
		} else {
			assignment = assignment.trim();
			if (assignment.length() == 0) {
				errorMessages.add("Assignment is empty...");
			}
		}

		// and for population
		tempStr = (String) request.getParameter("grade");
		if (tempStr == null) {
			errorMessages.add("Grade parameter is missing (check input form!)");
		} else {
			try {
				grade = Integer.parseInt(tempStr);
			} catch (NumberFormatException ex) {
				errorMessages.add("Grade must be a number");
			}
		}

		// do we have any errors?
		if (errorMessages.size() != 0) {
			showErrorMessage(errorMessages, response);
		} else {
			addToDatabase(assignment, grade);
		}
	}

	private void showErrorMessage(ArrayList<String> errMsgList,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Your request is very important for us, but:<br>");
		for (int i = 0; i < errMsgList.size(); i++) {
			out.println("<li>" + errMsgList.get(i));
		}
		out.println("<br><br>");
		out.println("<a href='GradeBook.jsp'>go back and correct you input please...</a>");
		out.println("</body></html>");
		out.close();

	}

}
