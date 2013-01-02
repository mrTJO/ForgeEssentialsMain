package com.ForgeEssentials.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConfig
{
	private static Logger _log = Logger.getLogger(DatabaseConfig.class.getName());
	
	private static final String DATABASE_CONFIG_FILE = "config/database.properties";
	public static final String HIBERNATE_CONFIG_FILE = "config/hibernate.properties";
	
	public static String DATABASE_PROVIDER;
	public static String DATABASE_DRIVER;
	public static String DATABASE_DIALECT;
	public static String DATABASE_URL;
	public static String DATABASE_LOGIN;
	public static String DATABASE_PASSWORD;
	
	public static int DATABASE_MAX_CONNECTIONS;
	public static int DATABASE_MAX_IDLE_TIME;
	
	public static void load()
	{
		try
		{
			Properties serverSettings = new Properties();
			serverSettings.load(new FileInputStream(DATABASE_CONFIG_FILE));
			
			DATABASE_PROVIDER = serverSettings.getProperty("dbProvider", "com.jolbox.bonecp.provider.BoneCPConnectionProvider");
			DATABASE_DRIVER = serverSettings.getProperty("dbDriver", "com.mysql.jdbc.Driver");
			DATABASE_DIALECT = serverSettings.getProperty("dbDialect", "org.hibernate.dialect.MySQL5Dialect");
			DATABASE_URL = serverSettings.getProperty("dbURL", "jdbc:mysql://localhost/minecraft");
			DATABASE_LOGIN = serverSettings.getProperty("dbLogin", "root");
			DATABASE_PASSWORD = serverSettings.getProperty("dbPassword", "");
			
			DATABASE_MAX_CONNECTIONS = Integer.parseInt(serverSettings.getProperty("MaximumDbConnections", "10"));
			DATABASE_MAX_IDLE_TIME = Integer.parseInt(serverSettings.getProperty("MaximumDbIdleTime", "0"));
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, "Cannot Read Server Settings - "+e.getMessage(), e);
		}
	}
	
	static
	{
		load();
	}
}
