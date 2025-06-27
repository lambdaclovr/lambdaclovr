package lambdaclovr.dsl.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: RESULT</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used as a Anomaly model.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 4.0
 * @since 2024-02-05
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Anomaly implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String anomalyId;
	private String dsId;
	private String serviceId;
	private String topicName;

	private String title;
	private String message;

	// For Image Encodeing
	private int cols = 0;
	private int rows = 0;
	private int type = 0;
	private byte[] data;
	private String timestamp;

	// Start and End Frame Timestamp of a camera
	private String startFrameTimestamp = "";
	private String endFrameTimestamp = "";
	private String position = "";
	private String creationDate = "";


	public String getAnomalyId() {
		return anomalyId;
	}

	public Anomaly setAnomalyId(String anomalyId) {
		this.anomalyId = anomalyId;
		return this;
	}

	public String getDsId() {
		return dsId;
	}

	public Anomaly setDsId(String dsId) {
		this.dsId = dsId;
		return this;
	}

	public String getServiceId() {
		return serviceId;
	}

	public Anomaly setServiceId(String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	public String getTopicName() {
		return topicName;
	}

	public Anomaly setTopicName(String topicName) {
		this.topicName = topicName;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Anomaly setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Anomaly setMessage(String message) {
		this.message = message;
		return this;
	}

	public int getCols() {
		return cols;
	}

	public Anomaly setCols(int cols) {
		this.cols = cols;
		return this;
	}

	public int getRows() {
		return rows;
	}

	public Anomaly setRows(int rows) {
		this.rows = rows;
		return this;
	}

	public int getType() {
		return type;
	}

	public Anomaly setType(int type) {
		this.type = type;
		return this;
	}

	public byte[] getData() {
		return data;
	}

	public Anomaly setData(byte[] data) {
		this.data = data;
		return this;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Anomaly setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public String getStartFrameTimestamp() {
		return startFrameTimestamp;
	}

	public Anomaly setStartFrameTimestamp(String startFrameTimestamp) {
		this.startFrameTimestamp = startFrameTimestamp;
		return this;
	}

	public String getEndFrameTimestamp() {
		return endFrameTimestamp;
	}

	public Anomaly setEndFrameTimestamp(String endFrameTimestamp) {
		this.endFrameTimestamp = endFrameTimestamp;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public Anomaly setPosition(String position) {
		this.position = position;
		return this;
	}
    public String getCreationDate() {
        return creationDate;
    }

    public Anomaly setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }
	

}