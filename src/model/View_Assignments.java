package model;

import java.sql.*;

public class View_Assignments{
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

	

		public String readView_Assignments() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Assignments No</th> <th>Assignments</th><th>Create Questions</th><th>Remove</th> </tr>";
				String query = "select * from assignmentdetails";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("id"));
					String assignmentName = rs.getString("assignmentName");
					
					
					// Add into the html table
					output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + id + "'>"
			                 + id + "</td>";
			output += "<td>" + assignmentName + "</td>";
					
					// buttons
					output += "<td><a href='http://localhost:8080/Wiley/Create_Question.jsp' name='btnUpdate'   class='btnUpdate btn btn-secondary'>Create Questions</a></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-assignmentid='"
							+ id + "'>" + "</td></tr>";

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

		
		public String deleteView_Assignments(String id) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from assignmentdetails where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newView_Assignments = readView_Assignments();
				output = "{\"status\":\"success\", \"data\": \"" + newView_Assignments + "\"}";
			} catch (Exception e) {
				output = "Error while deleting the card details.";
				System.err.println(e.getMessage());
			}
			return output;
	}
}