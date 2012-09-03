package learning.apis.arquillian.rest.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author paolo.antinori@gmail.com
 */
@Path("/api")
public class SampleRest {

	@GET
	@Path("/test/{whatever}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test(@PathParam(value = "whatever") String whatever) {
		try {
			if ("KO".equalsIgnoreCase(whatever))
				throw new RuntimeException(
						"Manually Raised");
			else if ("OK".equalsIgnoreCase(whatever)) {
				return Response.ok().entity("OK").build();
			} else {
				return Response.serverError().build();
			}

		} catch (RuntimeException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/test_entity")
	@Produces(MediaType.APPLICATION_XML)
	public SampleBean test() {
		SampleBean result = new SampleBean();
		result.setMyInteger(12345);
		result.setMyString("myString");
		return result;
	}

}