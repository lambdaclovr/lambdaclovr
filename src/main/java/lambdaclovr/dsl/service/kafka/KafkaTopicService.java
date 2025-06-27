package lambdaclovr.dsl.service.kafka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.model.TopicModel;
import lambdaclovr.dsl.data.acquisition.streaming.operations.client.kafka.topic.TopicManager;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service.kafka;</h3>
 * <h3>Class Name: Topic</h3>
 * 
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide Kafka Topic Management REST Services.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added.
 **/

@Path("/KafkaTopic")
@Api("/Kafka Topic Service")
@SwaggerDefinition(tags = {
		@Tag(name = "Kafka Topic Management Service", description = "REST Endpoint for Kafak Topic Management") })
public class KafkaTopicService {

	////////////////////////////////////////////////////////

	/**
	 * GET request to get the details of a topic on the Kafka Broker server (Admin).
	 * <br>
	 * http://hostname:port/lambdaclovr/app/topics <br>
	 * RolesAllowed: Session user (Admin, Developer).
	 * 
	 * @param Topic
	 *            Object
	 * @return Topic Object
	 * @throws Exception
	 * 
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create a new Kafka Topic", notes = "RolesAllowed: Session user (Admin, Developer).")
	public TopicModel createTopic(TopicModel topicModel) throws Exception {

		String topicName = topicModel.getTopicName();
		int PARTITIONS = topicModel.getPartitions();
		int REPLICATION_FACTOR = topicModel.getReplicationFactor();

		System.out.println(topicName);
		System.out.println(PARTITIONS);
		System.out.println(REPLICATION_FACTOR);

		try {
			//TopicManager.createTopic(topicName, PARTITIONS, REPLICATION_FACTOR);
			TopicManager.createTopic("vidCam2", 1, 1);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return topicModel;

	}

	////////////////////////////////////////////////////////

	/**
	 * GET request to delete a topic from the Kafka Broker server (Admin). <br>
	 * http://hostname:port/lambdaclovr/app/topics/{topicName} <br>
	 * RolesAllowed: Session user (Admin).
	 * 
	 * @param topicName
	 * @return topicObject
	 * @throws Exception
	 *
	 */
	// @GET
	// @Path("/delete/{topicName}")
	// @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	// @ApiOperation(value = "Get a list of Algorithm", notes = "RolesAllowed:
	// Session user (Admin, Developer).")
	// public List<Algorithm> getUserAlgorithms(@PathParam("userId") String userId)
	// throws Exception {
	// List<Algorithm> listAlgorithm =
	// AlgorithmDAO.getUserAlgorithms(Integer.parseInt(userId));
	// return listAlgorithm;
	// }

	////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////

	/**
	 * GET request to delete a topic from the Kafka Broker server (Admin). <br>
	 * http://hostname:port/lambdaclovr/app/topics/{topicName} <br>
	 * RolesAllowed: Session user (Admin).
	 * 
	 * @param topicName
	 * @return topicObject
	 * @throws Exception
	 *
	 */
	@DELETE
	@Path("delete/{topicName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Delete Kafka Topic", notes = "RolesAllowed: Session user (Admin, Developer).")
	public TopicModel deleteTopic(@PathParam("topicName") String topicName) throws Exception {

		TopicModel topicModel = new TopicModel();
		topicModel.setTopicName(topicName);
		boolean isDeleted = TopicManager.deleteTopic(topicModel.getTopicName());
		topicModel.setIsDeleted(isDeleted);

		System.out.println(topicModel.getTopicName());
		System.out.println(topicModel.getIsDeleted());

		return topicModel;
	}

	////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////

	/**
	 * GET request to get a list of Kafak Topics(Admin). <br>
	 * http://hostname:port/lambdaclovr/app/topics/ <br>
	 * RolesAllowed: Session user (All).
	 * 
	 * @param null
	 * @return list of objects
	 * @throws Exception
	 *
	 */
	@GET
	@Path("/all/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a list of Algorithm", notes = "RolesAllowed: Session user (Admin, Developer).")
	public List<TopicModel> getAllTopics() throws Exception {

		// Get all topics
		List<String> allTopics = TopicManager.getAllTopics();
		Iterator<String> iteratorTopics = allTopics.iterator();

		List<TopicModel> topicList = new ArrayList<>();

		while (iteratorTopics.hasNext()) {
			TopicModel topicModel = new TopicModel();
			topicModel.setTopicName((String) iteratorTopics.next());

			System.out.println(topicModel.getTopicName());
			topicList.add(topicModel);
		}

		return topicList;
	}

	////////////////////////////////////////////////////////

}