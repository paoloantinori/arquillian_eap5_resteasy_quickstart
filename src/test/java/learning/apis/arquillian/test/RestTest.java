package learning.apis.arquillian.test;

import java.net.URL;

import javax.ws.rs.core.MediaType;

import learning.apis.arquillian.rest.sample.SampleBean;
import learning.apis.arquillian.rest.sample.SampleRest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Arquillian Tests have these requirements:
 * 
 * 1) A @RunWith(Arquillian.class) annotation on the class 2) A public static
 * method annotated with @Deployment that returns a ShrinkWrap archive 3) At
 * least one method annotated with @Test
 * 
 * The @RunWith annotation tells JUnit to use Arquillian as the test controller.
 * Arquillian then looks for a public static method annotated with the @Deployment
 * annotation to retrieve the test archive (i.e., micro-deployment). Then some
 * magic happens and each @Test method is run inside the container environment.
 * 
 * ShrinkWrap is a Java API for creating archives (e.g., jar, war, ear) in Java.
 * The purpose of the test archive is to isolate the classes and resources which
 * are needed by the test from the remainder of the classpath. The
 * micro-deployment strategy let’s you focus on precisely the classes you want
 * to test. As a result, the test remains very lean and manageable.
 * 
 * 
 * @author paolo.antinori@gmail.com
 * 
 */

@RunWith(Arquillian.class)
@RunAsClient
public class RestTest {

	protected final static Logger logger = LoggerFactory
			.getLogger(RestTest.class.getName());

	@ArquillianResource
	URL deploymentUrl;

	@Deployment(testable = false)
	public static WebArchive create() {

		WebArchive setWebXML = ShrinkWrap.create(WebArchive.class)
				.addPackage(SampleRest.class.getPackage())
				.addClass(SampleBean.class).setWebXML("WEB-INF/web.xml");

		return setWebXML;

	}

	@Test
	public void testRestEasyOk() throws Exception {

		String endpoint = deploymentUrl.toString() + "api/test/ok";

		logger.info("Invoking: " + endpoint);
		
		
		ClientRequest request = new ClientRequest(endpoint);
		request.header("Accept", MediaType.APPLICATION_JSON);

		// we're expecting a String back
		ClientResponse<String> responseObj = request.get(String.class);

		
		Assert.assertEquals(200, responseObj.getStatus());
	
		String response = responseObj.getEntity().trim();

		Assert.assertEquals("OK", response);

	}

	@Test
	public void testRestEasyKo() throws Exception {

		String endpoint = deploymentUrl.toString() + "api/test/ko";

		logger.info("Invoking: " + endpoint);
		ClientRequest request = new ClientRequest(endpoint);

		request.header("Accept", MediaType.APPLICATION_JSON);

		// we're expecting a String back
		ClientResponse<String> responseObj = request.get(String.class);

		Assert.assertEquals(404, responseObj.getStatus());

	}

	@Test
	public void testRestEasyFatalError() throws Exception {

		String endpoint = deploymentUrl.toString() + "api/test/fatal_error";

		logger.info("Invoking: " + endpoint);
		ClientRequest request = new ClientRequest(endpoint);

		request.header("Accept", MediaType.APPLICATION_JSON);

		// we're expecting a String back
		ClientResponse<String> responseObj = request.get(String.class);

		Assert.assertEquals(500, responseObj.getStatus());

	}
	

	@Test
	public void testEntity() throws Exception {

		String endpoint = deploymentUrl.toString() + "api/test_entity";

		logger.info("Invoking: " + endpoint);
		ClientRequest request = new ClientRequest(endpoint);
		request.header("Accept",  MediaType.APPLICATION_XML);

		ClientResponse<SampleBean> responseObj = request.get(SampleBean.class);
		SampleBean entity = responseObj.getEntity(SampleBean.class);

		Assert.assertEquals("200 Status expected", 200, responseObj.getStatus());
		Assert.assertEquals("Wrong Property Returned", 12345, entity.getMyInteger());
		Assert.assertEquals("Wrong Property Returned", "myString", entity.getMyString());

	}


}
