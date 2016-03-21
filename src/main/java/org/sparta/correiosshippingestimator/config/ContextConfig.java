/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.config;

import org.sparta.correiosshippingestimator.service.SpartaCorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author Daniel Conde Diehl
 *
 */
public class ContextConfig {
	@Autowired
	private String endpoint;
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.sparta.virtualstore.schema.correios");
		return marshaller;
	}
	
	
	@Bean
	public SpartaCorreiosService shippingCorreiosService(Jaxb2Marshaller marshaller) {
		SpartaCorreiosService wsTemplate = new SpartaCorreiosService();
		wsTemplate.setMarshaller(marshaller);
		wsTemplate.setUnmarshaller(marshaller);
		wsTemplate.setDefaultUri(endpoint);
		return wsTemplate;
	}	
}
