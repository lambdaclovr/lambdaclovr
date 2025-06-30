package lambdaclovr.dsl.service.hbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/Test")
@Api("/Testing Service")
@SwaggerDefinition(tags = { @Tag(name = "Test Service", description = "REST Endpoint for Product Service") })
public class Test {

	@Context
	private ServletContext context;
	// @GET
	// @Produces({MediaType.TEXT_PLAIN})
	// public String getCurrency(@QueryParam("key") String key) {
	//
	// String returnValue = null;
	//
	// if( key == null || key.isEmpty() ) return returnValue;
	//
	// String realPath = context.getRealPath("/WEB-INF/kafkaproducer.properties");
	// try {
	// Properties props = new Properties();
	// props.load(new FileInputStream(new File(realPath)));
	// returnValue = props.getProperty(key);
	//
	// } catch (IOException ex) {
	// // Logger.getLogger(UsersEntryPoint.class.getName()).log(Level.SEVERE, null,
	// ex);
	// System.out.println();
	// }
	//
	// return returnValue;
	// }

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Updated User", notes = "This can only be done by the logged in user.")
	public String sayPlainTextHello() throws SQLException {
		
		//File read
		String returnValue = null;
		String key = "prop";
		if (key == null || key.isEmpty())
			return returnValue;
		String realPath = context.getRealPath("/WEB-INF/kafkaproducer.properties");
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(new File(realPath)));
			returnValue = props.getProperty(key);
		} catch (IOException ex) {
			// Logger.getLogger(UsersEntryPoint.class.getName()).log(Level.SEVERE, null, ex);
		}
		return returnValue;
		// return "Publish Message";
	}
}