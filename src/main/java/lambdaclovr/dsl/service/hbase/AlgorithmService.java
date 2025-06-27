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
import lambdaclovr.dsl.dao.AlgorithmDAO;
import lambdaclovr.dsl.model.Algorithm;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: AlgorithmService</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide AlgorithmService REST Service.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/Algorithm")
@Api("/Algorithm Service")
@SwaggerDefinition(tags = { @Tag(name = "Algorithm Service", description = "REST Endpoint for Algorithm Service") })
public class AlgorithmService {

	/**
	 * POST request to create a new Algorithm. <br>
	 * http://hostname:port/lambdaclovr/app/Algorithm <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param AlgorithmService object
	 * @return AlgorithmService object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new Algorithm", notes = "RolesAllowed: Session user (Admin, Developer).")
	public Algorithm create(Algorithm algorithm) throws Exception {
		AlgorithmDAO.create(algorithm);
		return algorithm;
	}

	///////////////////////////////////////////////////////

	/**
	 * PUT request to update existing Algorithm. <br>
	 * http://hostname:port/lambdaclovr/app/Algorithm <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param Video
	 *            object
	 * @return Video object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing video", notes = "RolesAllowed: Session user (Admin, Developer).")
	public Algorithm update(Algorithm algorithm) throws Exception {
		AlgorithmDAO.update(algorithm);
		return algorithm;
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a list of all the Algorithms of a user(Admin, Developer).  <br>
	 * http://hostname:port/lambdaclovr/app/Algorithm/ofUser/{SessionUserId} <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param dsId
	 * @return Video list of objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/ofUser/{userId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a list of Algorithm", notes = "RolesAllowed: Session user (Admin, Developer).")
	public List<Algorithm> getUserAlgorithms(@PathParam("userId") String userId) throws Exception {
		List<Algorithm> listAlgorithm = AlgorithmDAO.getUserAlgorithms(Integer.parseInt(userId));
		return listAlgorithm;
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a single Video in a Data Source. <br>
	 * http://hostname:port/lambdaclovr/app/Algorithm/algoId <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param algoId
	 * @return Algorithm Objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/{algoId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a Video", notes = "RolesAllowed: Session user (Admin, Developer).")
	public Algorithm get(@PathParam("algoId") String algoId) throws Exception {
		return AlgorithmDAO.get(Integer.parseInt(algoId));
	}

}
