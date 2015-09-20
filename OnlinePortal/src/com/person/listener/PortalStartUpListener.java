package com.person.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class PortalStartUpListener implements ServletContextListener
{
	public static String JDBC_PROPERTY = "jdbc";

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		System.out.println("On Close");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		System.out.println("START UP");
		String userName = null;
		String passWord = null;
		String url = null;
		String driver = null;
		boolean isInfoCorrect = getJDBCInfo(driver, url, userName, passWord);
		if (true == isInfoCorrect)
		{
			boolean isConnectionSuccess = checkConnection(driver, userName,
					passWord, url);
		}
	}

	private boolean checkConnection(String driver ,String userName, String passWord, String url ) 
	{
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/MyLocalDB");

			con = ds.getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM employee");
			
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String name = rs.getString("name");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", name: " + name);
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				rs.close();
				stmt.close();
				con.close();
				ctx.close();
			} catch (SQLException e)
			{
				System.out.println("Exception in closing DB resources");
			} catch (NamingException e)
			{
				System.out.println("Exception in closing Context");
			}
			return true;
		}

		/*
		 * Connection conn = null; try { // STEP 2: Register JDBC driver
		 * Class.forName(driver);
		 * 
		 * // STEP 3: Open a connection
		 * System.out.println("Connecting to database..."); conn =
		 * DriverManager.getConnection(url, userName, passWord);
		 * System.out.println("Got Connection in listtener"); return true; }
		 * catch(ClassNotFoundException ex) { return false; } catch(SQLException
		 * e) { return false; }
		 */}

	private boolean getJDBCInfo(String driverName, String url, String userName,
			String passWord)
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle(JDBC_PROPERTY);
		if (null != resourceBundle)
		{
			driverName = resourceBundle.getString("online.portal.driver.name");
			url = resourceBundle.getString("online.portal.database.url");
			userName = resourceBundle.getString("online.portal.user.name");
			passWord = resourceBundle.getString("online.portal.user.password");
			return true;
		} else
		{
			return false;
		}
	}

}
