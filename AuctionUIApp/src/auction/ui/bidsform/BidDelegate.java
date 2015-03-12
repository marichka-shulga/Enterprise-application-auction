package auction.ui.bidsform;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import client.artefacts.Bid;

public class BidDelegate {
	
	private Bid bid;	

	public BidDelegate(Bid bid){
		this.bid = bid;
	}
	
	public void setBid(Bid bid){
		this.bid = bid;
	}
	
	public Bid getBid(){
		return this.bid;
	}	
	
	public String getDateAdding() {
		String result = "";
		XMLGregorianCalendar calendar = bid.getDateAdding();
		Date date = null;
        if( null != calendar ) {
            date = calendar.toGregorianCalendar().getTime();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a");
           	result = format.format(date);
        }
        return result;
	}

	public String getRate() {
		String res = bid.getRate()+" $";
		return res;
	}

	public String getUser() {
		return bid.getUser().getUserLogin();
	}
	
	public Boolean isIsWinningBid() {
		return bid.isIsWinningBid();
	}

	public void setIsWinningBid(Boolean value) {
		bid.setIsWinningBid(value);
	}
}
