package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Algorithm")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: ALGORITHM</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used as an ALGORITHM model.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-07-27
 **/

public class Algorithm {
	private int algoId = 0;
	private int userId = 0;
	private int inputTypeId = 0;
	private int scId = 0; // Source Code ID

	private String name = "";
	private String algorithmId = ""; // e.g., algo://UserName/AlgoName
	private String resources = ""; // CPU or GPU
	private String creationDate = "";

	public int getAlgoId() {
		return algoId;
	}

	public Algorithm setAlgoId(String algoId) {
		this.algoId = Integer.parseInt(algoId);
		return this;
	}

	public int getUserId() {
		return userId;
	}

	public Algorithm setUserId(String userId) {
		this.userId = Integer.parseInt(userId);
		return this;
	}

	public int getInputTypeId() {
		return inputTypeId;
	}

	public Algorithm setInputTypeId(String inputTypeId) {
		this.inputTypeId = Integer.parseInt(inputTypeId);
		return this;
	}

	public int getScId() {
		return scId;
	}

	public Algorithm setScId(String scId) {
		this.scId = Integer.parseInt(scId);
		return this;
	}

	public String getName() {
		return name;
	}

	public Algorithm setName(String name) {
		this.name = name;
		return this;
	}

	public String getAlgorithmId() {
		return algorithmId;
	}

	public Algorithm setAlgorithmId(String algorithmId) {
		this.algorithmId = algorithmId;
		return this;
	}

	public String getResources() {
		return resources;
	}

	public Algorithm setResources(String resources) {
		this.resources = resources;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public Algorithm setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}

}
