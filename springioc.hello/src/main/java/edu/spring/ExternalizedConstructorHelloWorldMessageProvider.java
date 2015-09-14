package edu.spring;

public class ExternalizedConstructorHelloWorldMessageProvider implements MessageProvider {

	private String message;
	public ExternalizedConstructorHelloWorldMessageProvider(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
