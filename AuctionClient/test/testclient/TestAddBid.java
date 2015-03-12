package testclient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import client.artefacts.BaseResponse;
import client.artefacts.Bid;
import client.artefacts.Lot;
import client.artefacts.LotState;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.artefacts.UserAuthenticResponse;
import client.realization.ClientAuction;

public class TestAddBid {
	ClientAuction client = new ClientAuction();
	User user2 = null;
	Lot lot = null;
//	@Test
//	@Before
//	public void authenticationUser2() {
//		UserAuthenticResponse resp = client.userAuthentication("qwer", "qwer");
//		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
//		Assert.assertTrue(resp.getUser().getUserLogin().equals("qwer"));
//		Assert.assertTrue(resp.getUser().getPassword().equals("qwer"));
//		user2 = resp.getUser();
//	}	
	
//	@Test
//	@Before
//	public void addLot1() {
//		
//		UserAuthenticResponse resp = client.userAuthentication("qwer", "qwer");
//		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
//		Assert.assertTrue(resp.getUser().getUserLogin().equals("qwer"));
//		Assert.assertTrue(resp.getUser().getPassword().equals("qwer"));
//		user2 = resp.getUser();		
		
//		//GetLotByIdResponse respLot = client.getLot(63);
//		lot = respLot.getLot();
//		lot = new Lot();
//		lot.setCode(8978);
//		lot.setDescriptions("Lot description");
//		Date finishDate = new Date();
//		finishDate.setTime(finishDate.getTime() + 38000);		
//
//		GregorianCalendar c = new GregorianCalendar();
//		c.setTime(finishDate);
//		XMLGregorianCalendar date = null;
//		try {
//			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
//		} catch (DatatypeConfigurationException e) {
//			e.printStackTrace();
//		}
//		lot.setFinishDate(date);
//		lot.setName("lot33");
//		lot.setStartPrice(new BigDecimal(3));
//		lot.setState(LotState.ACTIVE);
//		lot.setUser(user2);
//		BaseResponse resp1 = client.addLot(lot);
//		Assert.assertEquals(resp1.getStateResult(),StateResult.SUCCESS);
//	}
	
//	@Test
//	public void addBid1() {
//		
//		Bid bid = new Bid();
//		bid.setIsWinningBid(false);
//		bid.setRate(new BigDecimal(4.0));
//		bid.setUser(user2);
//		bid.setLot(lot);
//		
//		BaseResponse resp = client.addBid(bid);
//		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);
//	}	
	
//	@Test
//	public void addBid2() {
//		
//		Bid bid = new Bid();
//		bid.setIsWinningBid(false);
//		bid.setRate(new BigDecimal(4.0));
//		bid.setUser(user2);
//		bid.setLot(lot);
//		
//		BaseResponse resp = client.addBid(bid);
//		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);
//	}		
//	
//	public void addBid3() {
//		
//		Bid bid = new Bid();
//		bid.setIsWinningBid(false);
//		bid.setRate(new BigDecimal(5.0));
//		bid.setUser(user2);
//		bid.setLot(lot);
//		
//		BaseResponse resp = client.addBid(bid);
//		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);
//	}		
//	public void addBid4() {
//		
//		Bid bid = new Bid();
//		bid.setIsWinningBid(false);
//		bid.setRate(new BigDecimal(4.0));
//		bid.setUser(user2);
//		bid.setLot(lot);
//		
//		BaseResponse resp = client.addBid(bid);
//		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);
//	}	
//	public void addBid5() {
//		
//		Bid bid = new Bid();
//		bid.setIsWinningBid(false);
//		bid.setRate(new BigDecimal(2.0));
//		bid.setUser(user2);
//		bid.setLot(lot);
//		
//		BaseResponse resp = client.addBid(bid);
//		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);
//	}	
}
