package model;

import java.sql.*;

public class AssignmentDetails {
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

	public String insertAssignmentDetails(String id,String assignmentName) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into assignmentdetails (`id`,`assignmentName`)"
					+ " values (?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, assignmentName);
			
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newAssignmentDetails  = readAssignmentDetails();
			output = "{\"status\":\"success\", \"data\": \"" + newAssignmentDetails + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting assignment details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

		public String readAssignmentDetails() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Assignments No</th> <th>Assignments</th><th>Update</th><th>Remove</th> </tr>";
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
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
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

		public String updateAssignmentDetails(String id, String assignmentName) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE assignmentdetails SET assignmentNo=?,assignmentName=? WHERE id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, assignmentName);
				
				preparedStmt.setInt(2, Integer.parseInt(id));

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newAssignmentDetails = readAssignmentDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newAssignmentDetails + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the assignment details.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	}

		public String deleteAssignmentDetails(String id) {
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
				String newAssignmentDetails = readAssignmentDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newAssignmentDetails + "\"}";
			} catch (Exception e) {
				output = "Error while deleting the card details.";
				System.err.println(e.getMessage());
			}
			return output;
	}
}