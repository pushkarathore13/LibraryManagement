package in.sp.redis;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisConnection {

	private static final Logger logger = LogManager.getLogger(RedisConnection.class);

	public static Jedis getConnection() {

		try {
			Properties prop = new Properties();

			InputStream input = RedisConnection.class.getClassLoader().getResourceAsStream("db.properties");

			prop.load(input);

			String host = prop.getProperty("redis.host", "localhost");
			int port = Integer.parseInt(prop.getProperty("redis.port", "6379"));

			return new Jedis(host, port);

		} catch (Exception e) {
			logger.warn("Redis not available", e);
			return null;
		}
	}
}