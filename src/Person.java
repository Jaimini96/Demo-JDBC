
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Person {


	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
	
		
//TODO LOAD JDBC PROPERTIES FROM FILE SYSTEM		
		Properties props = new Properties();
		
		FileInputStream fIn = new FileInputStream("dbDetails.properties");
		props.load(fIn);
		
		
//TODO GET DATABASE CONNECTION USING 'JDBC URL'
		
		String url = props.getProperty("jdbc.url");
				
		Connection dbConnection;
				
		dbConnection = DriverManager.getConnection(url);
				
		System.out.println("Connection successful? "+ (dbConnection!=null));
			
		
		String deleteQuery = props.getProperty("jdbc.query.delete");
		try(Statement createStatement = dbConnection.createStatement()){
			
			createStatement.executeUpdate(deleteQuery);

			/*String msg = "random message";
			insertStatement.setString(1, msg);
			insertStatement.executeUpdate();*/
		}
		String createQuery = props.getProperty("jdbc.query.create");
		try(Statement createStatement = dbConnection.createStatement()){
			
			createStatement.executeUpdate(createQuery);

			/*String msg = "random message";
			insertStatement.setString(1, msg);
			insertStatement.executeUpdate();*/
		}
		

		String insertQuery = props.getProperty("jdbc.query.insert");
		try(java.sql.PreparedStatement insertStatement = dbConnection.prepareStatement(insertQuery)){
			
			String msg = "Abhishek";
			int msg1 = 20;

			insertStatement.setString(1, msg);
			insertStatement.setInt(2, msg1);

			insertStatement.executeUpdate();
		}
		
		try(Statement selectStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)){
			String selectQuery = props.getProperty("jdbc.query.select");
			
			ResultSet result;
			result = selectStatement.executeQuery(selectQuery);
			
			while(result.next()){
				String message = result.getString(1);
				int age = result.getInt(2);

				System.out.println(message+"  "+age);
			}
			result.beforeFirst();
			while(result.next()){
				String message = "Jaimini";
				int age = 29;
				result.updateString(1, message);
				result.updateDouble(2, age);
		        result.updateRow();

				System.out.println(message+"  "+age);
			}
			result.beforeFirst();

			while(result.next()){
				String message = result.getString(1);
				int age = result.getInt(2);

				System.out.println(message+"  "+age);
			}
			

			
		}
		
		
	}	
}
