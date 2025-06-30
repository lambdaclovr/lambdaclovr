package lambdaclovr.dsl.phoenix;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * <h1>LAMBDA-CLOVR Project</h1> 
 * <h2>Layer: Data Storage Layer</h2> 
 * <h3>Package Name: lambdaclovr.dsl.phoenix</h3>
 * <h3>Class Name: Crud</h3>
 * <p>
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p>
 * <p>
 * @Description: This class is used to handel Hbase/Phoenix CRUD operations.
 * </p>
 * 
 * @author Numan Khan, 
 * 
 * @version 1.0
 * @since 2024-05-20
 **/
public class Crud extends PhoenixConnection {
	/**
	 * This function add a new user into lambdaclovr HBase database or update the information of the existing one.
	 * @param table_name
	 * @param data
	 */

	public void insertOrUdate(String table_name, String[] data) {
		try {
			statement = connection.createStatement();
			String query = "upsert into " + dataBase + "." + table_name + "  values ( " + String.join(",", data) + ")";
			
			System.out.println(query);
			
			statement.executeUpdate(query);
			connection.commit();
			System.out.println("Inserted/Updated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function return sql ResultSet object contain a row of table <table_name> associated with <colName> which value is in <colValue>
	 * @param table_name
	 * @return ResultSet object list
	 */
	public ResultSet getAllRows(String table_name) {
		
		ResultSet rs=null;
		try {
			statement = connection.createStatement();
			String query = "select * from " + dataBase + "." + table_name;
			//System.out.println(query);
			rs= statement.executeQuery(query);
//			rs.next();
//			System.out.println(rs.getString(2));
//			System.out.println("successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * This function return sql ResultSet object contain a row of table <table_name> associated with <colName> which value is in <colValue>
	 * @param table_name
	 * @param colName
	 * @param colvalue
	 * @return ResultSet object
	 */
	public ResultSet getRow(String table_name,String colName, String colValue) {
		
		ResultSet rs=null;
		try {
			statement = connection.createStatement();
			String query = "select * from " + dataBase + "." + table_name + " where "+colName+" =\'" + colValue + "\'";
			//System.out.println(query);
			rs= statement.executeQuery(query);
//			rs.next();
//			System.out.println(rs.getString(2));
//			System.out.println("successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 *  This is an overridden  function  and it will return sql ResultSet object contain a row of table <table_name> associated with <colName> which value is in <colValue>.
	 *  this function clumnValue should be Number
	 * @param table_name
	 * @param colName
	 * @param colvalue
	 * @return
	 */
	public ResultSet getRow(String table_name, String colName,  Number colValue) {
		ResultSet rs=null;
		try {
			statement = connection.createStatement();
			String query = "select * from " + dataBase + "." + table_name + " where "+colName+"=" + colValue;
			System.out.println(query);
			rs=statement.executeQuery(query);
			//rs.next();
			//System.out.println(rs.getString("USER_NAME")); //just for test
			System.out.println("successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	
	/**
	 * 
	 * This is an overridden  function  and it will return sql ResultSet object contain a row of table <table_name> associated with mulitple querries
	 * this function clumnValue should be Number
	 * 
	 * @ Numan & MOSTAFIJ
	 * 
	 * @param table_name
	 * @param colName
	 * @param queryMap
	 * @return
	 */
	public ResultSet getRow(String table_name, HashMap<String, String> queryMap) {
		
		String strQueryClause = "";
		
		for (String key: queryMap.keySet()) {
			strQueryClause +=  " " + key + " = " + queryMap.get(key) + " and " ; 
		}		
		
		strQueryClause = strQueryClause.length() > 2 ? strQueryClause.substring(0, strQueryClause.length() - 4) :strQueryClause ; 
		
		ResultSet rs=null;
		try {
			statement = connection.createStatement();
			String query = "select * from " + dataBase + "." + table_name + " where " + strQueryClause;
			System.out.println(query);
			rs=statement.executeQuery(query);
			//rs.next();
			//System.out.println(rs.getString("USER_NAME")); //just for test
			System.out.println("successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * This function deletes a row from table <table_name> give the column colName with column value colValue
	 * @param table_name
	 * @param colName
	 * @param colValue
	 * @return
	 */
	
	
	public ResultSet removeRow(String table_name,String colName,  String colValue) {
		ResultSet rs=null;
		try {
			statement = connection.createStatement();
			String query = "delete from " + dataBase + "." + table_name + " where "+colName+" =\'" + colValue + "\'";
			System.out.println(query);
			statement.executeUpdate(query);
			connection.commit();
			System.out.println("successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public ResultSet removeRow(String table_name,String colName,  Number colValue) {
		ResultSet rs=null;
		try {
			statement = connection.createStatement();
			String query = "delete from " + dataBase + "." + table_name + " where "+colName+" =" + colValue ;
			System.out.println(query);
			statement.executeUpdate(query);
			connection.commit();
			System.out.println("Deleted successfully.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	//generic Function
/*	public static <T> T f(String x) {
		  Integer[] arr = new Integer[4];
		  T ret = (T) arr[2];
		  return ret;
		}*/
}