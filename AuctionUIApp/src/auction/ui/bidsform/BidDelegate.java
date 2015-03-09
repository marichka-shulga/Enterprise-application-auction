package auction.ui.bidsform;

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
	
	public Date getDateAdding() {
		XMLGregorianCalendar calendar = bid.getDateAdding();
		Date date = null;
        if( null != calendar ) {
            date = calendar.toGregorianCalendar().getTime();
//            DateFormat format;
        }
        return date;
	}

	public String getRate() {
		String res = bid.getRate()+" $";
		return res;
	}

	public String getUser() {
		return bid.getUser().getUserLogin();
	}

}
