package auction.service.response;
 import java.util.List;

import auction.model.Bid;
public class GetBidsByIdLotResponse extends BaseResponse {
	
	private List<Bid> bids;
	
	public void setBids(List<Bid> bids){
		this.bids = bids;
	}
	
	public List<Bid> getBids(){
		return this.bids;
	}	
}
