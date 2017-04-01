package test;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.constants.Constants;
import test.response.TestApplicationResponse;
import test.validator.RequestValidator;
import test.validator.ValidatorOutput;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class TestApplication {

	private final Logger logger = (Logger) LoggerFactory
			.getLogger(this.getClass());
	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping(value = "/minimumNStrides", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TestApplicationResponse calculateMinimumStrides(
			@RequestParam(value = "nr_steps", required = true) int[] nr_steps,
			@RequestParam(value = "stepsPerStride", required = true) int stepsPerStride) {

		TestApplicationResponse response = new TestApplicationResponse();

		// validation
		logger.info("Starting validation...");
		ValidatorOutput validatorOutput = RequestValidator
				.validateRequest(nr_steps, stepsPerStride);

		if (!validatorOutput.isValid()) {
			response.setMessage(messageSource.getMessage(
					validatorOutput.getMessage(), null, Locale.ENGLISH));
			response.setStatusCode(Constants.STATUS_CODE_ERR);
			logger.error("Validation error: " + response.getMessage());

			return response;
		}

		int minimumStrides = 0;
		for (int steps : nr_steps) {
			if (steps % stepsPerStride == 0) {
				minimumStrides += steps / stepsPerStride;
			} else {
				minimumStrides += steps / stepsPerStride + 1;
			}
		}

		/**
		 * Now let's consider the 2 additional strides necessary to turn on the
		 * landing
		 */

		minimumStrides += Constants.STRIDES_TO_TURN_ON_THE_LANDING
				* (nr_steps.length - 1);

		response.setStatusCode(Constants.STATUS_CODE_OK);
		response.setMinimumStrides(minimumStrides);
		response.setMessage(Constants.STATUS_CODE_OK);

		logger.info("Service response: " + response);
		return response;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
