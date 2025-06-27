package lambdaclovr.APIsManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.ext.RuntimeDelegate;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConfigurationServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		BeanConfig beanConfig = new BeanConfig();
		
		beanConfig.setBasePath("lambdaclovr/app");
		beanConfig.setHost("host:port");
		beanConfig.setResourcePackage("lambdaclovr.dsl");
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
		beanConfig.setSchemes(new String[] {"http"});
		beanConfig.setVersion("1.0");
		beanConfig.setTitle("LAMBDA-CLOVR App Swagger Docs");		
		RuntimeDelegate.setInstance(new org.glassfish.jersey.internal.RuntimeDelegateImpl());
	}

}
