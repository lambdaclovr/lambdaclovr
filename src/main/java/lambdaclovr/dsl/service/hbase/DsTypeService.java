package lambdaclovr.dsl.service.hbase;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lambdaclovr.dsl.dao.DsTypeDAO;
import lambdaclovr.dsl.dao.VendorDAO;
import lambdaclovr.dsl.exception.CustomeException;
import lambdaclovr.dsl.model.DsType;
import lambdaclovr.dsl.model.Vendor;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: VendorService</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide DsType REST Services.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/dstype")
@Api("/Data Source Type Service")
@SwaggerDefinition(tags = { @Tag(name = "Data Source Type Service", description = "REST Endpoint") })
public class DsTypeService {

	/**
	 * POST request to create new DsType. <br>
	 * http://hostname:port/lambdaclovr/app/dstype <br>
	 * Access Level: Admin Only
	 * 
	 * @param DsType Object
	 * @return DsType Object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create new Vendor", notes = "RolesAllowed: Session user (Admin).")
	public DsType create(DsType dsType) throws Exception {
		DsTypeDAO.create(dsType);
		return dsType;
	}

	////////////////////////////////////////////////////

	/**
	 * PUT request to update existing DsType. <br>
	 * http://hostname:port/lambdaclovr/app/dstype <br>
	 * Access Level: Admin Only
	 * 
	 * @param DsType Object
	 * @return DsType object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing Vendor", notes = "RolesAllowed: Session user (Admin).")
	public DsType update(DsType dsType) throws SQLException, CustomeException {
		return DsTypeDAO.update(dsType);
	}
	
	/**
	 * GET request to get a list of all vendors. <br>
	 * http://hostname:port/lambdaclovr/app/dstype <br>
	 * Access Level: Any logged in user
	 * 
	 * @param Nill
	 * @return List of vendor objects
	 * @throws Exception
	 *
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get DsType list", notes = "RolesAllowed: Session user (Admin, Developer).")
	public List<DsType> listDsType() throws Exception {
		
		List<DsType> listDsType = DsTypeDAO.getAll();
		return listDsType;
	}
	
}