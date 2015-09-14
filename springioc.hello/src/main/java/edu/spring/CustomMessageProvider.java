package edu.spring;

public class CustomMessageProvider implements MessageProvider {

	private String message;

	public CustomMessageProvider(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
