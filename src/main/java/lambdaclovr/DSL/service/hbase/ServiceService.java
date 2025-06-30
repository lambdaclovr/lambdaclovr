package lambdaclovr.dsl.service.hbase;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lambdaclovr.dsl.dao.ServiceDAO;
import lambdaclovr.dsl.model.Service;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: ServiceServic</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide Service REST Service.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/Services")
@Api("/Service Service")
@SwaggerDefinition(tags =  { @Tag(name = "Service Service", description = "REST Endpoints") })
public class ServiceService {

	/**
	 * POST request to create a new Service. <br>
	 * http://hostname:port/lambdaclovr/app/Service <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param Service object
	 * @return Service object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new service", notes = "RolesAllowed: Session user (Admin, Developer).")
	public Service create(Service service) throws Exception {
		ServiceDAO.create(service);
		return service;
	}

	///////////////////////////////////////////////////////

	/**
	 * PUT request to update existing video. <br>
	 * http://hostname:port/lambdaclovr/app/Service <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param Service
	 *            object
	 * @return Service object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing Service", notes = "RolesAllowed: Session user (Admin, Developer).")
	public Service update(Service service) throws Exception {
		return ServiceDAO.update(service);
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a list of all the Video in a data source. <br>
	* http://hostname:port/lambdaclovr/app/Service/of/userId <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param userId
	 * @return Service: list of objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/of/{userId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a list of User's Services", notes = "RolesAllowed: Session user (Admin, Developer).")
	public List<Service> getUserServicesList(@PathParam("userId") String userId) throws Exception {
		
		List<Service> listServices = ServiceDAO.getList(Integer.parseInt(userId));
		
		return listServices;
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a single Video in a Data Source. <br>
	 * http://hostname:port/lambdaclovr/app/Service/serviceId <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param serviceId
	 * @return Service objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/{serviceId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a Service", notes = "RolesAllowed: Session user (Admin, Developer).")
	public Service get(@PathParam("serviceId") String serviceId) throws Exception {
		return ServiceDAO.get(Integer.parseInt(serviceId));
	}
	
}
