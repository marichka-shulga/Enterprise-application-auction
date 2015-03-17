package auction.webServicePublisher;

import javax.xml.ws.Endpoint;

import org.apache.logging.log4j.Logger;

import auction.log.LogFactory;
import auction.service.FacadeService;

public class AuctionWebServicePublisher {
	private static final Logger LOGGRER = LogFactory.getLogger(AuctionServiceProperties.class);	
	public static void main(String[] args) {
		AuctionServiceProperties auctionServiceProperties = new AuctionServiceProperties();
		String message = "";
		try{
			Endpoint.publish(auctionServiceProperties.getWsdlURL(), new FacadeService());
			message = "WSDL is published";
			System.out.println(message);
			LOGGRER.info(message);
		} catch (Exception e){
			message = "WSDL is not published because it already published";
			System.out.println(message);
			LOGGRER.info(message);
		}
	}

}
