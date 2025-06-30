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
import lambdaclovr.dsl.dao.IntermediateResultDAO;
import lambdaclovr.dsl.model.Algorithm;
import lambdaclovr.dsl.model.IntermediateResult;

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
 * @Description: Provide IntermediateResult REST Service.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/IntermediateResult")
@Api("/Intermediate Result Service")
@SwaggerDefinition(tags = { @Tag(name = "Intermediate Result Service", description = "REST Endpoint") })
public class IntermediateResultService {

	/**
	 * POST request to create a new Intermediate Result. <br>
	 * http://hostname:port/lambdaclovr/app/IntermediateResult <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param IntermediateResult object
	 * @return IntermediateResult object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new Intermediate Result", notes = "RolesAllowed: Session user (Admin, Developer).")
	public IntermediateResult create(IntermediateResult intermediateResult) throws Exception {
		IntermediateResultDAO.create(intermediateResult);
		return intermediateResult;
	}

	///////////////////////////////////////////////////////

	/**
	 * PUT request to update an existing IntermediateResult. <br>
	 * http://hostname:port/lambdaclovr/app/IntermediateResult <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param IntermediateResult
	 *            object
	 * @return IntermediateResult object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing IntermediateResult", notes = "RolesAllowed: Session user (Admin, Developer).")
	public IntermediateResult update(IntermediateResult intermediateResult) throws Exception {
		IntermediateResultDAO.update(intermediateResult);
		return intermediateResult;
	}


	////////////////////////////////////////////////////////
	/**
	 * GET request to get a single record from IntermediateResult table. <br>
	 * http://hostname:port/lambdaclovr/app/IntermediateResult/{intResultId} <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param intResultId
	 * @return IntermediateResult Objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/{intResultId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get an Intermediate Result", notes = "RolesAllowed: Session user (All).")
	public IntermediateResult get(@PathParam("intResultId") String intResultId) throws Exception {
		return IntermediateResultDAO.get(Integer.parseInt(intResultId));
	}
	
	////////////////////////////////////////////////////////
	/**
	 * GET request: get a list of all the Intermediate Result of a video. <br>
	 * http://hostname:port/lambdaclovr/app/IntermediateResult/ofVideo/{videoId} <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param dsId
	 * @return Video list of objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/ofVideo/{videoId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a list of Intermediate Result", notes = "RolesAllowed: Session user (All).")
	public List<IntermediateResult> getVideoIntermediateResult(@PathParam("videoId") String videoId) throws Exception {
		List<IntermediateResult> listIntermediateResult = IntermediateResultDAO.getVideoIntermediateResult(Integer.parseInt(videoId));
		return listIntermediateResult;
	}
	
}
