package model;

import java.sql.*;

public class StudentAnswers{
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

	public String insertStudentAnswers(String stuAnswer) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into studentanswer (`id`,`stuAnswer`)"
					+ " values (?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, stuAnswer);
			
			
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newStudentAnswers  = readStudentAnswers();
			output = "{\"status\":\"success\", \"data\": \"" + newStudentAnswers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting Question and Answers .\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

		public String readStudentAnswers() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr> <th>Answers</th> <th>Update</th> </tr>";
				String query = "select * from studentanswer";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("id"));
					String stuAnswer = rs.getString("stuAnswer");
					
					
					
					// Add into the html table
					output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + id + "'>"
					                 + stuAnswer + "</td>";
					
					
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
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

		public String updateStudentAnswers(String id, String stuAnswer) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE studentanswer SET stuAnswer=? WHERE id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, stuAnswer);
				
				
				
				preparedStmt.setInt(2, Integer.parseInt(id));

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newStudentAnswers = readStudentAnswers();
				output = "{\"status\":\"success\", \"data\": \"" + newStudentAnswers + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Answers.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	}

		
}