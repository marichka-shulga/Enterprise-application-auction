package auction.businesslogic.modelBL;

import org.apache.logging.log4j.Logger;

import auction.dao.BidDAO;
import auction.log.LogFactory;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.LotState;
import auction.service.response.BaseResponse;
import auction.service.response.StateResult;

public class BidLogic {
	private BidDAO bidDAO;

	private static final Logger LOGGRER = LogFactory.getLogger(BidLogic.class);
	
	public BidLogic() {
		bidDAO = new BidDAO();
	}

	public BaseResponse addBid(Bid bid){
		BaseResponse res = new BaseResponse();
		Lot lot = bid.getLot();
		String message = null;
		try {
			if(lot.getState() == LotState.ACTIVE){
				int rateIsMore = bid.getRate().compareTo(lot.getStartPrice());
				if( (0 == rateIsMore) || (1 == rateIsMore) ) {
					rateIsMore = bid.getRate().compareTo(bidDAO.getMaxRate(lot.getIdLot()));
					if( 1 == rateIsMore ){
						bidDAO.save(bid);
						res.setStateResult(StateResult.SUCCESS);
						res.setIdEntity(bid.getIdBid());
						message = "The addition bid successfully idLot={}, userLogin={}";
					}
				}
			}
			if( null == res.getStateResult() ){
				res.setStateResult(StateResult.NOT_SUCCESS);
				message = "The addition bid not successfully idLot={}, userLogin={}";
			}
			
			LOGGRER.info(message, lot.getIdLot(), bid.getUser().getUserLogin());
			
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addBid{}, reason={}, idLot={}, userLogin={}",
					e, e.getMessage(), lot.getIdLot(), bid.getUser().getUserLogin());
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());
		}
		
		return res;
	}
	
	
}
