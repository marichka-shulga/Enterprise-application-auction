package auction.ui.lotsform;

import java.util.List;

import auction.ui.domainDelegate.BidDelegate;

public interface ClickedLotListenerForBidsForm {
	public void bidsForClickedLot(List<BidDelegate> bids);

}
