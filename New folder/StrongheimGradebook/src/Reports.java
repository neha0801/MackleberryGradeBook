import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reports
 */
@WebServlet("/Reports")
public class Reports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Statement st;
	private static Connection con ;
	private static ArrayList<String> errorMessages = new ArrayList<String>();
	private static int studentId=0;
	private static String aType="";
	private static String reportType="";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reports() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		reportType=request.getParameter("reportType");
		getServletContext().getRequestDispatcher("/Reports.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	private void openConnection(){
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, props);
			st = con.createStatement();
		} catch (SQLException|ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connection established successfully...!!");
	
	}
	

	private String getReports(int id, String type, String reportType){
		openConnection();
		String sql="",message="";
		ResultSet rs=null;
		switch (reportType){
			case "A":	
				sql ="Select assignment from StrongheimGradebook where STUDENTID =" +id;
				break;
			case "B": 
				sql ="Select assignment from StrongheimGradebook where TYPE = '" +type +"'";
				break;
			case "C":
				sql ="Select assignment from StrongheimGradebook where TYPE = '" +type +"' and studentId = " + id;
				break;
			case "D":
				sql ="Select avg(grade) from StrongheimGradebook where STUDENTID =" +id;
				break;
			case "E":
				sql ="Select avg(grade) from StrongheimGradebook  where studentId = " + id + " group by type";
				break;
			case "F":
				sql="select max(grade), min(grade) from StrongheimGradebook where type= '" + type +"'" ;
				break;
		}
		System.out.println(sql);
		try{
			if(reportType.equalsIgnoreCase("F")){
				rs = st.executeQuery(sql);
				message+="<br></br>";			
				if (rs.next()) {
					message += "Highest Score:" + ("<p><b>" + rs.getString(1) + "</b></p>") + "\n";
					message+= "Lowest Score:" + ("<p><b>" + rs.getString(2) + "</b></p>") + "\n";
				}
			}else{
				rs = st.executeQuery(sql);
				message+="<br></br>";			
				while (rs.next()) {
					message += ("<p><b>" + rs.getString(1) + "</b></p>");
				}
			}
			// that's all folks...
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return message;
	}
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String message="";
		
		
		String tempStr="";
				
		tempStr = request.getParameter("assignType");
		if(tempStr.equalsIgnoreCase("")){
			aType="quiz";
		}else if (tempStr.equalsIgnoreCase("quiz") || tempStr.equalsIgnoreCase("Homework")
				||tempStr.equalsIgnoreCase("Project") || tempStr.equalsIgnoreCase("test")) {
			aType=tempStr;		
		}else {
			errorMessages.add("Assignment Type parameter is incorrect!");
		}
		tempStr = request.getParameter("studentId");
		if(tempStr==null){
			studentId=0;
		}else{
			try {
					studentId = Integer.parseInt(tempStr);
				} catch (NumberFormatException ex) {
					errorMessages.add("Student Id must be a number");
				}
		}
		// do we have any errors?
		if (errorMessages.size() != 0) {
			showErrorMessage(errorMessages, response);
		} else {
			switch (reportType){
			case "A":	
				message = "Report A: Assignment Names\n" ;
				break;
			case "B": 
				message = "Report B: Assignment Names\n" ;
				break;
			case "C":
				message = "Report C: Assignment Names\n" ;
				break;
			case "D":
				message = "Report D: Average Grade for selected student \n" ;
				break;
			case "E":
				message = "Report E: Average Grade for selected student according to assignment Type\n" ;
				break;
			case "F":
				message = "Report F: Highest and lowest grade for a particular assignment type \n" ;
				break;
		}
			
			message+=getReports(studentId, aType, reportType);
			
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/FinalReports.jsp").forward(
					request, response);
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
