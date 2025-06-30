package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DsType")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.model</h3>
 * <h3>Class Name: DsType</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used as a Vendor model.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4
 **/
public class DsType {
	private int dsTypeId = 0;
	private String name = "";
	private String description = "";

	public int getDsTypeId() {
		return dsTypeId;
	}

	public DsType setDsTypeId(String dsTypeId) {
		this.dsTypeId = Integer.parseInt(dsTypeId);
		return this;
	}

	public String getName() {
		return name;
	}

	public DsType setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public DsType setDescription(String description) {
		this.description = description;
		return this;
	}

}
