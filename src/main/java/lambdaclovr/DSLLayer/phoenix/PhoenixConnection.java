package lambdaclovr.dsl.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <h1>LAMBDA-CLOVR Project</h1> 
 * <h2>Layer: Data Storage Layer</h2> 
 * <h3>Package Name: lambdaclovr.dsl.phoenix</h3>
 * <h3>Class Name: PhoenixConnection</h3>
 * <p>
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p>
 * <p>
 * @Description: This class is used to handel Hbase/Phoenix Connection.
 * </p>
 * 
 * @author Numan Khan, 
 * http://<host-ip>		
 * @version 1.0
 * @since 2024-05-20
 **/

public class PhoenixConnection {
	public String URl1 = "jdbc:phoenix:host:port:/hbase-unsecure"; // In use
	//public String URl2 = "jdbc:phoenix:thin:url=http://host:port;serialization=PROTOBUF";
	public String URl2 = "jdbc:phoenix:thin:url=http://<host-ip>:8765;serialization=PROTOBUF";
	//public String driver = "org.apache.phoenix.jdbc.PhoenixDriver";
	public String driver = "org.apache.phoenix.queryserver.client.Driver";
	public String userName = "";
	public String password = "";
	public String dataBase= "lambdaclovr";
	public Connection connection = null;
	public Statement statement = null;
	public ResultSet rS = null;
	public PreparedStatement ps = null;

	public PhoenixConnection() {

		try {

			// Connect to the database
			Class.forName(this.driver);
			connection = DriverManager.getConnection(this.URl2);
			//System.out.println("Connection established successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}