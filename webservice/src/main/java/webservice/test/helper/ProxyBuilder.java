package webservice.test.helper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import webservice.ITransactionsService;

public class ProxyBuilder {

	public static ITransactionsService buildProxy() {
		WebTarget webTarget = buildwebTarget();
		ITransactionsService proxy = ((ResteasyWebTarget) webTarget).proxy(ITransactionsService.class);
		return proxy;
	}

	public static WebTarget buildwebTarget() {
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("http://localhost:8081/webservice-0.0.1-SNAPSHOT/rest");
		return webTarget;
	}
}
