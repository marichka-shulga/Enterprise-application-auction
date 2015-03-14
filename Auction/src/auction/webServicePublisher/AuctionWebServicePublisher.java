package auction.webServicePublisher;

import javax.xml.ws.Endpoint;

import auction.service.FacadeService;

public class AuctionWebServicePublisher {

	public static void main(String[] args) {
		AuctionServiceProperties auctionServiceProperties = new AuctionServiceProperties();
		try{
			Endpoint.publish(auctionServiceProperties.getWsdlURL(), new FacadeService());
			System.out.println("wsdl published");
		} catch (Exception e){
			System.out.println("wsdl alredy published");
		}
		//System.exit(0);
	}

}
