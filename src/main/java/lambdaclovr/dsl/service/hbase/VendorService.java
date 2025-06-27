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
import lambdaclovr.dsl.dao.VendorDAO;
import lambdaclovr.dsl.exception.CustomeException;
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
 * @Description: Provide Vendor REST Services.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/vendor")
@Api("/Vendor Service")
@SwaggerDefinition(tags = { @Tag(name = "Vendor Service", description = "REST Endpoint for Vendor Service") })
public class VendorService {

	/**
	 * POST request to create new vendor. <br>
	 * http://hostname:port/lambdaclovr/app/vendor <br>
	 * Access Level: Admin Only
	 * 
	 * @param vendor
	 *            Object
	 * @return vendor object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create new Vendor", notes = "This can only be done by the logged in admin.")
	public Vendor create(Vendor vendor) throws Exception {
		VendorDAO.create(vendor);
		return vendor;
	}

	////////////////////////////////////////////////////

	/**
	 * PUT request to update existing vendor. <br>
	 * http://hostname:port/lambdaclovr/app/vendor <br>
	 * Access Level: Admin Only
	 * 
	 * @param vendor Object
	 * @return vendor object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing Vendor", notes = "Only admin account can update existing Vendor.")
	public Vendor update(Vendor vendor) throws SQLException, CustomeException {
		return VendorDAO.update(vendor);
	}
	
	/**
	 * GET request to get a list of all vendors. <br>
	 * http://hostname:port/lambdaclovr/app/vendor <br>
	 * Access Level: Any logged in user
	 * 
	 * @param Nill
	 * @return List of vendor objects
	 * @throws Exception
	 *
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get vendors list", notes = "This can only be done by the logged in User.")
	public List<Vendor> listVendors() throws Exception {
		
		List<Vendor> listVendors = VendorDAO.getAll();
		return listVendors;
	}
}