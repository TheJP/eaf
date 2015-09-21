package edu.spring.domain.impl;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration
@PropertySource(value = { "classpath:app.properties" })
@ComponentScan(basePackages = { "edu.spring.domain" })
public class Configuration {

}
