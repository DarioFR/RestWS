package webservice.test;

import org.junit.Test;
import webservice.ITransactionsService;
import webservice.request.StoreTransactionRequest;
import webservice.response.StoreTransactionResponse;
import webservice.test.helper.ProxyBuilder;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class StoreTransactionTest {

	@Test
	public void testStoreTransaction() {

		ITransactionsService proxy = ProxyBuilder.buildProxy();

		StoreTransactionRequest request = new StoreTransactionRequest();
		request.setAmount(5000);
		request.setType("cars");
		StoreTransactionResponse response = proxy.storeTransaction(10, request);

		assertThat(response, notNullValue());
		assertThat(response.getStatus(), is("OK"));
		assertThat(response.getResult(), notNullValue());
		assertThat(response.getResult().getResultCode(), is("OK"));
		
		request.setAmount(10000);
		request.setType("shopping");
		request.setParent_id(10L);
		response = proxy.storeTransaction(11, request);
		
		assertThat(response, notNullValue());
		assertThat(response.getStatus(), is("OK"));
		assertThat(response.getResult(), notNullValue());
		assertThat(response.getResult().getResultCode(), is("OK"));
	}

	
}
