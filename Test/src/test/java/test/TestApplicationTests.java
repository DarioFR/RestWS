package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import test.constants.Constants;
import test.messagesConfig.Config;
import test.response.TestApplicationResponse;
import test.validator.RequestValidator;
import test.validator.ValidatorOutput;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Config.class)
public class TestApplicationTests {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Test
	public void testApplication() {

		TestApplication test = new TestApplication();
		test.setMessageSource(messageSource);

		TestApplicationResponse response = test
				.calculateMinimumStrides(new int[] { 17, 17 }, 3);

		assertThat(response == null, is(false));
		assertThat(response.getMinimumStrides(), is(14));
		assertThat(response.getStatusCode(), is(Constants.STATUS_CODE_OK));
		assertThat(response.getMessage(), is(Constants.STATUS_CODE_OK));

		response = test.calculateMinimumStrides(new int[] { 17 }, 3);
		assertThat(response == null, is(false));
		assertThat(response.getMinimumStrides(), is(6));
		assertThat(response.getStatusCode(), is(Constants.STATUS_CODE_OK));
		assertThat(response.getMessage(), is(Constants.STATUS_CODE_OK));

		response = test.calculateMinimumStrides(
				new int[] { 4, 9, 8, 11, 7, 20, 14 }, 2);

		assertThat(response == null, is(false));
		assertThat(response.getMinimumStrides(), is(50));
		assertThat(response.getStatusCode(), is(Constants.STATUS_CODE_OK));
		assertThat(response.getMessage(), is(Constants.STATUS_CODE_OK));

		response = test.calculateMinimumStrides(
				new int[] { 4, 9, 8, 11, 7, 22, 14 }, 2);

		assertThat(response == null, is(false));
		assertThat(response.getMinimumStrides() == null, is(true));
		assertThat(response.getStatusCode(),
				is(Constants.STATUS_CODE_ERR));

		response = test.calculateMinimumStrides(
				new int[] { 4, 9, 8, 11, 7, 20, 14 }, 5);

		assertThat(response == null, is(false));
		assertThat(response.getMinimumStrides() == null, is(true));
		assertThat(response.getStatusCode(),
				is(Constants.STATUS_CODE_ERR));

		response = test.calculateMinimumStrides(new int[] { 18, 14, 3 },
				4);
		assertThat(response == null, is(false));
		assertThat(response.getMinimumStrides(), is(14));
		assertThat(response.getStatusCode(), is(Constants.STATUS_CODE_OK));
		assertThat(response.getMessage(), is(Constants.STATUS_CODE_OK));
	}

	@Test
	public void validatorTest() {

		ValidatorOutput validatorOutput = RequestValidator
				.validateRequest(new int[] { 17, 17 }, 3);

		assertThat(validatorOutput == null, is(false));
		assertThat(validatorOutput.getMessage(), is("validation.ok"));
		assertThat(validatorOutput.isValid(), is(true));

		validatorOutput = RequestValidator
				.validateRequest(new int[] { 12 }, 3);

		assertThat(validatorOutput == null, is(false));
		assertThat(validatorOutput.getMessage(), is("validation.ok"));
		assertThat(validatorOutput.isValid(), is(true));

		validatorOutput = RequestValidator
				.validateRequest(new int[] { 44 }, 2);

		assertThat(validatorOutput == null, is(false));
		assertThat(validatorOutput.getMessage(),
				is("error.invalid.request.incorrect.steps.nr"));
		assertThat(validatorOutput.isValid(), is(false));

		validatorOutput = RequestValidator
				.validateRequest(new int[] { 14 }, 28);

		assertThat(validatorOutput == null, is(false));
		assertThat(validatorOutput.getMessage(),
				is("error.invalid.request.incorrect.steps.per.stride"));
		assertThat(validatorOutput.isValid(), is(false));
		
		validatorOutput = RequestValidator
				.validateRequest(new int[] { 14,3,6,12,11,6,13,16,3,4,15,17,15,16,13,15,14,12,8,9,20,13,14,15,13,14,15,7,11,12,11},3);
	
		assertThat(validatorOutput == null, is(false));
		assertThat(validatorOutput.getMessage(),
				is("error.invalid.request.incorrect.flights.nr"));
		assertThat(validatorOutput.isValid(), is(false));
	}

}
