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
import lambdaclovr.dsl.dao.DataSourceDAO;
import lambdaclovr.dsl.model.DataSource;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: DataSourceService</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide DataSource REST Service.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/datasouce")
@Api("/Data Souce Service")
@SwaggerDefinition(tags = { @Tag(name = "Data Souce Service", description = "REST Endpoint for Data Souce Service") })
public class DataSourceService {

	/**
	 * POST request to create a new DataSource. <br>
	 * http://host:port/lambdaclovr/app/datasouce/ <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param DataSource
	 *            object
	 * @return DataSource object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new Data Source", notes = "RolesAllowed: Session user (all).")
	public DataSource create(DataSource dataSource) throws Exception {
		DataSourceDAO.create(dataSource);
		return dataSource;
	}

	///////////////////////////////////////////////////////

	/**
	 * POST request to create a new DataSource. <br>
	 * http://host:port/lambdaclovr/app/datasouce/ <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param DataSource
	 *            object
	 * @return DataSource object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update an existing Data Source", notes = "RolesAllowed: Session user (all).")
	public DataSource update(DataSource dataSource) throws Exception {

		return DataSourceDAO.update(dataSource);
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a list of DataSources. <br>
	 * http://host:port/lambdaclovr/app/datasouce/ <br>
	 * RolesAllowed: Session user (Admin).
	 * 
	 * @param DataSource
	 *            Nill
	 * @return DataSource list of objects
	 * @throws Exception
	 *
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a list of Data Source", notes = "RolesAllowed: Session user (Admin).")
	public List<DataSource> getAll() throws Exception {
		List<DataSource> listDataSources = DataSourceDAO.getAll();
		return listDataSources;
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a Data Source. <br>
	 * http://host:port/lambdaclovr/app/datasouce/{ds_id} <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param Nill
	 * @return DataSource objects
	 * @throws Exception
	 *
	 */

	@GET
	@Path("/{ds_id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a DataSource", notes = "RolesAllowed: Session user (All).")
	public DataSource get(@PathParam("ds_id") String ds_id) throws Exception {
		return DataSourceDAO.get(Integer.parseInt(ds_id));
	}

}
