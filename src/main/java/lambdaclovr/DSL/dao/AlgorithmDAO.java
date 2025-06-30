package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lambdaclovr.dsl.model.Algorithm;
import lambdaclovr.dsl.model.Video;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: algorithmDAO</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used to handel algorithmDAO related CRUD
 *               operations.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.00
 * @since 2024-29-07
 **/
public class AlgorithmDAO {
	private static Crud operation = new Crud();

	/**
	 * Create a new Algorithm.
	 * 
	 * @param RESULT  Object
	 * @return RESULT Object
	 * @throws SQLException
	 */
	public static Algorithm create(Algorithm algorithm) throws Exception {

		String[] data = new String[8];

		data[0] = "NEXT VALUE FOR lambdaclovr.algo_id";

		data[1] = "\'" + algorithm.getName() + "\'";
		data[2] = "\'" + algorithm.getAlgorithmId() + "\'";

		data[3] = "\'" + algorithm.getResources() + "\'";
		data[4] = "\'" + algorithm.getCreationDate() + "\'";

		data[5] = "" + algorithm.getInputTypeId() + "";
		data[6] = "" + algorithm.getUserId() + "";
		data[7] = "" + algorithm.getScId() + "";

		System.out.println("Data = " + Arrays.toString(data));

		operation.insertOrUdate("ALGORITHM", data);

		return algorithm;
	}

	/////////////////////////////////////////////////////////////

	/**
	 * Create a new Algorithm.
	 * 
	 * @param RESULT Object
	 * @return RESULT Object
	 * @throws SQLException
	 */
	public static Algorithm update(Algorithm algorithm) throws Exception {

		String[] data = new String[8];

		data[0] = "" + algorithm.getAlgorithmId() + "";

		data[1] = "\'" + algorithm.getName() + "\'";
		data[2] = "\'" + algorithm.getAlgorithmId() + "\'";

		data[3] = "\'" + algorithm.getResources() + "\'";
		data[4] = "\'" + algorithm.getCreationDate() + "\'";

		data[5] = "" + algorithm.getInputTypeId() + "";
		data[6] = "" + algorithm.getUserId() + "";
		data[7] = "" + algorithm.getScId() + "";

		System.out.println("Data = " + Arrays.toString(data));

		operation.insertOrUdate("ALGORITHM", data);

		return algorithm;
	}

	/////////////////////////////////////////////////////////////

	/**
	 * get a single ALGORITHM
	 * 
	 * @param algoId
	 *            (PK of the ALGORITHM table)
	 * @return ALGORITHM object
	 * @throws SQLException
	 */
	public static Algorithm get(int algoId) throws SQLException {

		String algoIdColumnName = "ALGO_ID";
		ResultSet rs = operation.getRow("ALGORITHM", algoIdColumnName, algoId);
		Algorithm algorithm = new Algorithm();

		while (rs.next()) {
			algorithm.setAlgoId(rs.getString("ALGO_ID")).setName(rs.getString("NAME"))
					.setAlgorithmId(rs.getString("ALGORITHM_ID")).setCreationDate(rs.getString("CREATION_DATE"))
					.setInputTypeId(rs.getString("INPUT_TYPE_ID")).setUserId(rs.getString("USER_ID"))
					.setScId(rs.getString("SC_ID"));
		}

		return algorithm;
	}

	/////////////////////////////////////////////////////////////

	/**
	 * get a single ALGORITHM
	 * 
	 * @param userId
	 *            (PK of the User table)
	 * @return ALGORITHM Object List 
	 * @throws SQLException
	 */
	public static List<Algorithm> getUserAlgorithms(int userId) throws SQLException {

		String algoIdColumnName = "user_id";
		ResultSet rs = operation.getRow("ALGORITHM", algoIdColumnName, userId);
		
		List<Algorithm> algorithmList = new ArrayList<>();
		
		while (rs.next()) {
			Algorithm algorithm = new Algorithm()
					.setAlgoId(rs.getString("ALGO_ID"))
					.setName(rs.getString("NAME"))
					.setAlgorithmId(rs.getString("ALGORITHM_ID"))
					.setCreationDate(rs.getString("CREATION_DATE"))
					.setInputTypeId(rs.getString("INPUT_TYPE_ID"))
					.setUserId(rs.getString("USER_ID"))
					.setScId(rs.getString("SC_ID"));
		
			algorithmList.add(algorithm);
		}

		return algorithmList;
	}

}
