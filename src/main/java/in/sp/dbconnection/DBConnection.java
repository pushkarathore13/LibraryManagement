package in.sp.dbconnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {

	private static final Logger logger = LogManager.getLogger(DBConnection.class);

	private static DBConnection instance = new DBConnection(); // one obj of dbconnection ie singleton obj

	private Connection con;

	private DBConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Properties prop = new Properties();

			InputStream input = getClass().getClassLoader() .getResourceAsStream("db.properties");

			prop.load(input); // load file content to memory

			String url = prop.getProperty("db.url");
			String username = prop.getProperty("db.username");
			String password = prop.getProperty("db.password");

			con = DriverManager.getConnection(url, username, password);

			logger.info("Database connected successfully");

		} catch (Exception e) {
			logger.error("Database connection failed", e);
		}
	}

	public static DBConnection getInstance() {
		return instance;
	}

	public Connection getConnection() {
		return con;
	}
}