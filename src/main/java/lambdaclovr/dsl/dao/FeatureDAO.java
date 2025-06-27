package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lambdaclovr.dsl.model.Feature;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1> 
 * <h2>Layer: Data Storage Layer</h2> 
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: FeatureDAO</h3>
 * <p>
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p>
 * <p>
 * @Description: This class is used to handel Feature CRUD Operations.
 * </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-05-30
 **/
public class FeatureDAO {
	private static Crud operation = new Crud();
	
	////////////////////////////////////////////////////
	
	/**
	 * Create a new Feature.
	 * 
	 * @param feature object
	 * @return feature object
	 * @throws SQLException
	 */
	public static Feature create(Feature feature) throws SQLException {

		String[] data = new String[5];

		data[0] = "NEXT VALUE FOR lambdaclovr.feature_pk";
		data[1] = "" + feature.getVideoId() + "";
		data[2] = "\'" + feature.getFeatureName() + "\'";
		data[3] = "\'" + feature.getFeatureVector() + "\'";
		data[4] = "TO_DATE(\'" + feature.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

		//System.out.println("Data = " + Arrays.toString(data));
		operation.insertOrUdate("FEATURE", data);

		return feature;
	}
	
	////////////////////////////////////////////////////
	
	/**
	 * Update a Feature.
	 * 
	 * @param feature object
	 * @return feature object
	 * @throws SQLException
	 */
	public static Feature update(Feature feature) throws SQLException {

		String[] data = new String[4];

		data[0] = "" + feature.getFeatureId() + "";
		data[1] = "" + feature.getVideoId() + "";
		data[2] = "\'" + feature.getFeatureName() + "\'";
		data[3] = "\'" + feature.getFeatureVector() + "\'";
		//data[4] = "TO_DATE(\'" + feature.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')"; //no need to update date

		//System.out.println("Data = " + Arrays.toString(data));
		operation.insertOrUdate("FEATURE", data);

		return feature;
	}
	
	////////////////////////////////////////////////////
	
	/**
	 * Delete a Feature;
	 * 
	 * @param feature id, Type int
	 */
	public static void delete(int id) {
		String IdColumnName = "feature_id";
		operation.removeRow("FEATURE", IdColumnName, id);
	}
	
	////////////////////////////////////////////////////
	
	/**
	 * This function fetch a single record of feature from DB.  
	 * 
	 * @param PK id 
	 * @return Feature object
	 * @throws SQLException
	 */
	public static Feature get(int id) throws SQLException {
		String featureIdColumnName = "FEATURE_ID";
		ResultSet rs = operation.getRow("FEATURE", featureIdColumnName, id);
		Feature feature = new Feature();
		
		while (rs.next()) {
			feature.setFeatureId(rs.getString("FEATURE_ID"))
				.setVideoId(rs.getString("VIDEO_ID"))
				.setFeatureName(rs.getString("FEATURE_NAME"))
				.setFeatureVector(rs.getString("FEATURE_VECTOR"))
				.setCreationDate(rs.getString("CREATION_DATE"));
		}
		
		//System.out.println("Name: " + feature.getFeatureName());
		return feature;
	}

	////////////////////////////////////////////////////
	
	 /* This function fetch a list of all the features of a video from the DB (1:m).
	 * id is the foreigh key from video table
	 * 
	 * @param id FK 
	 * @return list of features 
	 * @throws SQLException
	 */
	 
	public static List<Feature> getList(int id) throws SQLException {
		String VideoIdColumnName = "VIDEO_ID";
		ResultSet rs = operation.getRow("VENDOR_MODEL", VideoIdColumnName, id);
				
		List<Feature> list = new ArrayList<Feature>();

		while (rs.next()) {
			
			Feature feature = new Feature()
					.setFeatureId(rs.getString("FEATURE_ID"))
					.setVideoId(rs.getString("VIDEO_ID"))
					.setFeatureName(rs.getString("FEATURE_NAME"))
					.setFeatureVector(rs.getString("FEATURE_VECTOR"))
					.setCreationDate(rs.getString("CREATION_DATE"));

			list.add(feature);
		}

		System.out.println(list.size());
		return list;
	}

}