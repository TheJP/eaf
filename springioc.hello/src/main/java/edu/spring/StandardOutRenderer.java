package edu.spring;

public class StandardOutRenderer implements MessageRenderer {

	private MessageProvider provider;

	public void render() {
		System.out.println(provider.getMessage());
	}

	public void setMessageProvider(MessageProvider provider) {
		this.provider = provider;
	}

}
