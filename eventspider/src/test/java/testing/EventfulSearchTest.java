package testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.Assert.*;

/**
 * @author Sebastian Greenholtz
 */
public class EventfulSearchTest {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://barnivore.com/company/1.json");
    String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

//    ObjectMapper mapper = new ObjectMapper();
//    Response results = mapper.readValue(response, Response.class);
//    Company company = results.getCompany();
//    ProductsItem item = company.getProducts().get(0);
//    assertEquals("Alexander Keith's", company.getCompany_name());
//    assertEquals("Beer", item.getBooze_type());
}