package webservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import webservice.ITransactionsService;
import webservice.response.GetTransactionResponse;
import webservice.test.helper.ProxyBuilder;

public class GetTransactionTest {

	@Test
	public void testGetTransaction() {
		ITransactionsService proxy = ProxyBuilder.buildProxy();
		
		GetTransactionResponse response = proxy.getTransaction(10);
		
		assertThat(response, notNullValue());
		assertThat(response.getTransaction(), notNullValue());
		assertThat(response.getTransaction().getType(), is("cars"));
		assertThat(response.getTransaction().getAmount(), is(new Double(5000)));
		assertThat(response.getResult(), notNullValue());
		assertThat(response.getResult().getResultCode(), is("OK"));
		
		response = proxy.getTransaction(11);
		
		assertThat(response, notNullValue());
		assertThat(response.getTransaction(), notNullValue());
		assertThat(response.getTransaction().getType(), is("shopping"));
		assertThat(response.getTransaction().getAmount(), is(new Double(10000)));
		assertThat(response.getTransaction().getParent_id(), is(10L));
		assertThat(response.getResult(), notNullValue());
		assertThat(response.getResult().getResultCode(), is("OK"));
		
	}
	
}
