import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;
public class Entry {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
	
		
//TODO LOAD JDBC PROPERTIES FROM FILE SYSTEM		
		Properties props = new Properties();
		
		FileInputStream fIn = new FileInputStream("dbDetails.properties");
		props.load(fIn);
		
		
		
		
		
		
		
		
		
		
//TODO LOAD AND REGISTER 'JDBC DRIVER'
		
		//String driver = props.getProperty("jdbc.driver");
		//Class.forName(driver);
		
		
		
//TODO GET DATABASE CONNECTION USING 'JDBC URL'
		
		String url = props.getProperty("jdbc.url");
		
		Connection dbConnection;
		
		dbConnection = DriverManager.getConnection(url);
		
		System.out.println("Connection successful? "+ (dbConnection!=null));
		
		//Statement insertStatement = null;
		
	/*try{
		insertStatement=dbConnection.createStatement();
		
		String insertQuery = props.getProperty("jdbc.query.insert");
		
		int rows;
		rows = insertStatement.executeUpdate(insertQuery);
		
		System.out.println(rows+ " record is(are) added successfully");
	}finally{
			
			if(insertStatement !=null)
				insertStatement.close();
			
		}*/
	
	
	try(Statement selectStatement = dbConnection.createStatement()){
		String selectQuery = props.getProperty("jdbc.query.select");
		
		ResultSet result;
		result = selectStatement.executeQuery(selectQuery);
		
		while(result.next()){
			String message = result.getString(1);
			System.out.println(message+" yolo");
		}
				
	}
		String insertQuery = props.getProperty("jdbc.query.insert");

	try(java.sql.PreparedStatement insertStatement = dbConnection.prepareStatement(insertQuery)){
		
		String msg = "random message";
		insertStatement.setString(1, msg);
		insertStatement.executeUpdate();
	
	
	}


		
	}
	
	
	
}
