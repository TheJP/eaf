package edu.spring;

public class StandardErrorXMLRenderer implements MessageRenderer {

	private MessageProvider provider;

	public void render() {
		System.err.println(String.format("<xml>%s</xml>", provider.getMessage()));
	}

	public void setMessageProvider(MessageProvider provider) {
		this.provider = provider;
	}

}
