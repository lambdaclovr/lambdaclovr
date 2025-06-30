package lambdaclovr.dsl.phoenix;

import java.sql.SQLException;

/**
 * <h1>LAMBDA-CLOVR Project</h1> <h2>Layer: Data Storage Layer</h2> <h3>Class Name:
 * Services</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 * @Description: </p>
 * 
 *               <p>
 * @Description: This class is used to create LAMBDA-CLOVR Model (Phoenix HBase). The access level
 *               is system administrator
 * 
 *               </p>
 * 
 *
 * @author Numan Khan <Numan@dke.khu.ac.kr> 
 * 
 * @version 1.0
 * @since 2024-07-24
 **/

public class ModelHandler extends SchemaHandler {
	private static String schemaName = "LAMBDA-CLOVR";
	private String tableName = "";
	private String columnNames = "";
	private String primaryKeyName = "";
	private String primaryKeyField = "";
	private SchemaHandler schemaHandler = new SchemaHandler();
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.Users table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createUserTable() throws SQLException {
		tableName = "USER";
		columnNames = "user_id BIGINT NOT NULL, " 
						+ "user_name VARCHAR, "  
						+ "first_name VARCHAR, " 
						+ "last_name VARCHAR, "
						+ "password VARCHAR, "
						+ "email VARCHAR, "
						+ "user_role TINYINT, "
						+ "date_of_birth DATE, " 
						+ "gender TINYINT, " 
						+ "display_picture VARCHAR, " 
						+ "creation_date DATE, " 
						+ "remember_me BOOLEAN, ";
		primaryKeyName = "user_id_pk";
		primaryKeyField = "user_id"; //Also sequence name for this table
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
		
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.VENDOR table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createVendorTable() throws SQLException {
		tableName = "VENDOR";
		columnNames = "vendor_id INTEGER NOT NULL, "
						+ "vendor_name VARCHAR, ";
		
		primaryKeyName = "vendor_id_pk";
		
		//Also used as a sequence name
		primaryKeyField = "vendor_id"; 
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
		
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.VENDOR_MODEL table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createVendorModelTable() throws SQLException {
		tableName = "VENDOR_MODEL";
		columnNames = "vendor_model_id INTEGER NOT NULL, "
						+ "vendor_id INTEGER, "
						+ "camera_model VARCHAR, "
						+ "description VARCHAR, " ;
		
		primaryKeyName = "vendor_model_id_pk";
		
		primaryKeyField = "vendor_model_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
		
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.DATA_SOURCE table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createDataSourceTable() throws SQLException {
		tableName = "DATA_SOURCE";
		columnNames = "ds_id BIGINT NOT NULL, "
						+ "user_id BIGINT, "
						+ "vendor_model_id INTEGER, "
						+ "dstype_id BIGINT, "
						+ "ds_name VARCHAR, "
						+ "description VARCHAR, "
						+ "camera_username VARCHAR, "
						+ "camera_password VARCHAR, "
						+ "camera_port VARCHAR, "
						+ "camera_link VARCHAR, "
						+ "camera_snapshot VARCHAR, " 
						+ "creation_date DATE, ";
		
		primaryKeyName = "ds_id_pk";
		
		primaryKeyField = "ds_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
		
	}

	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.DS_TYPE table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createDsTypeTable() throws SQLException {
		tableName = "DS_TYPE";
		columnNames = "dstype_id BIGINT NOT NULL, "
						+ "name VARCHAR, "
						+ "description VARCHAR, ";
		
		primaryKeyName = "dstype_pk";
		primaryKeyField = "dstype_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
		
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.VIDEO table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createVideoTable() throws SQLException {
		tableName = "VIDEO";
		columnNames = "video_id BIGINT NOT NULL, "
						+ "ds_id BIGINT, "
						+ "name VARCHAR, "
						+ "size INTEGER, "
						+ "format VARCHAR, "
						+ "tags VARCHAR, "
						+ "snapshot VARCHAR, "
						+ "creation_date DATE, ";
		
		primaryKeyName = "video_id_pk";
		primaryKeyField = "video_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);

	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.SERVICE table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createServiceTable() throws SQLException {
		tableName = "SERVICE";
		columnNames = "service_id BIGINT NOT NULL, "
						+ "user_id BIGINT, "
						+ "dstype_id BIGINT, "
						+ "service_type_id INTEGER, "
						+ "service_name VARCHAR, "
						+ "description VARCHAR, "
						+ "creation_date DATE, ";
		
		primaryKeyName = "service_id_pk";
		primaryKeyField = "service_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.SERVICE_SUBSCRIPTION table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createServiceSubscriptionTable() throws SQLException {
		tableName = "SERVICE_SUBSCRIPTION";
		columnNames = "ss_id BIGINT NOT NULL, "
						+ "service_id BIGINT, "
						+ "ds_id BIGINT, "
						+ "status TINYINT, "
						+ "creation_date DATE, "
						+ "subscription_start_date DATE, "
						+ "subscription_stop_date DATE, ";
		
		primaryKeyName = "service_subscription_pk";
		primaryKeyField = "ss_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.ALGORITHM table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createAlgorithmTable() throws SQLException {
		tableName = "ALGORITHM";
		columnNames = "algo_id BIGINT NOT NULL, "
						+ "name VARCHAR, "
						+ "algorithm_id VARCHAR, "
						+ "resources VARCHAR, "
						
						+ "creation_date DATE, "
						
						+ "input_type_id  BIGINT, "
						+ "user_id BIGINT, "
						+ "sc_id INTEGER, ";
		
	
		primaryKeyName = "algorithm_pk";
		primaryKeyField = "algo_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.SouceCode table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createSourceCodeTable() throws SQLException {
		tableName = "SOURCE_CODE";
		columnNames = "sc_id INTEGER NOT NULL, "
						+ "source_type VARCHAR, "
						+ "license VARCHAR, ";
		
		primaryKeyName = "sc_id_pk";
		primaryKeyField = "sc_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}	
	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.SERVICE_ALGORITHM table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createServiceAlgorithmTable() throws SQLException {
		tableName = "SERVICE_ALGORITHM";
		columnNames = "service_algo_id BIGINT, "
						+ "algo_id BIGINT, "
						+ "creation_date DATE, ";
		
		primaryKeyName = "service_algo_pk";
		primaryKeyField = "service_algo_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}

	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.SERVICE_ALGORITHM table.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createIntermediateResulTable() throws SQLException {
		tableName = "INTERMEDIATE_RESULT";
		columnNames = "int_result_id BIGINT NOT NULL, "
						+ "video_id BIGINT, "
						+ "algo_id BIGINT, "
						+ "ds_id BIGINT, "
						+ "name VARCHAR, "
						+ "picture VARCHAR, "
						+ "description VARCHAR, "
						+ "start_frame INTEGER, "
						+ "stop_frame INTEGER, "
						+ "positions VARCHAR, "
						+ "features VARCHAR, "
						+ "creation_date DATE, ";
		
		primaryKeyName = "int_result_pk";
		primaryKeyField = "int_result_id";
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to create lambdaclovr.ANOMALY table to store alerts etc.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void createAnomaly() throws SQLException {
		tableName = "ANOMALY";
		columnNames = "anomaly_id BIGINT NOT NULL, "
						+ "service_id BIGINT, "
						+ "ds_id BIGINT, "
						+ "topic_name VARCHAR, "
						+ "title VARCHAR, "
						+ "message VARCHAR, "
						+ "frame_cols INTEGER, "
						+ "frame_rows INTEGER, "
						+ "frame_type INTEGER, "
						+ "frame_data VARBINARY,"
						+ "frame_timestamp TIMESTAMP, "
						+ "start_frame TIMESTAMP, "
						+ "stop_frame TIMESTAMP, "
						+ "positions VARCHAR, "
						+ "creation_date DATE, ";
		
		primaryKeyName = "anomaly_pk";
		primaryKeyField = "anomaly_id"; //Sequence name
		
		schemaHandler.createTable(schemaName, tableName, columnNames, primaryKeyName, primaryKeyField);
	}	
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to deploy LAMBDA-CLOVR Schema on HBase.
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void deploySiatSchema() throws SQLException
	{
		//Create Schema SAIT
		createSchema(schemaName);
		
		//Create Tables
		createUserTable();					// 1 
		createVendorTable();				// 2
		createVendorModelTable();			// 3
		createDataSourceTable();			// 4
		createDsTypeTable();				// 5
		createVideoTable();					// 6
		createServiceTable();				// 7
		createServiceSubscriptionTable();	// 8
		createAlgorithmTable();				// 9
		createServiceAlgorithmTable();		// 10
		createIntermediateResulTable();		// 11
		createAnomaly();					// 12
	}
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * This method is used to delete the LAMBDA-CLOVR Schema and the respective sequences.
	 * 
	 * @param void
	 * @return void
	 * @throws SQLException 
	 */
	public void dropSiatSchema() throws SQLException
	{	
		//Drop Tables
		dropTable(schemaName, "USER", "user_id");						//1
		dropTable(schemaName, "VENDOR", "vendor_id");					//2
		dropTable(schemaName, "VENDOR_MODEL", "vendor_model_id");		//3
		dropTable(schemaName, "DATA_SOURCE", "ds_id");					//4
		dropTable(schemaName, "DS_TYPE", "dstype_id");					//5
		dropTable(schemaName, "VIDEO", "video_id");						//6
		dropTable(schemaName, "SERVICE", "service_id");					//7
		dropTable(schemaName, "SERVICE_SUBSCRIPTION", "ss_id");			//8
		dropTable(schemaName, "ALGORITHM", "algo_id");					//9
		dropTable(schemaName, "SERVICE_ALGORITHM", "service_algo_id");	//10
		dropTable(schemaName, "INTERMEDIATE_RESULT", "int_result_id");	//11
		dropTable(schemaName, "ANOMALY", "anomaly_pk");					//12
		
		//Drop LAMBDA-CLOVR Schema
		dropSchema(schemaName);			
	}

}
