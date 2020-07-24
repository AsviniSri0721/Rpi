package model;

import java.sql.*;

public class QuestionDetails {
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

	public String insertQuestionDetails(String questionNo, String question, String answer) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into questiondetails (`questionNo`,`question`,`answer`)"
					+ " values (?,?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, questionNo);
			preparedStmt.setString(2, question);
			preparedStmt.setString(3, answer);
			
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newQuestionDetails  = readQuestionDetails();
			output = "{\"status\":\"success\", \"data\": \"" + newQuestionDetails + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting Question and Answers .\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

		public String readQuestionDetails() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr> <th>Question No</th> <th>Questions</th> <th>Answers</th> <th>Update</th><th>Remove</th> </tr>";
				String query = "select * from questiondetails ";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("id"));
					String questionNo = Integer.toString(rs.getInt("questionNo"));
					String question = rs.getString("question");
					String answer = rs.getString("answer");
					
					
					
					// Add into the html table
					output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + id + "'>"
					                 + questionNo + "</td>";
					output += "<td>" + question + "</td>";
					output += "<td>" + answer + "</td>";
					
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-questionid='"
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

		public String updateQuestionDetails(String id, String questionNo, String question, String answer) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE questiondetails SET questionNo=?,question=?,answer=? WHERE id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(questionNo));
				preparedStmt.setString(2, question);
				preparedStmt.setString(3, answer);
				
				
				preparedStmt.setInt(4, Integer.parseInt(id));

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newQuestionDetails = readQuestionDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newQuestionDetails + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Questions and Answers.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	}

		public String deleteQuestionDetails(String id) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from questiondetails where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newQuestionDetails = readQuestionDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newQuestionDetails + "\"}";
			} catch (Exception e) {
				output = "Error while deleting the card details.";
				System.err.println(e.getMessage());
			}
			return output;
	}
}