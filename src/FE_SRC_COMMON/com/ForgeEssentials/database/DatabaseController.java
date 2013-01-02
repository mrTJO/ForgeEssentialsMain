package com.ForgeEssentials.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ForgeEssentials.database.table.BaseTable;
import com.ForgeEssentials.database.table.GroupConnectorTable;
import com.ForgeEssentials.database.table.GroupTable;
import com.ForgeEssentials.database.table.LadderNameTable;
import com.ForgeEssentials.database.table.LadderTable;
import com.ForgeEssentials.database.table.PermissionTable;
import com.ForgeEssentials.database.table.PlayerTable;
import com.ForgeEssentials.database.table.ZoneTable;

public class DatabaseController
{
private static final Logger _log = Logger.getLogger(DatabaseController.class.getName());
	
	private SessionFactory _sessionFactory;
	
	private DatabaseController()
	{
		init();
	}
	
	public static DatabaseController getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private void init()
	{
		Configuration conf = new Configuration();

		conf.addAnnotatedClass(ZoneTable.class);
		conf.addAnnotatedClass(GroupTable.class);
		conf.addAnnotatedClass(LadderNameTable.class);
		conf.addAnnotatedClass(PlayerTable.class);
		conf.addAnnotatedClass(PermissionTable.class);
		conf.addAnnotatedClass(GroupConnectorTable.class);
		conf.addAnnotatedClass(LadderTable.class);
		
		conf.setProperty("hibernate.connection.provider_class", DatabaseConfig.DATABASE_PROVIDER);
		conf.setProperty("hibernate.connection.driver_class", DatabaseConfig.DATABASE_DRIVER);
		conf.setProperty("hibernate.dialect", DatabaseConfig.DATABASE_DIALECT);
		conf.setProperty("hibernate.connection.url", DatabaseConfig.DATABASE_URL);
		conf.setProperty("hibernate.connection.username", DatabaseConfig.DATABASE_LOGIN);
		conf.setProperty("hibernate.connection.password", DatabaseConfig.DATABASE_PASSWORD);
		
		try
		{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(DatabaseConfig.HIBERNATE_CONFIG_FILE);
			prop.load(fis);
			conf.addProperties(prop);
			fis.close();
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, "Failed to load hibernate.properties", e);
		}
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		
		_sessionFactory = conf.buildSessionFactory(serviceRegistry);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> executeSelect(Class<T> table, Criterion... conditions)
	{
		Session session = _sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria query = session.createCriteria(table);
		for (Criterion cond : conditions)
		{
			query.add(cond);
		}
		
		List<T> lst = query.list();
		
		session.close();
		return lst;
	}
	
	public boolean executeInsert(BaseTable table)
	{
		try
		{
			Session session = _sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(table);
			
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Error in Database Insert: " + e.getMessage(), e);
		}
		return false;
	}
	
	public boolean executeUpdateSingle(BaseTable table)
	{
		try
		{
			Session session = _sessionFactory.openSession();
			session.beginTransaction();
			
			session.update(table);
			
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Error in Database Update: " + e.getMessage(), e);
		}
		return false;
	}
	
	private static class SingletonHolder
	{
		protected static final DatabaseController _instance = new DatabaseController();
	}
}
