package auction.service.response;

import auction.model.Bid;

public class GetWinningBidByIdResponseResponse extends BaseResponse {
	
	Bid winnindBid;

	public Bid getWinnindBid() {
		return winnindBid;
	}
	public void setWinnindBid(Bid winnindBid) {
		this.winnindBid = winnindBid;
	}
}
