package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Service")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: Service</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 * </p><p>
 * @Description: This class is used as a Service model.
 *</p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-06-02
 **/
public class Service {
	private int serviceId = 0;
	private int userId = 0;
	private int dsTypeId = 0;
	private int serviceTypeId = 0;
	private String serviceName = "";
	private String description = "";
	private String creationDate = "";

	public int getServiceId() {
		return serviceId;
	}

	public Service setServiceId(String serviceId) {
		this.serviceId = Integer.parseInt(serviceId);
		return this;
	}

	public int getUserId() {
		return userId;
	}

	public Service setUserId(String userId) {
		this.userId = Integer.parseInt(userId);
		return this;
	}

	public int getDsTypeId() {
		return dsTypeId;
	}

	public Service setDsTypeId(String dsTypeId) {
		this.dsTypeId = Integer.parseInt(dsTypeId);
		return this;
	}

	public String getServiceName() {
		return serviceName;
	}

	public Service setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Service setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public Service setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public Service setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = Integer.parseInt(serviceTypeId);
		return this;
	}
}
