package test.response;

public class TestApplicationResponse {

	private String statusCode;
	private String message;
	private Integer minimumStrides;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getMinimumStrides() {
		return minimumStrides;
	}

	public void setMinimumStrides(Integer minimumStrides) {
		this.minimumStrides = minimumStrides;
	}

	@Override
	public String toString() {
		return "[statusCode="
				+ statusCode + ", message=" + message
				+ ", minimumStrides=" + minimumStrides + "]";
	}
	
}
