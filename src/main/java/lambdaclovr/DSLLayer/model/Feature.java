package lambdaclovr.dsl.model;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: Feature</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p><p>
 * @Description: This class is used as a Feature model.
 *</p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-05-30
 **/
public class Feature {
	private int featureId = 0;
	private int videoId = 0;
	private String featureName = "";
	private String featureVector = "";
	private String creationDate = "";

	public int getFeatureId() {
		return featureId;
	}

	public Feature setFeatureId(String featureId) {
		this.featureId = Integer.parseInt(featureId);
		return this;
	}

	public int getVideoId() {
		return videoId;
	}

	public Feature setVideoId(String videoId) {
		this.videoId = Integer.parseInt(videoId);
		return this;
	}

	public String getFeatureName() {
		return featureName;
	}

	public Feature setFeatureName(String featureName) {
		this.featureName = featureName;
		return this;
	}

	public String getFeatureVector() {
		return featureVector;
	}

	public Feature setFeatureVector(String featureVector) {
		this.featureVector = featureVector;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public Feature setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}
}
