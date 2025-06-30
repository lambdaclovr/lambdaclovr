package lambdaclovr.dsl.service.hbase;

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
import lambdaclovr.dsl.dao.VendorModelDAO;
import lambdaclovr.dsl.model.VendorModel;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: VendorModelService</h3>
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

@Path("/vendormodel")
@Api("/Vendor Model Service")
@SwaggerDefinition(tags = {
		@Tag(name = "Vendor Model Service", description = "REST Endpoint") })
public class VendorModelService {

	/**
	 * POST request to create new vendor model. <br>
	 * http://hostname:port/lambdaclovr/app/vendormodel <br>
	 * RolesAllowed: Logged in user.
	 * 
	 * @param VendorModel
	 *            object
	 * @return VendorModel object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new Vendor Model", notes = "RolesAllowed: Logged in user.")
	public VendorModel create(VendorModel vendorModel) throws Exception {
		VendorModelDAO.create(vendorModel);
		return vendorModel;
	}

	////////////////////////////////////////////////////

	/**
	 * PUT request to update existing VendorModel. <br>
	 * http://hostname:port/lambdaclovr/app/vendormodel <br>
	 * RolesAllowed: Logged in user.
	 * 
	 * @param vendor
	 *            Object
	 * @return vendor object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update an existing Vendor Model", notes = "RolesAllowed: Logged in user.")
	public VendorModel update(VendorModel vendorModel) throws Exception {
		return VendorModelDAO.update(vendorModel);
	}

	/**
	 * GET request to get a list of all vendors. <br>
	 * http://hostname:port/lambdaclovr/app/vendor <br>
	 * RolesAllowed: Admin only.
	 * 
	 * @param Nill
	 * @return List of vendor objects
	 * @throws Exception
	 *
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get Vendor Model list", notes = "RolesAllowed: Admin only.")
	public List<VendorModel> listVendorModel() throws Exception {

		List<VendorModel> listVendorModel = VendorModelDAO.getAll();
		return listVendorModel;
	}
}