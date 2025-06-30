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
import lambdaclovr.dsl.dao.VideoDAO;
import lambdaclovr.dsl.model.Video;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: VideoService</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide Video REST Service.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/video")
@Api("/Video Service")
@SwaggerDefinition(tags = { @Tag(name = "Video Service", description = "REST Endpoint for Vidoe Service") })
public class VideoService {

	/**
	 * POST request to create a new Video. <br>
	 * http://host:port/lambdaclovr/app/video <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param Video
	 *            object
	 * @return Video object
	 * @throws Exception
	 *
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new video", notes = "RolesAllowed: Session user (all).")
	public Video create(Video video) throws Exception {
		VideoDAO.create(video);
		return video;
	}

	///////////////////////////////////////////////////////

	/**
	 * PUT request to update existing video. <br>
	 * http://hostname:port/lambdaclovr/app/video <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param Video
	 *            object
	 * @return Video object
	 * @throws Exception
	 *
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing video", notes = "RolesAllowed: Session user (all).")
	public Video update(Video video) throws Exception {
		return VideoDAO.update(video);
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a list of all the Video in a data source. <br>
	* http://hostname:port/lambdaclovr/app/video/dsId <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param dsId
	 * @return Video list of objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/in/{dsId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a list of Videos in a Data Source", notes = "RolesAllowed: Session user (All).")
	public List<Video> getDsVideos(@PathParam("dsId") String dsId) throws Exception {
		List<Video> listVideos = VideoDAO.getDsVideos(Integer.parseInt(dsId));
		return listVideos;
	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a single Video in a Data Source. <br>
	 * http://hostname:port/lambdaclovr/app/video/videoId <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param videoId
	 * @return Video objects
	 * @throws Exception
	 *
	 */

	@GET
	@Path("/{videoId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a Video", notes = "RolesAllowed: Session user (All).")
	public Video get(@PathParam("videoId") String videoId) throws Exception {
		return VideoDAO.get(Integer.parseInt(videoId));
	}

}
