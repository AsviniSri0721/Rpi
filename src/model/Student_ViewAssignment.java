package model;

import java.sql.*;

public class Student_ViewAssignment {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wiley", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	

		public String readAssignmentDetails() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr> <th>Assignments</th><th>Start Attempt</th> </tr>";
				String query = "select * from assignmentdetails";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("id"));
					String assignmentName = rs.getString("assignmentName");
					
					
					// Add into the html table
					output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + id + "'>"
					                 + assignmentName + "</td>";
					
					// buttons
					output += "<td><a href='http://localhost:8080/Wiley/Student_ViewQuestions.jsp' name='btnUpdate'   class='btnUpdate btn btn-secondary'>Start Assignment</a></td>"
							 + "</td></tr>";

				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the card details.";
				System.err.println(e.getMessage());
			}
			return output;
	}

		
}