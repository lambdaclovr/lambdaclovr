package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceSubscription")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: Service</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used as a ServiceSubscription model.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-06-06
 **/
public class ServiceSubscription {

	private int sSId = 0;
	private int dSId = 0;
	private int serviceId = 0;
	private int status = 0;
	private String subscriptionDate = "";
	private String subscriptionStartDate = "";
	private String subscriptionStopDate = "";

	public int getsSId() {
		return sSId;
	}

	public ServiceSubscription setsSId(String sSId) {
		this.sSId = Integer.parseInt(sSId);
		return this;
	}

	public int getServiceId() {
		return serviceId;
	}

	public ServiceSubscription setServiceId(String serviceSubscriptionId) {
		this.serviceId = Integer.parseInt(serviceSubscriptionId);
		return this;
	}

	public int getDsId() {
		return dSId;
	}

	public ServiceSubscription setDsId(String dsId) {
		this.dSId = Integer.parseInt(dsId);
		return this;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public ServiceSubscription setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
		return this;
	}

	public String getSubscriptionStartDate() {
		return subscriptionStartDate;
	}

	public ServiceSubscription setSubscriptionStartDate(String subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
		return this;
	}

	public String getSubscriptionStopDate() {
		return subscriptionStopDate;
	}

	public ServiceSubscription setSubscriptionStopDate(String subscriptionStopDate) {
		this.subscriptionStopDate = subscriptionStopDate;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public ServiceSubscription setStatus(String status) {
		this.status = Integer.parseInt(status);
		return this;
	}
}
