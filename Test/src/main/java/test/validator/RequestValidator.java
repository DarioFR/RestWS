package test.validator;

public class RequestValidator {

	public static ValidatorOutput validateRequest(int[] nr_steps,
			int stepsPerStride) {
		ValidatorOutput vOutput = new ValidatorOutput();
		if (nr_steps.length < 1 || nr_steps.length > 30) {
			vOutput.setValid(false);
			vOutput.setMessage(
					"error.invalid.request.incorrect.flights.nr");
			return vOutput;
		}

		for (int step : nr_steps) {
			if (step < 1 || step > 20) {
				vOutput.setValid(false);
				vOutput.setMessage(
						"error.invalid.request.incorrect.steps.nr");
				return vOutput;
			}
		}

		if (stepsPerStride < 1 || stepsPerStride > 4) {
			vOutput.setValid(false);
			vOutput.setMessage(
					"error.invalid.request.incorrect.steps.per.stride");
			return vOutput;
		}
		vOutput.setValid(true);
		vOutput.setMessage("validation.ok");
		return vOutput;
	}
}
