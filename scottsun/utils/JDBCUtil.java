package scottsun.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Customized JDBC Tools class 
 *  	1. load driver class
 * 	 	2. get connection class
 * 	 	3. close connection
 * @author Scott Sun
 *
 */
public class JDBCUtil {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	private static InputStream in = null;

	//static code block executes codes within the block when the class file is being loaded to the memory. 
	// we use this characteristic to to load db.propertyies when class file is loaded

	static {
		try {
			// 1. instantiate properties class
			Properties props = new Properties();

			// read db.properties file located at the root class in Tomcat 
			in = JDBCUtil.class.getResourceAsStream("/db.properties");

			// 2. load db.properties
			props.load(in);

			// 3. get information from the db.properties
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			driverClass = props.getProperty("driver");

			// 4. load driverclass
			Class.forName(driverClass);

		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver failed to load");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Driver failed to load");
		} finally {
			if (in != null) {
				try {
					// 4. close resource
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * get database connection object
	 * 
	 * @return Connection connection to the database
	 */
	public static Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * close connection to the database, release statement resource 
	 * 
	 * @param conn Connection object to the database
	 * @param st   Statement object
	 */
	public static void close(Connection conn, Statement st) {
		try {
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// RuntimeException for user experience when encounter SQLException
			throw new RuntimeException(e);
		}
	}

	/**
	 * Close resource when there is a ResultSet
	 * 
	 * @param conn
	 * @param st
	 * @param set
	 */
	public static void close(Connection conn, Statement st, ResultSet set) {
		try {
			if (st != null) {
				st.close();
			}
			if (set != null) {
				set.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// RuntimeException for user experience when encounter SQLException
			throw new RuntimeException(e);
		}
	}
}
