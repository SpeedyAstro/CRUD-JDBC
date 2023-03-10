package dbConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class DbConnection {
	public static Connection getConnection() throws SQLException, IOException {
		FileInputStream fis = new FileInputStream("src\\dbConnection\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		Connection con = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
		System.out.println("Connection Established!!");
		return con;
	}
	public static void cleanUp(Connection con,ResultSet resultset,Statement statement) throws SQLException {
		if(resultset!=null) resultset.close();
		if(statement!=null) statement.close();
		if(con!=null) con.close();
	}
}
