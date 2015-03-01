package auction.businesslogic.modelBL;

import org.apache.logging.log4j.Logger;

import auction.dao.BidDAO;
import auction.dao.UserDAO;
import auction.log.LogFactory;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.LotState;
import auction.model.User;
import auction.service.response.BaseResponse;
import auction.service.response.StateResult;

public class BidLogic {
	private UserDAO userDAO;
	private BidDAO bidDAO;

	private static final Logger LOGGRER = LogFactory.getLogger(BidLogic.class);
	
	public BidLogic() {
		userDAO = new UserDAO();
		bidDAO = new BidDAO();
	}

	public BaseResponse addBid(Lot lot, Bid bid){
		BaseResponse res = new BaseResponse();
		User user = bid.getUser();
		try {
			if( lot.getState() == LotState.ACTIVE ){
				int rateIsMore = bid.getRate().compareTo(lot.getStartPrice());
				if( (1 == rateIsMore) || (0 == rateIsMore) ){
					rateIsMore = bid.getRate().compareTo(bidDAO.getMaxRate(lot.getIdLot()));
					if( 1 == rateIsMore ){
						user.addBid(bid);
						lot.addBid(bid);
						userDAO.update(user);

						res.setStateResult(StateResult.SUCCESS);
					}
				}
			}
		
			} catch( Exception e ){
				LOGGRER.error("Is not satisfied addBid{}, reason={}, idBid={}, idLot={}, userLogin={}", 
						e, e.getMessage(), bid.getIdBid(), lot.getIdLot(), user.getLogin());	
				res.setStateResult(StateResult.ERROR);
				res.setErrorMessage(e.getMessage());	
			}
		
		LOGGRER.info("The addition bid successfully idBid={}, idLot={}, userLogin={}",
					bid.getIdBid(), lot.getIdLot(), user.getLogin());
		
		return res;
	}
}
