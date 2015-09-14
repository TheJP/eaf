package springioc.hello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.spring.DecoupledHelloWorldWithSpring;
import edu.spring.MessageProvider;

public class MessageProviderTest {

	private MessageProvider provider;

	@Before
	public void setUp() throws Exception {
		provider = (MessageProvider) DecoupledHelloWorldWithSpring.getBeanFactory().getBean("provider");
	}

	@Test
	public void testGetMessage() {
		assertTrue("None of the expected messages was returned.",
			provider.getMessage().equals("Hello World!") ||
			provider.getMessage().equals("Herzlich Willkommen!"));
	}

}
