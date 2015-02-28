package auction.service;

import javax.xml.ws.Endpoint;

public class AuctionWebServicePublisher {

	public static void main(String[] args) {
		Endpoint endPoint = Endpoint.publish("http://localhost:8080/Auction/AuctionService", new FacadeService());
		System.out.println(endPoint.isPublished());

	}

}
