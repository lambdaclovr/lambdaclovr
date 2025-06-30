package lambdaclovr.dsl.service.hbase;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.minidev.json.JSONObject;
import lambdaclovr.dsl.dao.UserDAO;
import lambdaclovr.dsl.exception.CustomeException;
import lambdaclovr.dsl.model.User;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.service</h3>
 * <h3>Class Name: UserService</h3>
 * 
 * <p>
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: Provide User REST Service.
 *          </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4 The version since the feature was added. 
 **/

@Path("/users")
@Api("/User Service")
@SwaggerDefinition(tags = { @Tag(name = "User Service", description = "REST Endpoint for User Service") })
public class UserService {
	/**
	 * Respond to get request. http://host:port/lambdaclovr/app/users
	 * 
	 * @param Nill
	 * @return List of Users (JSon Object)
	 * @throws Exception
	 * 
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "List Users", notes = "RolesAllowed: Session user (Admin).")
	public String getUsers_JSON() throws Exception {
		JSONObject listUsersJson = new JSONObject();
		
		ObjectMapper mapper = new ObjectMapper();
		List<User> userList = new ArrayList<User>();
		userList = UserDAO.getAll();

		if (userList.size() > 0) {
			listUsersJson.put("Info", mapper.writeValueAsString(userList));
			listUsersJson.put("status", "1"); // 1=> success , 0=>error
			listUsersJson.put("message", "List of the requested users");
		} else {
			listUsersJson.put("status", "0"); // 1=> success , 0=>error
			listUsersJson.put("message", "No user found.");
		}

		// System.out.println(generalJsonObject.get("message"));
		return listUsersJson.toString();
	}

	///////////////////////////////////////////////////

	/**
	 * Response to get request. http://host:port/lambdaclovr/rest/users/{email}
	 * 
	 * @param email
	 * @return return a user if found against the email.
	 * @throws Exception
	 * 
	 */

	@GET
	@Path("/{email}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Get a User", notes = "RolesAllowed: Session user (All).")
	public String getUser(@PathParam("email") String email) throws Exception {

		User u = UserDAO.get(email);
		JSONObject userJsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		
		if (u.getFirstName().length() > 1) {
			userJsonObject.put("status", "1");
			userJsonObject.put("message", "Requested Information.");
			userJsonObject.put("info", mapper.writeValueAsString(u));
		} else {
			userJsonObject.put("status", "0");
			userJsonObject.put("message", "Invalid email address.");

		}

		return userJsonObject.toString();
	}

	////////////////////////////////////////////////////

	/**
	 * POST request. 
	 * http://host:port/lambdaclovr/app/users
	 * 
	 * @param User Object
	 * @return User object
	 * @throws Exception
	 * 
	 */

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Create new user", notes = "RolesAllowed: (All).")
	public String create(User userInfo) throws Exception {

		JSONObject user = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean affecteduser = UserDAO.create(userInfo);
		
		if(affecteduser) {
			user.put("info", mapper.writeValueAsString(userInfo));
			user.put("message", "New account created successfully.");
			user.put("status", 1);
			
		}
		else {
			user.put("message", "Cannot create new user account,");
			user.put("status", 0);
		}

		return user.toString();
	}
	
	
	////////////////////////////////////////////////////
	/**
	 * POST request to update user information. 
	 * http://host:port/lambdaclovr/app/users
	 * 
	 * @param User Object
	 * @return User object
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws Exception
	 * 
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Update existing User", notes = "RolesAllowed: (All).")
	public String update(User user) throws SQLException, CustomeException, JsonGenerationException, JsonMappingException, JSONException, IOException {

		JSONObject jUser = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean affecteduser=UserDAO.update(user);
		if(affecteduser) {
			jUser.put("info", mapper.writeValueAsString(user));
			jUser.put("message", "Updated successfully");
			jUser.put("status", 1);
			
		}
		else {
			jUser.put("message", "Error");
			jUser.put("status", 0);
		}
		return jUser.toString();
}

	////////////////////////////////////////////////////

	/**
	 * POST request for login. 
	 * http://hostname:portNo/lambdaclovr/app/users
	 * 
	 * @param Email, Password
	 * @return User object
	 * @throws Exception
	 * 
	 */
	@POST
	@Path("/login")
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Allow user for login", notes = "RolesAllowed: All Users.")
	public String login(@FormParam("email") String email, @FormParam("password") String password) throws Exception {

		User user = new User();
		user = UserDAO.get(email);
		
		// encrypt the password provided by the user in order to compare
		System.out.println("Raw password:  " + password + "  and Encrypted: ");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(password.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String encrypted_password = number.toString(16);
		
		System.out.println("encrypted_password  " + encrypted_password);
		System.out.println("user Password is  " + user.getPassword());
		
		JSONObject userJsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();

		if (user.getFirstName().length() > 1) {

			if (user.getPassword().equals(encrypted_password)) {
				userJsonObject.put("status", "1");
				userJsonObject.put("message", "Logged in successfully.");
				userJsonObject.put("info", mapper.writeValueAsString(user));
			} else {
				userJsonObject.put("status", "0");
				userJsonObject.put("message",
						"Incorrect Information. Please provide corect information.");
			}
		} else {
			userJsonObject.put("status", "0");
			userJsonObject.put("message",
					"Incorrect Information. Please provide corect information.");
		}
		return userJsonObject.toString();	
	}

	/**
	 * PUT request to update user level. 
	 * http://host:port/lambdaclovr/app/users/developerRequest/{email}
	 * 
	 * @param email
	 * @return  
	 * @throws Exception
	 * 
	 */
//	@PUT
//	@Path("/developerRequest/{email}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public User developerRequest(@PathParam("email") String userEmail) throws SQLException, CustomeException {
//		User u = UserDAO.get(userEmail);
//		u.setUserRole(2);
//		return UserDAO.update(u);
//	}

}
