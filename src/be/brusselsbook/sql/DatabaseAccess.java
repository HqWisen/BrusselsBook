package be.brusselsbook.sql;

import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DatabaseAccess extends BoneCP{
	
	private static final long serialVersionUID = 1373811783278087243L;
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/brusselsbook";
	private static final String DBUSER = "bbadmin";
	private static final String DBPWD = "common";
	private static final int MINCONNECTIONS = 5;
	private static final int MAXCONNECTIONS = 10;
	private static final int PARTITIONCOUNT = 2;

	static{
		loadDriver();
	}
	
	private static void loadDriver() {
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	private static BoneCPConfig createDefaultConfig() {
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(DBURL);
		config.setUsername(DBUSER);
		config.setPassword(DBPWD);
		config.setMinConnectionsPerPartition(MINCONNECTIONS);
		config.setMaxConnectionsPerPartition(MAXCONNECTIONS);
		config.setPartitionCount(PARTITIONCOUNT);
		return config;
	}
	
	public DatabaseAccess() throws SQLException{
		this(createDefaultConfig());
	}

	private DatabaseAccess(BoneCPConfig config) throws SQLException {
		super(config);
	}
	
}
