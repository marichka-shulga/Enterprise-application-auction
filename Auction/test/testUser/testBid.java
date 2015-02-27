package testUser;



import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import auction.businesslogic.modelBL.BidLogic;
import auction.dao.BidDAO;
import auction.dao.LotDAO;
import auction.dao.UserDAO;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.User;

public class testBid {

	@Test
	public void getMaxRate() {
		
		BidDAO bidDAO = new BidDAO();
		BigDecimal rate = new BigDecimal(3);
		BigDecimal maxrate = bidDAO.getMaxRate(1);
		
		Assert.assertTrue(rate.compareTo(maxrate) == 0);
	}	
	
	
	@Test
	public void addBid1() {
		LotDAO lotDAO = new LotDAO();
		Lot lot = lotDAO.getObjectById(1);
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getObjectById(2);		
		
		Bid bid = new Bid();
		bid.setIsWinningBid(false);
		bid.setRate(new BigDecimal(1.0));
		bid.setLot(lot);
		bid.setUser(user);

		BidLogic lotLogic = new BidLogic();

		Assert.assertFalse(lotLogic.addBid(user,lot,bid));

	}
	@Test
	public void addBid2() {
		LotDAO lotDAO = new LotDAO();
		Lot lot = lotDAO.getObjectById(1);
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getObjectById(1);		
		
		Bid bid = new Bid();
		bid.setIsWinningBid(false);
		bid.setRate(new BigDecimal(3.0));
		bid.setLot(lot);
		bid.setUser(user);

		BidLogic lotLogic = new BidLogic();

		Assert.assertTrue(lotLogic.addBid(user,lot,bid));

	}
	
	@Test
	public void addBid3() {
		LotDAO lotDAO = new LotDAO();
		Lot lot = lotDAO.getObjectById(1);
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getObjectById(1);		
		
		Bid bid = new Bid();
		bid.setIsWinningBid(false);
		bid.setRate(new BigDecimal(2.0));
		bid.setLot(lot);
		bid.setUser(user);

		BidLogic lotLogic = new BidLogic();

		Assert.assertFalse(lotLogic.addBid(user,lot,bid));

	}
	
	@Test
	public void getWinBid() {
		
		BidDAO bidDAO = new BidDAO();
		Bid bid = bidDAO.getWinningBid(2);
		
		Assert.assertTrue(bid == null);				


	}
	
	@Test
	public void getAllBidsForLot() {
		
		BidDAO bidDAO = new BidDAO();
		Set<Bid> bids = bidDAO.getAllBidsForLot(3);
		Iterator<Bid> it = bids.iterator();
		
		while(it.hasNext()){
			Bid bid = it.next();
			System.out.println(bid.getIdBid());
		}


	}
	
}
