package modelBL;

import org.apache.logging.log4j.Logger;

import dao.BidDAO;
import dao.UserDAO;
import log.LogFactory;
import model.Bid;
import model.Lot;
import model.LotState;
import model.User;

public class BidLogic {
	private UserDAO userDAO;
	private BidDAO bidDAO;

	private static final Logger LOGGRER = LogFactory.getLogger(BidLogic.class);
	
	public BidLogic() {
		userDAO = new UserDAO();
		bidDAO = new BidDAO();
	}

	public boolean addBid(User user, Lot lot, Bid bid){
		boolean res = false;
		try {
			if( lot.getState() == LotState.ACTIVE ){
				int rateIsMore = bid.getRate().compareTo(lot.getStartPrice());
				if( (1 == rateIsMore) || (0 == rateIsMore) ){
					rateIsMore = bid.getRate().compareTo(bidDAO.getMaxRate(lot.getIdLot()));
					if( 1 == rateIsMore ){
						user.addBid(bid);
						lot.addBid(bid);
						userDAO.update(user);

						res = true;
					}
				}
			}
		
			} catch( Exception e ){
				LOGGRER.error("Is not satisfied addBid: idBid={}, idLot={}, userLoggin={}, reason={}", 
						bid.getIdBid(), lot.getIdLot(), user.getLoggin(), e.getMessage());						
			}
		String message = "";
		if( res )
			message = "The addition bid successfully: idBid={}, idLot={}, userLoggin={}";
	
		else
			message = "The addition bid not successfully: idBid={}, idLot={}, userLoggin={}";
		
		LOGGRER.info(message, bid.getIdBid(), lot.getIdLot(), user.getLoggin());
		
		return res;
	}
}
