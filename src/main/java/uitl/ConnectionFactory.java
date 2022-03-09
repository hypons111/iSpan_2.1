package uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static final Connection connection = createConnection();

	public static Connection getConnection() {
		return connection;
	}

	public static Connection createConnection() {
		String urlStr = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;user=sa;password=Passw0rd";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(urlStr);
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
