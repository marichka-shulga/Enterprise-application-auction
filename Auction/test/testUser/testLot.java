package testUser;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import auction.businesslogic.modelBL.LotLogic;
import auction.businesslogic.modelBL.UserLogic;
import auction.dao.LotDAO;
import auction.dao.UserDAO;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.LotState;
import auction.model.User;

public class testLot {

//	@Test
//	public void addLot1() {
//		UserDAO userDAO = new UserDAO();
//		User user = userDAO.getObjectById(1);
//		
//		Lot lot = new Lot();
//		lot.setCode(120);
//		lot.setDescriptions("Lot description");
//		Timestamp finishDate = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
//		finishDate.setMinutes(15);
//		lot.setFinashDate(finishDate);
//		lot.setName("lot1");
//		lot.setStartPrice(new BigDecimal(2.25));
//		lot.setState(LotState.ACTIVE);
//		lot.setBids(new HashSet<Bid>());
//		lot.setUser(user);
//		LotLogic lotLogic = new LotLogic();
//
//		Assert.assertTrue(lotLogic.addLot(lot, user));
//
//	}
//	
//	@Test
//	public void addLot3() {
//		UserDAO userDAO = new UserDAO();
//		User user = userDAO.getObjectById(2);
//		
//		Lot lot = new Lot();
//		lot.setCode(911);
//		lot.setDescriptions("Lot description");
//		Timestamp finishDate = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
//		finishDate.setMinutes(10);
//		lot.setFinashDate(finishDate);
//		lot.setName("lot6");
//		lot.setStartPrice(new BigDecimal(3));
//		lot.setState(LotState.ACTIVE);
//		lot.setBids(new HashSet<Bid>());
//		lot.setUser(user);
//		LotLogic lotLogic = new LotLogic();
//
//		Assert.assertTrue(lotLogic.addLot(lot, user));
//
//	}
	
//	@Test
//	public void cancelLots() {
//		LotDAO lotDAO = new LotDAO();
//		Lot lot = lotDAO.getObjectById(2);
//		
//		LotLogic lotLogic = new LotLogic();
//
//		Assert.assertTrue(lotLogic.cancelOfTrades(lot));
//
//	}
	
	
//	@Test
//	public void finishTrades() {
//		LotDAO lotDAO = new LotDAO();
//		Lot lot = lotDAO.getObjectById(1);
//		
//		LotLogic lotLogic = new LotLogic();
//
//		lotLogic.finishTrades(lot);
//
//	}
	
	
//	@Test
//	public void firstLoadAllActiveLots() {
//		
//		LotLogic lotLogic = new LotLogic();
//		Set<Lot> activelots = lotLogic.firstLoadAllActiveLots();
//		Iterator<Lot> it = activelots.iterator();
//		
//		while(it.hasNext()){
//			Lot lot = it.next();
//			System.out.println(lot.getIdLot());
//		}
//
//
//	}

}
