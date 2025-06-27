package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lambdaclovr.dsl.model.Service;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1> 
 * <h2>Layer: Data Storage Layer</h2> 
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: ServiceDAO</h3>
 * <p>
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p>
 * <p>
 * @Description: This class is used to handle services related operations.
 * </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-06-01
 **/

public class ServiceDAO {
	private static Crud operation = new Crud();
	 
	/**
	 * Create a new Service.
	 * 
	 * @param Service Object
	 * @return Service Object
	 * @throws SQLException
	 */
	public static Service create(Service service) throws Exception {

		String[] data = new String[7];

		data[0] = "NEXT VALUE FOR lambdaclovr.service_id";
		data[1] = "" + service.getUserId() + "";
		data[2] = "" + service.getDsTypeId() + "";
		data[3] = "" + service.getServiceTypeId() + "";
		data[4] = "\'" + service.getServiceName() + "\'";
		data[5] = "\'" + service.getDescription() + "\'";
		data[6] = "TO_DATE(\'" + service.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

		operation.insertOrUdate("SERVICE", data);

		return service;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * Create an existing Service.
	 * 
	 * @param Service Object
	 * @return Service Object
	 * @throws SQLException
	 */
	public static Service update(Service service) throws Exception {

		String[] data = new String[7];

		data[0] = "" + service.getServiceId() + "";
		data[1] = "" + service.getUserId() + "";
		data[2] = "" + service.getDsTypeId() + "";
		data[3] = "" + service.getServiceTypeId() + "";
		data[4] = "\'" + service.getServiceName() + "\'";
		data[5] = "\'" + service.getDescription() + "\'";
		data[6] = "TO_DATE(\'" + service.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

		System.out.println("Data = " + Arrays.toString(data));
		
		operation.insertOrUdate("SERVICE", data);

		return service;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a single service
	 * 
	 * @param serviceId (PK of the Service table)
	 * @return Service object
	 * @throws SQLException
	 */
	public static Service get(int serviceId) throws SQLException {

		String ServicelIdColumnName = "SERVICE_ID";
		ResultSet rs = operation.getRow("SERVICE", ServicelIdColumnName, serviceId);
		Service service = new Service();
		
		while (rs.next()) {
			service.setServiceId(rs.getString("SERVICE_ID"))
				.setUserId(rs.getString("USER_ID"))	
				.setDsTypeId(rs.getString("DSTYPE_ID"))
				.setServiceTypeId(rs.getString("SERVICE_TYPE_ID"))
				.setServiceName(rs.getString("SERVICE_NAME"))
				.setDescription(rs.getString("DESCRIPTION"))
				.setCreationDate(rs.getString("CREATION_DATA"));
		}
		return service;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a list of services belongs to a user.
	 * 
	 * @param userId (FK from USER table)
	 * @return A list of service objects
	 * @throws SQLException
	 * 
	 */
	public static  List<Service> getList(int userId) throws SQLException {
	
		String userIdColumnName = "USER_ID";
		ResultSet rs = operation.getRow("SERVICE", userIdColumnName, userId);
				
		List<Service> list = new ArrayList<Service>();
	
		while (rs.next()) {
			Service service = new Service()
				.setServiceId(rs.getString("SERVICE_ID"))
				.setUserId(rs.getString("USER_ID"))	
				.setDsTypeId(rs.getString("DSTYPE_ID"))
				.setServiceTypeId(rs.getString("SERVICE_TYPE_ID"))
				.setServiceName(rs.getString("SERVICE_NAME"))
				.setDescription(rs.getString("DESCRIPTION"))
				.setCreationDate(rs.getString("CREATION_DATA"));
			
			list.add(service);
		}

		//System.out.println(list.size());
		return list;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a list of services belongs to a USER and of type DS_TYPE.
	 * 
	 * @param userId and dsTypeId (FK from USER and DS_TYPE table respectively)
	 * @return A list of service objects
	 * @throws SQLException
	 * 
	 */
	public static  List<Service> getList(int userId, int dsTypeId) throws SQLException {
	
		HashMap<String, String> queryMap = new HashMap<String, String>();
		
		queryMap.put("user_id", userId + " " );
		queryMap.put("dstype_id", dsTypeId + " " );
		
		ResultSet rs = operation.getRow("SERVICE", queryMap);
		List<Service> list = new ArrayList<Service>();
		Service service = new Service();
		
		while (rs.next()) {
			service.setServiceId(rs.getString("SERVICE_ID"))
				.setUserId(rs.getString("USER_ID"))	
				.setDsTypeId(rs.getString("DSTYPE_ID"))
				.setServiceTypeId(rs.getString("SERVICE_TYPE_ID"))
				.setServiceName(rs.getString("SERVICE_NAME"))
				.setDescription(rs.getString("DESCRIPTION"))
				.setCreationDate(rs.getString("CREATION_DATA"));
			
			list.add(service);
		}
		return list;
	}

	/////////////////////////////////////////////////////////////
	
	/**
	 * get all services
	 * 
	 * @param 
	 * @return Service object
	 * @throws SQLException
	 */
	public static List<Service> getAll() throws SQLException {

		ResultSet rs = operation.getAllRows("SERVICE");
		List<Service> list = new ArrayList<Service>();
		Service service = new Service();
		
		while (rs.next()) {
			service.setServiceId(rs.getString("SERVICE_ID"))
				.setUserId(rs.getString("USER_ID"))	
				.setDsTypeId(rs.getString("DSTYPE_ID"))
				.setServiceTypeId(rs.getString("SERVICE_TYPE_ID"))
				.setServiceName(rs.getString("SERVICE_NAME"))
				.setDescription(rs.getString("DESCRIPTION"))
				.setCreationDate(rs.getString("CREATION_DATA"));
		
			list.add(service);
		}
		
		return list;
	}
}