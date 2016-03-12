package webservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import webservice.ITransactionsService;
import webservice.response.GetTransactionByTypeResponse;
import webservice.test.helper.ProxyBuilder;

public class GetTransactionsByTypeTest {

	@Test
	public void testGetTransactionsByType(){
		ITransactionsService proxy = ProxyBuilder.buildProxy();
		
		GetTransactionByTypeResponse response = proxy.getTransactionsByType("cars");
		
		assertThat(response, notNullValue());
		assertThat(response.getTransactionsIds().size(), is(1));
		assertThat(response.getTransactionsIds().get(0), is(10L));
		assertThat(response.getResult(), notNullValue());
		assertThat(response.getResult().getResultCode(), is("OK"));
	}
}
