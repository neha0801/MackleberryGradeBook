

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GradeRecord
 */
@WebServlet("/GradeRecord")
public class GradeRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Statement st;
	private static Connection con ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getFromDatabase(request) ;
		
		getServletContext().getRequestDispatcher("/GradeRecord.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//getFromDatabase(request) ;
		if(request.getParameter("hiddenAction").equals("average")){
			String avg = getAverage();
			avg+= "<div class='form-group'><div style='text-align: center'><div class='col-sm-offset-2 col-sm-10'>" +
					"<p><a href='GradeBook' class='btn pull-right btn-primary btn-lg' role='button'>Go To Home Page</a>"+
					"</p></div></div></div>";
			request.setAttribute("message", avg);
			getServletContext().getRequestDispatcher("/GradeRecord.jsp").forward(
					request, response);
		}

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
	
	private void getFromDatabase(HttpServletRequest request) {
		
		String message="";
		ResultSet rs=null;
		try {
			openConnection();		
			String sql = "Select * from GradeBook";
			// let's execute
			rs = st.executeQuery(sql);
			message+="<br></br>";
			message += "<table border=2 width = 50% background-color:Light grey>"; 
			message += "<tr><td><b> Assignment</b> </td><td> <b>Grade</b> </td><tr>";
			while (rs.next()) {
				message += ("<tr><td>" + rs.getString(1) + "</td><td>" +rs.getInt(2) + "</td></tr>");
			}
			// that's all folks...
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		message+="<form action='GradeRecord' method='post'> " +
				"<input type='hidden' value='average' name='hiddenAction'></input>"+
				"<input type='submit' value='Calculate Average Grade' name = 'average'></input></form>";
		
		request.setAttribute("message", message);
	}
	private String getAverage() {
		
		String avg="";
		ResultSet rs=null;
		openConnection();
		String sql = "Select avg(grade) as average from GradeBook";
		// let's execute
		try{
			rs = st.executeQuery(sql);
			avg+="<br></br>";
			avg += "<p>Average Grade <p>";
			if (rs.next()) {
				avg += ("<p><b>" + rs.getString(1) + "</b></p>");
			}
			// that's all folks...
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return avg;
	}
	

}
