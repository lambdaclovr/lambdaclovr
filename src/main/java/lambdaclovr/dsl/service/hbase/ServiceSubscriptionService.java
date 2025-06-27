package lambdaclovr.dsl.service.hbase;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lambdaclovr.dsl.dao.ServiceSubscriptionDAO;
import lambdaclovr.dsl.model.ServiceSubscription;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: ServiceSubscriptionService</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide ServiceSubscription REST Service.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/ServiceSubscription")
@Api("/Service Subscription Service")
@SwaggerDefinition(tags = { @Tag(name = "Service Subscription Service", description = "REST Endpoints") })
public class ServiceSubscriptionService {

	/**
	 * POST request to create a new Subscription. <br>
	 * http://hostname:port/lambdaclovr/app/ServiceSubscription <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param ServiceSubscription
	 *            object
	 * @return ServiceSubscription object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new service", notes = "RolesAllowed: Session user (All).")
	public ServiceSubscription create(ServiceSubscription serviceSubscription) throws Exception {
		ServiceSubscriptionDAO.create(serviceSubscription);
		return serviceSubscription;
	}

	///////////////////////////////////////////////////////

	/**
	 * PUT request to update existing ServiceSubscription. <br>
	 * http://hostname:port/lambdaclovr/app/ServiceSubscription <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param ServiceSubscription
	 *            object
	 * @return ServiceSubscription object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing ServiceSubscription", notes = "RolesAllowed: Session user (All).")
	public ServiceSubscription update(ServiceSubscription serviceSubscription) throws Exception {
		return ServiceSubscriptionDAO.update(serviceSubscription);
	}
}
