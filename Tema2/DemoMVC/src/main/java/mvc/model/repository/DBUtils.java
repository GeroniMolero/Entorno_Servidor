package mvc.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	
	private static final String CONN_URL = "jdbc:h2:mem:company";
	
	// Si quiéramos crear una base de datos "permanente" con un fichero en el disco duro haríamos:
	// private static final String CONN_URL = "jdbc:h2:file:~/db/company";
	// Esto crearía el fichero en C:\Users\YourUser\db\ o en el directorio HOME/YourUser/db

	private static Connection conn = null;

	private static final String CREATE_CUSTOMERS = "CREATE TABLE IF NOT EXISTS customers("
			+ "    customer_id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR( 255 ) NOT NULL,"
			+ "    address VARCHAR( 255 ), website VARCHAR(255)," + "    credit_limit DECIMAL(8, 2))";
	private static final String INSERT_CUSTOMERS = "INSERT INTO CUSTOMERS "
			+ "(NAME,ADDRESS,WEBSITE, CREDIT_LIMIT) values (?, ?, ?, ?)";

	public static Connection getConnection() throws RepositoryException {
		try {
			if (conn == null) {
				Class.forName("org.h2.Driver");
				conn = DriverManager.getConnection(CONN_URL);
				initialize();
			}
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	private DBUtils() {
	}

	private static void initialize() throws RepositoryException {
		try {
			var stm = conn.prepareStatement(CREATE_CUSTOMERS);
			stm.executeUpdate();

			if (customerTableIsEmpty(conn)) {
				stm = conn.prepareStatement(INSERT_CUSTOMERS);
				stm.setString(1, "Pepe");
				stm.setString(2, "Su casa");
				stm.setString(3, "http://pepephone.es");
				stm.setDouble(4, 2000);
				stm.executeUpdate();

				stm.setString(1, "Juan");
				stm.setString(2, "John's house");
				stm.setString(3, "http://johnnywalker.es");
				stm.setDouble(4, 2000);
				stm.executeUpdate();

				stm.setString(1, "Mary");
				stm.setString(2, "Mary's house");
				stm.setString(3, "http://maryhadalamb.es");
				stm.setDouble(4, 2000);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	private static boolean customerTableIsEmpty(Connection conn) throws SQLException {
		var stm = conn.createStatement();
		var rs = stm.executeQuery("SELECT COUNT(*) FROM CUSTOMERS");
		if(rs!=null && rs.next()) {
			int count = rs.getInt(1);
			return count == 0;
		}
		return true;
	}

	public static void closeConnection() throws RepositoryException {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	public static void close(Statement st) throws RepositoryException {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	public static void close(ResultSet rs) throws RepositoryException {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}
}