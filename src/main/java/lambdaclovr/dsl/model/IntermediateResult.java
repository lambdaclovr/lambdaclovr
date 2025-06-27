package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IntermediateResult")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: IntermediateResult</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used as an Intermediate Result model.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-07-27
 **/

public class IntermediateResult {
	private int intResultId = 0;
	private int videoId = 0;
	private int algoId = 0;
	private int dsId = 0;
	private String name = "";
	private String picture = "";
	private String description = "";
	private int startFrame = 0;
	private int endFrame = 0;
	private String position = "";
	private String features = "";
	private String creationDate = "";

	public int getIntResultId() {
		return intResultId;
	}

	public IntermediateResult setIntResultId(String intResultId) {
		this.intResultId = Integer.parseInt(intResultId);
		return this;
	}

	public int getVideoId() {
		return videoId;
	}

	public IntermediateResult setVideoId(String videoId) {
		this.videoId = Integer.parseInt(videoId);
		return this;
	}

	public int getAlgoId() {
		return algoId;
	}

	public IntermediateResult setAlgoId(String algoId) {
		this.algoId = Integer.parseInt(algoId);
		return this;
	}

	public int getDsId() {
		return dsId;
	}

	public IntermediateResult setDsId(String dsId) {
		this.dsId = Integer.parseInt(dsId);
		return this;
	}

	public String getName() {
		return name;
	}

	public IntermediateResult setName(String name) {
		this.name = name;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public IntermediateResult setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public IntermediateResult setDescription(String description) {
		this.description = description;
		return this;
	}

	public int getStartFrame() {
		return startFrame;
	}

	public IntermediateResult setStartFrame(String startFrame) {
		this.startFrame = Integer.parseInt(startFrame);
		return this;
	}

	public int getEndFrame() {
		return endFrame;
	}

	public IntermediateResult setEndFrame(String endFrame) {
		this.endFrame = Integer.parseInt(endFrame);
		return this;
	}

	public String getPosition() {
		return position;
	}

	public IntermediateResult setPosition(String position) {
		this.position = position;
		return this;
	}
	
	public String getFeatures() {
		return features;
	}

	public IntermediateResult setFeatures(String features) {
		this.features = features;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public IntermediateResult setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}

}
