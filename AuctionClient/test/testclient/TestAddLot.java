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
import client.artefacts.Lot;
import client.artefacts.LotState;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.artefacts.UserAuthenticResponse;
import client.realization.ClientAuction;

public class TestAddLot {
	ClientAuction client = new ClientAuction();
	User user1 = null;
	User user2 = null;
//	@Test
//	@Before
//	public void authenticationUser1() {
//		UserAuthenticResponse resp = client.userAuthentication("marichka", "marichka");
//		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
//		Assert.assertTrue(resp.getUser().getUserLogin().equals("marichka"));
//		Assert.assertTrue(resp.getUser().getPassword().equals("marichka"));
//		user1 = resp.getUser();
//	}
	
	@Test
	@Before
	public void authenticationUser2() {
		UserAuthenticResponse resp = client.userAuthentication("qwer", "qwer");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
		Assert.assertTrue(resp.getUser().getUserLogin().equals("qwer"));
		Assert.assertTrue(resp.getUser().getPassword().equals("qwer"));
		user2 = resp.getUser();
	}	
	
	@Test
	public void addLot1() {
		
		Lot lot = new Lot();
		lot.setCode(8978);
		lot.setDescriptions("Lot description");
		Date finishDate = new Date();
		finishDate.setTime(finishDate.getTime() + 38000);		

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(finishDate);
		XMLGregorianCalendar date = null;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		lot.setFinishDate(date);
		lot.setName("lot4");
		lot.setStartPrice(new BigDecimal(9));
		lot.setState(LotState.ACTIVE);
		lot.setUser(user2);
		BaseResponse resp = client.addLot(lot);
//		System.out.println(resp.getStateResult());
//		System.out.println(resp.getErrorMessage());
		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);

	}
	
	@Test
	public void addLot2() {
		
		Lot lot = new Lot();
		lot.setCode(776);
		lot.setDescriptions("Lot description");
		Date finishDate = new Date();
		finishDate.setTime(finishDate.getTime() + 38000);		

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(finishDate);
		XMLGregorianCalendar date = null;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		lot.setFinishDate(date);
		lot.setName("lot3");
		lot.setStartPrice(new BigDecimal(9));
		lot.setState(LotState.ACTIVE);
		lot.setUser(user2);
		BaseResponse resp = client.addLot(lot);
//		System.out.println(resp.getStateResult());
//		System.out.println(resp.getErrorMessage());
		Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);

	}
//	@Test
//	public void addLot3() {
//		
//		Lot lot = new Lot();
//		lot.setCode(554);
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
//		lot.setFinashDate(date);
//		lot.setName("lot5");
//		lot.setStartPrice(new BigDecimal(9));
//		lot.setState(LotState.ACTIVE);
//		lot.setUser(user1);
//		BaseResponse resp = client.addLot(lot);
//		System.out.println(resp.getStateResult());
//		System.out.println(resp.getErrorMessage());
//		//Assert.assertEquals(resp.getStateResult(),StateResult.SUCCESS);
//
//	}

}
