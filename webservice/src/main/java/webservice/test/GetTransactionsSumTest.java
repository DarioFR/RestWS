package webservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import webservice.ITransactionsService;
import webservice.response.GetTransactionsSumResponse;
import webservice.test.helper.ProxyBuilder;

public class GetTransactionsSumTest {

	@Test
	public void testGetTransactionsSumTest() {
		ITransactionsService proxy = ProxyBuilder.buildProxy();

		GetTransactionsSumResponse response1 = proxy.getTransactionsSum(10);
		assertThat(response1, notNullValue());
		assertThat(response1.getSum(), is(new Double(15000)));
		assertThat(response1.getResult(), notNullValue());
		assertThat(response1.getResult().getResultCode(), is("OK"));

		GetTransactionsSumResponse response2 = proxy.getTransactionsSum(11);

		assertThat(response2, notNullValue());
		assertThat(response2.getSum(), is(new Double(10000)));
		assertThat(response2.getResult(), notNullValue());
		assertThat(response2.getResult().getResultCode(), is("OK"));
	}

}
