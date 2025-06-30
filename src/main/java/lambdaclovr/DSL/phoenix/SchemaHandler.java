package lambdaclovr.dsl.phoenix;

import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Class Name: Services</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 * @Description:
 *               </p>
 * 
 *               <p>
 * @Description: This class is used to perform basic schema level operations
 *               such as schema, table creation etc.The access level is system
 *               administrator
 * 
 *               </p>
 * 
 * @author Numan Khan <Numan@dke.khu.ac.kr>
 * 
 * @version 1.0
 * @since 2024-07-24
 **/

public class SchemaHandler extends PhoenixConnection {

	private String query = "";
	
	
	///////////////////////////////////////////////////////////////////////	
	/**
	 * This method is used to create new schema if not exist.
	 * 
	 * @param void
	 * @return void
	 */

	public void createSchema(String schemaName) throws SQLException {
		statement = connection.createStatement();

		String query = "CREATE SCHEMA IF NOT EXISTS " + schemaName;

		statement.executeUpdate(query);
		connection.commit();
		System.out.println(schemaName + " schema created successfully.");
	}

	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to drop schema if exists.
	 * 
	 * @param void
	 * @return void
	 */

	public void dropSchema(String schemaName) throws SQLException {
		statement = connection.createStatement();

		query = "DROP SCHEMA IF EXISTS " + schemaName;

		statement.executeUpdate(query);				
		connection.commit();
		
		System.out.println("Schema deleted successfully.");
	}

	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create new table in the schema if not exists.
	 * 
	 * @param void
	 * @return void
	 */
	public void createTable(String schemaName, String tableName, String columnNames, String primaryKeyName, String primaryKeyField) throws SQLException {

		statement = connection.createStatement();

		query = "CREATE TABLE IF NOT EXISTS " 
						+ schemaName 
						+ "." + tableName 
						+ "(" + columnNames 
						+ "CONSTRAINT " 
						+ primaryKeyName
						+ " PRIMARY KEY ("
						+ primaryKeyField
						+ "))";

		
		System.out.println(query);
		statement.executeUpdate(query);
		System.out.println(schemaName + "." + tableName + " table created successfully.");
		
		//Monotonically increasing numbers used to form an ID for each table. 
		//Need some error handler for ifexist 
		createSequence(schemaName, primaryKeyField);
		
		connection.commit();
		System.out.println("Table created successfully. \n");

	}


	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create new table in the schema if not exists.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void dropTable(String schemaName, String tableName, String primaryKeyField) throws SQLException {
		
		statement = connection.createStatement();
		query = "DROP TABLE IF EXISTS " + schemaName + "." + tableName; 
		
		System.out.println(query);
		statement.executeUpdate(query);
		System.out.println(schemaName + " table droped successfully. \n");
		
		//Drop sequence from the schema.
		dropSequence(schemaName, primaryKeyField);
		
		connection.commit();
		
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create new sequence table in the schema if not exists.
	 * 
	 * @param void
	 * @return void
	 */
	public void createSequence(String schemaName, String primaryKeyField) throws SQLException
	{
		statement = connection.createStatement();
		
		query = "CREATE SEQUENCE " + schemaName + "." + primaryKeyField;
		
		System.out.println(query);
		
		statement.executeUpdate(query);
		connection.commit();
		
		System.out.println(schemaName + "." + primaryKeyField  + " sequence created successfully.");
		
	}
	

	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to drop a sequence table from the schema if not exists.
	 * 
	 * @param void
	 * @return void
	 */
	public void dropSequence(String schemaName, String primaryKeyField) throws SQLException
	{
		statement = connection.createStatement();
		
		query = "DROP SEQUENCE IF EXISTS " + schemaName + "." + primaryKeyField;
		
		System.out.println(query);
		
		statement.executeUpdate(query);
		connection.commit();
		
		System.out.println(schemaName + "." + primaryKeyField + " sequence droped successfully.");
		
	}


	public void listTable() {

	}


}
