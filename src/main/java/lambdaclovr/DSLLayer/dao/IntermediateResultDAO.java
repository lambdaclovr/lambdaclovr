package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lambdaclovr.dsl.model.IntermediateResult;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: IntermediateResultDAO</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used to handle IntermediatResultDAO related CRUD
 *               operations.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since Available after 0.4
 **/
public class IntermediateResultDAO {
	private static Crud operation = new Crud();

	/**
	 * Create a new IntermediatResult.
	 * 
	 * @param IntermediatResult
	 *            Object
	 * @return IntermediatResult Object
	 * @throws SQLException
	 */
	public static IntermediateResult create(IntermediateResult intermediateResult) throws Exception {

		String[] data = new String[12];

		data[0] = "NEXT VALUE FOR lambdaclovr.int_result_id";
		data[1] = "" + intermediateResult.getVideoId() + "";
		data[2] = "" + intermediateResult.getAlgoId() + "";
		data[3] = "" + intermediateResult.getDsId() + "";
		data[4] = "\'" + intermediateResult.getName() + "\'";
		data[5] = "\'" + intermediateResult.getPicture() + "\'";
		data[6] = "\'" + intermediateResult.getDescription() + "\'";
		data[7] = "" + intermediateResult.getStartFrame() + "";
		data[8] = "" + intermediateResult.getEndFrame() + "";
		data[9] = "\'" + intermediateResult.getPosition() + "\'";
		data[10] = "\'" + intermediateResult.getFeatures() + "\'";
		data[11] = "TO_DATE(\'" + intermediateResult.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

		System.out.println("Data = " + Arrays.toString(data));
		
		operation.insertOrUdate("INTERMEDIATE_RESULT", data);

		return intermediateResult;
	}

	/////////////////////////////////////////////////////////////

	/**
	 * Update existing IntermediatResult.
	 * 
	 * @param IntermediatResult
	 *            Object
	 * @return IntermediatResult Object
	 * @throws SQLException
	 */
	public static IntermediateResult update(IntermediateResult intermediateResult) throws Exception {

		String[] data = new String[12];

		data[0] = "NEXT VALUE FOR lambdaclovr.int_result_id";
		data[1] = "" + intermediateResult.getVideoId() + "";
		data[2] = "" + intermediateResult.getAlgoId() + "";
		data[3] = "" + intermediateResult.getDsId() + "";
		data[4] = "\'" + intermediateResult.getName() + "\'";
		data[5] = "\'" + intermediateResult.getPicture() + "\'";
		data[6] = "\'" + intermediateResult.getDescription() + "\'";
		data[7] = "" + intermediateResult.getStartFrame() + "";
		data[8] = "" + intermediateResult.getEndFrame() + "";
		data[9] = "\'" + intermediateResult.getPosition() + "\'";
		data[10] = "\'" + intermediateResult.getFeatures() + "\'";
		data[11] = "TO_DATE(\'" + intermediateResult.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";


		System.out.println("Data = " + Arrays.toString(data));

		operation.insertOrUdate("INTERMEDIATE_RESULT", data);

		return intermediateResult;
	}

	/////////////////////////////////////////////////////////////

	/**
	 * get a single IntermediateResult
	 * 
	 * @param intResultId
	 *            (PK of the IntermediateResult Table)
	 * @return ALGORITHM object
	 * @throws SQLException
	 */
	public static IntermediateResult get(int intResultId) throws SQLException {

		String intResultIdColumnName = "INT_RESULT_ID";
		ResultSet rs = operation.getRow("INTERMEDIATE_RESULT", intResultIdColumnName, intResultId);
		IntermediateResult intermediateResult = new IntermediateResult();

		while (rs.next()) {

			intermediateResult
				.setIntResultId(rs.getString("INT_RESULT_ID"))
				.setVideoId(rs.getString("VIDEO_ID"))
				.setAlgoId(rs.getString("ALGO_ID"))
				.setDsId(rs.getString("DS_ID"))
				.setName(rs.getString("NAME"))
				.setPicture(rs.getString("PICTURE"))
				.setDescription(rs.getString("DESCRIPTION"))
				.setStartFrame(rs.getString("START_FRAME"))
				.setEndFrame(rs.getString("STOP_FRAME"))
				.setPosition(rs.getString("POSITIONS"))
				.setFeatures(rs.getString("FEATURES"))
				.setCreationDate(rs.getString("CREATION_DATE"));
		}

		return intermediateResult;
	}

	/////////////////////////////////////////////////////////////

	/**
	 * get a list of IntermediateResult
	 * 
	 * @param videoId
	 *            (PK of the Video table)
	 * @return IntermediateResult Object List
	 * @throws SQLException
	 */
	public static List<IntermediateResult> getVideoIntermediateResult(int videoId) throws SQLException {

		String videoIdColumnName = "VIDEO_ID";
		ResultSet rs = operation.getRow("INTERMEDIATE_RESULT", videoIdColumnName, videoId);

		List<IntermediateResult> intermediateResultList = new ArrayList<>();

		while (rs.next()) {

			IntermediateResult intermediateResult = new IntermediateResult()
					.setIntResultId(rs.getString("INT_RESULT_ID"))
					.setVideoId(rs.getString("VIDEO_ID"))
					.setAlgoId(rs.getString("ALGO_ID"))
					.setDsId(rs.getString("DS_ID"))
					.setName(rs.getString("NAME"))
					.setPicture(rs.getString("PICTURE"))
					.setDescription(rs.getString("DESCRIPTION"))
					.setStartFrame(rs.getString("START_FRAME"))
					.setEndFrame(rs.getString("STOP_FRAME"))
					.setPosition(rs.getString("POSITIONS"))
					.setCreationDate(rs.getString("CREATION_DATE"));

			intermediateResultList.add(intermediateResult);
		}

		return intermediateResultList;
	}

}
