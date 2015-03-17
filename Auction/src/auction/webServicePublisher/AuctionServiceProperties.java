package auction.webServicePublisher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import auction.log.LogFactory;

public class AuctionServiceProperties {

	private static final Logger LOGGRER = LogFactory.getLogger(AuctionServiceProperties.class);	
	
	private String wsdlURL;		
	
	public AuctionServiceProperties(){
    	Properties properties = new Properties();
    	InputStream fileInput = null;
		try {
			fileInput = getClass().getResourceAsStream("/config.properties");
			properties.load(fileInput);
			wsdlURL = properties.getProperty("wsdl_url");
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied AuctionServiceProperties={}, reason={}", e, e.getMessage());
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					LOGGRER.error("Is not satisfied AuctionServiceProperties={}, reason={}", e, e.getMessage());
				}
			}
		}
	}

	public String getWsdlURL() {
		return wsdlURL;
	}


}
