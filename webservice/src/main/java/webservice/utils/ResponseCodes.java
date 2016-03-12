package webservice.utils;

public enum ResponseCodes {

	OK("OK"),
	KO("KO");
	
	
	private String description;
	
	ResponseCodes(String description){
		this.setDescription(description);
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
