import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

	public void connect() {
		Connection connection;
		try {
			// Название драйвера
			String driverName = "com.mysql.jdbc.Driver"; 

			Class.forName(driverName);

			// Create a connection to the database
			String serverName = "localhost";
			String mydatabase = "wp";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root";
			String password = "vKgq5cFJ";

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("is connect to DB" + connection);

			String query = "Select * FROM world";
			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			String dbtime;
			while (rs.next()) {
				dbtime = rs.getString(1);
				System.out.println(dbtime);
			} // end while

			connection.close();
		} // end try
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Could not find the database driver
		} catch (SQLException e) {
			e.printStackTrace();
			// Could not connect to the database
		}
	}
}