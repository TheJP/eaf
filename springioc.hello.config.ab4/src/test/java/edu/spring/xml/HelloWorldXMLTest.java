package edu.spring.xml;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.spring.domain.MessageProvider;
import edu.spring.domain.MessageRenderer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/helloConfigWithApplicationContextWithXmlFormat.xml"})
public class HelloWorldXMLTest {
	@Autowired
	private MessageProvider messageProvider;
	
	@Autowired
	private MessageRenderer messageRenderer;
	
	@Test
	public void testGetMessage() {
		assertEquals("Herzlich willkommen!", messageProvider.getMessage());
	}

	@Test
	public void testGetMessageProvider() {
		assertNotNull(messageRenderer.getMessageProvider());
	}
}
