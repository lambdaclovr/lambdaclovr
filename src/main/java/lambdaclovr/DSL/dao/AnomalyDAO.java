package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lambdaclovr.dsl.model.Anomaly;
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
 * @Description: This class is used to handel RESULT related CRUD operations.
 * </p>
 * 
 * @author Numan Khan
 * 
 * @version 4.0
 * @since 2024-02-05
 **/
public class AnomalyDAO {
	private static Crud operation = new Crud();

	/**
	 * Create a new ANOMALY.
	 * 
	 * @param ANOMALY Object
	 * @return ANOMALY Object
	 * @throws SQLException
	 */
	public static Anomaly create(Anomaly anomaly) throws Exception {

		String[] data = new String[15];

		data[0] = "NEXT VALUE FOR lambdaclovr.anomaly_id";
		data[1] = "" + Integer.parseInt(anomaly.getServiceId()) + "";
		data[2] = "" + Integer.parseInt(anomaly.getDsId()) + "";
		data[3] = "\'" + anomaly.getTopicName() + "\'";
		
		data[4] = "\'" + anomaly.getTitle() + "\'";
		data[5] = "\'" + anomaly.getMessage() + "\'";
		
		data[6] = "" + anomaly.getCols() + "";
		data[7] = "" + anomaly.getRows() + "";
		data[8] = "" + anomaly.getType() + "";
		
		data[9] = "\'" + anomaly.getData() + "\'";
		data[10] = "\'" + anomaly.getTimestamp() + "\'";
		
		data[11] = "\'" + anomaly.getStartFrameTimestamp() + "\'";
		data[12] = "\'" + anomaly.getEndFrameTimestamp() + "\'";
		data[13] = "\'" + anomaly.getPosition() + "\'";
		
		data[14] = "\'" + anomaly.getCreationDate() + "\'";
		
		//System.out.println("Data = " + Arrays.toString(data));
		
		operation.insertOrUdate("ANOMALY", data);

		return anomaly;
	}
	
	/////////////////////////////////////////////////////////////

	/**
	 * Update ANOMALY.
	 * 
	 * @param ANOMALY Object
	 * @return ANOMALY Object
	 * @throws SQLException
	 */
	public static Anomaly update(Anomaly anomaly) throws Exception {

		String[] data = new String[15];

		data[0] = "NEXT VALUE FOR lambdaclovr.anomaly_id";
		data[1] = "" + Integer.parseInt(anomaly.getServiceId()) + "";
		data[2] = "" + Integer.parseInt(anomaly.getDsId()) + "";
		data[3] = "\'" + anomaly.getTopicName() + "\'";
		
		data[4] = "\'" + anomaly.getTitle() + "\'";
		data[5] = "\'" + anomaly.getMessage() + "\'";
		
		data[6] = "" + anomaly.getCols() + "";
		data[7] = "" + anomaly.getRows() + "";
		data[8] = "" + anomaly.getType() + "";
		
		data[9] = "\'" + anomaly.getData() + "\'";
		data[10] = "\'" + anomaly.getTimestamp() + "\'";
		
		data[11] = "\'" + anomaly.getStartFrameTimestamp() + "\'";
		data[12] = "\'" + anomaly.getEndFrameTimestamp() + "\'";
		data[13] = "\'" + anomaly.getPosition() + "\'";
		
		data[14] = "\'" + anomaly.getCreationDate() + "\'";
		
		operation.insertOrUdate("ANOMALY", data);

		return anomaly;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a single ANOMALY
	 * 
	 * @param anomalyId (PK of the ANOMALY table)
	 * @return ANOMALY object
	 * @throws SQLException
	 */
	public static Anomaly get(int anomalyId) throws SQLException {

		String resultIdColumnName = "ANOMALY_ID";
		ResultSet rs = operation.getRow("ANOMALY", resultIdColumnName, anomalyId);
		Anomaly anomaly = new Anomaly();
		
		while (rs.next()) {
			anomaly.setAnomalyId(rs.getString("ANOMALY_ID"))
					.setDsId(rs.getString("DS_ID"))
					.setServiceId(rs.getString("SERVICE_ID"))
					.setTopicName(rs.getString("topicName"))
					.setTitle(rs.getString("TITLE"))
					.setMessage(rs.getString("MESSAGE"))
					.setCols(Integer.parseInt(rs.getString("FRAME_COLS")))
					.setRows(Integer.parseInt(rs.getString("FRAME_ROWS")))
					.setType(Integer.parseInt(rs.getString("FRAME_TYPE")))
					.setData(rs.getBytes("FRAME_DATA"))
					.setTimestamp(rs.getString("FRAME_TIMESTAMP"))
					.setStartFrameTimestamp(rs.getString("START_FRAME"))
					.setEndFrameTimestamp(rs.getString("END_FRAME"))
					.setPosition(rs.getString("POSITION"))
					.setCreationDate(rs.getString("CREATION_DATE"));
		}
		return anomaly;
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * get a single RESULT
	 * 
	 * @param videoId (FK from VIDEO table)
	 * @return List of RESULT objects
	 * @throws SQLException
	 */
	public static List<Anomaly> getAll(int serviceId) throws SQLException {

		String videoIdColumnName = "VIDEO_ID";
		ResultSet rs = operation.getRow("SERVICE", videoIdColumnName, serviceId);
		
		List<Anomaly> list = new ArrayList<Anomaly>();
		
		Anomaly anomaly = new Anomaly();
		
		while (rs.next()) {
			anomaly.setAnomalyId(rs.getString("ANOMALY_ID"))
					.setServiceId(rs.getString("SERVICE_ID"))
					.setdsId(rs.getString("DS_ID"))
					.setTitle(rs.getString("TITLE"))
					.setMessage(rs.getString("MESSAGE"))
					.setPicture(rs.getString("PICTURE"))
					.setPictureTimeStamp(rs.getString("pciture_timestamp"))
					.setStartFrame(rs.getString("START_FRAME"))
					.setEndFrame(rs.getString("END_FRAME"))
					.setPosition(rs.getString("POSITION"))
					.setCreationDate(rs.getString("CREATION_DATE"));
		}
		return list;
	}
}
