package be.brusselsbook.sql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import be.brusselsbook.sql.exception.DatabaseAccessException;

public class DatabaseAccess extends BoneCP {

	private static final long serialVersionUID = 1373811783278087243L;
	private static final String PROPRETIESFILE = "access.properties";
	private static final DatabaseProperties DATABASEPROPERTIES;

	private static class DatabaseProperties {
		private final String driverClass;
		private final String url;
		private final String user;
		private final String password;
		private final int minConnection;
		private final int maxConnection;
		private final int partitionCount;

		public DatabaseProperties(Properties properties) {
			this.driverClass = properties.getProperty("driverClass");
			this.url = properties.getProperty("url");
			this.user = properties.getProperty("user");
			this.password = properties.getProperty("password");
			this.minConnection = Integer.parseInt(properties.getProperty("minConnection"));
			this.maxConnection = Integer.parseInt(properties.getProperty("maxConnection"));
			this.partitionCount = Integer.parseInt(properties.getProperty("partitionCount"));
		}

		public String getDriverClass() {
			return driverClass;
		}

		public String getUrl() {
			return url;
		}

		public String getUser() {
			return user;
		}

		public String getPassword() {
			return password;
		}

		public int getMinConnection() {
			return minConnection;
		}

		public int getMaxConnection() {
			return maxConnection;
		}

		public int getPartitionCount() {
			return partitionCount;
		}

	}

	static {
		DATABASEPROPERTIES = createDefaultProperties();
		loadDriver(DATABASEPROPERTIES);
	}

	public static void loadDriver(DatabaseProperties properties) throws DatabaseAccessException {
		try {
			Class.forName(properties.getDriverClass());
		} catch (ClassNotFoundException e) {
			throw new DatabaseAccessException("Cannot load driver class '" + properties.getDriverClass() + "'.");
		}
	}

	private static DatabaseProperties createDefaultProperties() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(PROPRETIESFILE);
		if (fichierProperties == null) {
			throw new DatabaseAccessException("the properties file '" + PROPRETIESFILE + "' cannot be found.");
		}
		try {
			properties.load(fichierProperties);
		} catch (IOException e) {
			throw new DatabaseAccessException("Unable to load the properties file '" + PROPRETIESFILE + "'.");
		}
		return new DatabaseProperties(properties);
	}

	private static BoneCPConfig createDefaultConfig(DatabaseProperties properties) {
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(properties.getUrl());
		config.setUsername(properties.getUser());
		config.setPassword(properties.getPassword());
		config.setMinConnectionsPerPartition(properties.getMinConnection());
		config.setMaxConnectionsPerPartition(properties.getMaxConnection());
		config.setPartitionCount(properties.getPartitionCount());
		return config;
	}

	public DatabaseAccess() throws SQLException {
		this(DATABASEPROPERTIES);
	}

	public DatabaseAccess(DatabaseProperties properties) throws SQLException {
		this(createDefaultConfig(properties));
	}

	private DatabaseAccess(BoneCPConfig config) throws SQLException {
		super(config);
	}

}
