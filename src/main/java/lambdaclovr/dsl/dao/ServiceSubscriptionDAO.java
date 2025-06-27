package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lambdaclovr.dsl.model.ServiceSubscription;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: Service</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p><p>
 * @Description: This class is used to handle services related CRUD operations.
 * </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-06-06
 **/
public class ServiceSubscriptionDAO {
	private static Crud operation = new Crud();

	/**
	 * Create a new ServiceSubscription.
	 * 
	 * @param Service Subscription Object
	 * @return Service Object
	 * @throws SQLException
	 */
	public static ServiceSubscription create(ServiceSubscription serviceSubscription) throws Exception {

		String[] data = new String[7];

		data[0] = "NEXT VALUE FOR lambdaclovr.ss_id";
		data[1] = "" + serviceSubscription.getServiceId() + "";
		data[2] = "" + serviceSubscription.getDsId() + "";
		data[3] = "" + serviceSubscription.getStatus() + "";
		data[4] = "TO_DATE(\'" + serviceSubscription.getSubscriptionDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		data[5] = "TO_DATE(\'" + serviceSubscription.getSubscriptionStartDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		data[6] = "TO_DATE(\'" + serviceSubscription.getSubscriptionStopDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		
		//System.out.println("Data = " + Arrays.toString(data));
		operation.insertOrUdate("SERVICE_SUBSCRIPTION", data);

		return serviceSubscription;
	}

	/////////////////////////////////////////////////////////////
	
	/**
	 * Update an existing ServiceSubscription.
	 * 
	 * @param Service Subscription Object
	 * @return Service Object
	 * @throws SQLException
	 */
	public static ServiceSubscription update(ServiceSubscription serviceSubscription) throws Exception {

		String[] data = new String[7];

		data[0] = "" + serviceSubscription.getsSId() + "";
		data[1] = "" + serviceSubscription.getServiceId() + "";
		data[2] = "" + serviceSubscription.getDsId() + "";
		data[3] = "" + serviceSubscription.getStatus() + "";
		data[4] = "TO_DATE(\'" + serviceSubscription.getSubscriptionDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		data[5] = "TO_DATE(\'" + serviceSubscription.getSubscriptionStartDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		data[6] = "TO_DATE(\'" + serviceSubscription.getSubscriptionStopDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

		//System.out.println("Data = " + Arrays.toString(data));
		
		operation.insertOrUdate("SERVICE_SUBSCRIPTION", data);

		return serviceSubscription;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a SERVICE_SUBSCRIPTION.  
	 * 
	 * @param sSId (PK of the SERVICE_SUBSCRIPTION table)
	 * @return Service Subscription object
	 * @throws SQLException
	 * 
	 */
	public static  ServiceSubscription get(int sSId) throws SQLException {
		
		String ServiceSubscriptionlIdColumnName = "SS_ID";
		ResultSet rs = operation.getRow("SERVICE_SUBSCRIPTION", ServiceSubscriptionlIdColumnName, sSId);
		ServiceSubscription serviceSubscription = new ServiceSubscription();
		
		while (rs.next()) {
			serviceSubscription.setsSId(rs.getString("ss_id"))
				.setServiceId(rs.getString("SERVICE_ID"))
				.setDsId(rs.getString("DS_ID"))
				.setStatus(rs.getString("STATUS"))
				.setSubscriptionDate(rs.getString("SUBSCRIPTION_DATE"))
				.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_START_DATE"))
				.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_STOP_DATE"));
		}
		
		return serviceSubscription;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a SERVICE_SUBSCRIPTION of a DATA_SOURCE subscribed to a SERVICE.  
	 * 
	 * @param dSId and serviceId (FK from DATA_SOURCE and SERVICE table respectively)
	 * @return Service Subscription object
	 * @throws SQLException
	 * 
	 */
	public static  ServiceSubscription get(int dSId, int serviceId) throws SQLException {
	
		HashMap<String, String> queryMap = new HashMap<String, String>();
		
		queryMap.put("ds_id", dSId + " " );
		queryMap.put("service_id", serviceId + " " );
		
		ResultSet rs = operation.getRow("SERVICE_SUBSCRIPTION", queryMap);
		ServiceSubscription serviceSubscription = new ServiceSubscription();
		
		while (rs.next()) {
			serviceSubscription.setsSId(rs.getString("ss_id"))
				.setServiceId(rs.getString("SERVICE_ID"))
				.setDsId(rs.getString("DS_ID"))
				.setStatus(rs.getString("STATUS"))
				.setSubscriptionDate(rs.getString("SUBSCRIPTION_DATE"))
				.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_START_DATE"))
				.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_STOP_DATE"));
		}
		
		return serviceSubscription;
	}

	/////////////////////////////////////////////////////////////
	
	/**
	 * get a list of SERVICE_SUBSCRIPTION of a DATA_SOURCE subscribed to a SERVICE.  
	 * 
	 * @param serviceId (FK from SERVICE table respectively)
	 * @return Service Subscription objects
	 * @throws SQLException
	 * 
	 */
	public static List<ServiceSubscription>  getSubscribedDataSourceList(int serviceId) throws SQLException {
		
		String serviceIdColumnName = "SERVICE_ID";
		ResultSet rs = operation.getRow("SERVICE_SUBSCRIPTION", serviceIdColumnName, serviceId);
				
		List<ServiceSubscription> list = new ArrayList<ServiceSubscription>();
		ServiceSubscription serviceSubscription = new ServiceSubscription();
		
		while (rs.next()) {
			serviceSubscription.setsSId(rs.getString("ss_id"))
			.setServiceId(rs.getString("SERVICE_ID"))
			.setDsId(rs.getString("DS_ID"))
			.setStatus(rs.getString("STATUS"))
			.setSubscriptionDate(rs.getString("SUBSCRIPTION_DATE"))
			.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_START_DATE"))
			.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_STOP_DATE"));
			
			list.add(serviceSubscription);
		}

		//System.out.println(list.size());
		return list;
	}

	/////////////////////////////////////////////////////////////
	
	/**
	 * get a list of SERVICE_SUBSCRIPTION of a SERVICE being subscribed.  
	 * 
	 * @param dSId (FK from DATA_SOURCE table)
	 * @return Service Subscription objects
	 * @throws SQLException
	 * 
	 */	
	public static List<ServiceSubscription>  getSubscribedServiceList(int dSId) throws SQLException {
		
		String serviceIdColumnName = "DS_ID";
		ResultSet rs = operation.getRow("SERVICE_SUBSCRIPTION", serviceIdColumnName, dSId);
				
		List<ServiceSubscription> list = new ArrayList<ServiceSubscription>();
		ServiceSubscription serviceSubscription = new ServiceSubscription();
		
		while (rs.next()) {
			serviceSubscription.setsSId(rs.getString("ss_id"))
			.setServiceId(rs.getString("SERVICE_ID"))
			.setDsId(rs.getString("DS_ID"))
			.setStatus(rs.getString("STATUS"))
			.setSubscriptionDate(rs.getString("SUBSCRIPTION_DATE"))
			.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_START_DATE"))
			.setSubscriptionStartDate(rs.getString("SUBSCRIPTION_STOP_DATE"));
			
			list.add(serviceSubscription);
		}

		//System.out.println(list.size());
		return list;
	}

}
