package auction.ui.bidsform;

import auction.ui.ClientAuctionSinglton;
import auction.ui.addbid.AddBidDialog;
import auction.ui.registration.MD5;
import client.artefacts.Bid;
import client.artefacts.GetLotByIdResponse;
import client.artefacts.Lot;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.artefacts.UserAuthenticResponse;
import client.realization.ClientAuction;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

public class BidsForm extends Form {

	private static final long serialVersionUID = 1L;
	private Table bidsTable;
	private Button newBidButton;
	
	private HorizontalLayout footer;	
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();		
	
	
	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		setCaption("Bids");
		addStyleName("bordered"); 

		setLayout(layout);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.addComponent(getBidsTable());
		layout.addComponent(getHozizontalLayoutWithButton());
		
		layout.setExpandRatio(getBidsTable(), 1);
		layout.setExpandRatio(getHozizontalLayoutWithButton(), 0);
		layout.setComponentAlignment(getHozizontalLayoutWithButton(), Alignment.MIDDLE_RIGHT);
		setSizeFull();
		//addLot(new Lot("code", "name", "finishDate", "state"));
		buttonNewBidClick();
		
	}	
	
	private HorizontalLayout getHozizontalLayoutWithButton(){
		if( null == footer ){
			footer = new HorizontalLayout();
			footer.setMargin(true, false, false, false);
			footer.addComponent(getNewBidButton());
			footer.setSizeUndefined();			
		}
		return footer;
	}	
	private Table getBidsTable() {
		if (bidsTable == null) {
			bidsTable = new Table();
			bidsTable.setSizeFull();
			bidsTable.addContainerProperty("Bid", String.class, "");
			bidsTable.addContainerProperty("Date", String.class, "");
			bidsTable.addContainerProperty("Bidder", String.class, "");
		}
		return bidsTable;
	}


	public Button getNewBidButton() {
		if (newBidButton == null) {
			newBidButton = new Button("New bid");
			newBidButton.setWidth(80, UNITS_PIXELS);
		}
		return newBidButton;
	}
	
	
	@SuppressWarnings("serial")	
	public void buttonNewBidClick(){
		newBidButton.addListener(new ClickListener() {				
		@Override
		public void buttonClick(ClickEvent event) {
			User user = null;
			UserAuthenticResponse responce = client.userAuthentication("qwerty", MD5.encryptPassword("qwerty"));
			if( responce.getStateResult().equals(StateResult.SUCCESS) ){
				user = responce.getUser();
			} else{
				getApplication().getMainWindow().showNotification(responce.getErrorMessage(),
						Notification.TYPE_ERROR_MESSAGE);
			}
			Lot lot = null;
			GetLotByIdResponse resp =  client.getLot(60);
			if( resp.getStateResult().equals(StateResult.SUCCESS) ){
				lot = resp.getLot();
			} else{
				getApplication().getMainWindow().showNotification(resp.getErrorMessage(),
						Notification.TYPE_ERROR_MESSAGE);
			}		
			
			
			Bid bid = new Bid();
			bid.setIsWinningBid(false);
			bid.setLot(lot);
			bid.setUser(user);
			
			
			getApplication().getMainWindow().addWindow(new AddBidDialog(bid));
			
			
		}
		});		 
	 }	

}
