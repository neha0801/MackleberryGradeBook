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
	private static int reportType=0;
	
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

	private void addToDatabase(int studentId, String assign, String aType,String sDate, int grade) {

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
			String insertQuery = "insert into StrongheimGradebook values ("+studentId + ",'" + assign + "','"
				+ aType+"', To_Date('"+sDate +"','mm/dd/yyyy'),"+ grade + " )";
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
		String assignment,aType="",sDate;
		String tempStr;
		int studentId =0, grade = 0;
		
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
		tempStr = (String) request.getParameter("type");
		if (tempStr == null) {
			errorMessages
					.add("Assignment type parameter is missing (check input form!)");
		}else if(tempStr.equalsIgnoreCase("quiz") || tempStr.equalsIgnoreCase("Homework")
				||tempStr.equalsIgnoreCase("Project") || tempStr.equalsIgnoreCase("test")) {
			aType=tempStr;
		}
		else {
			tempStr = tempStr.trim();
			if (tempStr.length() == 0) {
				errorMessages.add("Assignment type is empty...");
			}
		}
		sDate = (String) request.getParameter("sDate");
		if (sDate == null) {
			errorMessages
					.add("Submission Date parameter is missing (check input form!)");
		} else {
			sDate = sDate.trim();
			if (sDate.length() == 0) {
				errorMessages.add("Submission Date is empty...");
			}
		}

		tempStr = (String) request.getParameter("studentId");
		if (tempStr == null) {
			errorMessages.add("Student Id parameter is missing (check input form!)");
		} else {
			try {
				studentId = Integer.parseInt(tempStr);
			} catch (NumberFormatException ex) {
				errorMessages.add("Student Id must be a number");
			}
		}
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
		if (grade>100 || grade<0) {
			errorMessages.add("Grade must between 0 and 100");
		}
		// do we have any errors?
		if (errorMessages.size() != 0) {
			showErrorMessage(errorMessages, response);
		} else {
			addToDatabase(studentId, assignment, aType, sDate,  grade);
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
		out.println("<a href='GradeBook.jsp'>go back and correct your input please...</a>");
		out.println("</body></html>");
		out.close();

	}

}
