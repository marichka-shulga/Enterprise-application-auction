package auction.ui.addbid;

import java.util.Arrays;

import auction.ui.ClientAuctionSinglton;
import client.artefacts.BaseResponse;
import client.artefacts.Bid;
import client.artefacts.StateResult;
import client.realization.ClientAuction;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;



public class AddBidDialog extends Window {
	

	private static final long serialVersionUID = 1L;
	private Form from;
	private Label buckLabel;
	
	private Button addBidButton;
	
	private static final String BID = "<H2>$</H2>";
	
	private HorizontalLayout mainHorizontalLayout;
	
	private static final int BUTTON_WIDTH = 80;
	
	private static BidFieldFactory bidFieldFactory = BidFieldFactorySinglton.getBidFieldFactory();
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	
	
	Bid bid;
	 
	public AddBidDialog(Bid bid){
		this.bid = bid;
	}
	
	public void attach() {
		
		this.setCaption("New bid");
		this.center();
		this.setResizable(false);

		mainHorizontalLayout = new HorizontalLayout();
		mainHorizontalLayout.setMargin(false, true, false, true);
		mainHorizontalLayout.setSpacing(true);
		setContent(mainHorizontalLayout);		
		mainHorizontalLayout.setSizeFull();
		
	    BeanItem<Bid> bidItem = new BeanItem<Bid>(bid); 
	    getFrom().setFormFieldFactory(bidFieldFactory);
	    getFrom().setItemDataSource(bidItem);
		getFrom().setVisibleItemProperties(Arrays.asList(new String[] {"rate"}));	
		
		mainHorizontalLayout.addComponent(getFrom());
		mainHorizontalLayout.addComponent(getBuckLabel());
		mainHorizontalLayout.addComponent(getAddBidButton());
		mainHorizontalLayout.setComponentAlignment(getAddBidButton(),Alignment.MIDDLE_RIGHT);
		mainHorizontalLayout.setSizeUndefined();

		buttonAddBidClick();
	}


	@SuppressWarnings("serial")
	private void buttonAddBidClick() {
		addBidButton.addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
				
				BaseResponse response = client.addBid(bid);
				if( response.getStateResult().equals(StateResult.SUCCESS) ){
					bid.setIdBid(response.getIdEntity());
				} else if( response.getStateResult().equals(StateResult.NOT_SUCCESS) ){
					getApplication().getMainWindow().showNotification("Less than the previous rate",
											Notification.TYPE_WARNING_MESSAGE);
				} else{
					getApplication().getMainWindow().showNotification(response.getErrorMessage(),
							Notification.TYPE_ERROR_MESSAGE);
				}				
				
				//getParent().removeWindow(getWindow());				
		
			} catch (InvalidValueException e) {
				
			}
		}
	});
}

	public Button getAddBidButton() {
		if (addBidButton == null) {
			addBidButton = new Button("OK");
			addBidButton.setWidth(BUTTON_WIDTH, UNITS_PIXELS);
		}
		return addBidButton;
	}
	
	public Label getBuckLabel() {
		if (buckLabel == null) {
			buckLabel = new Label(BID);
			buckLabel.setContentMode(Label.CONTENT_XHTML);
		}
		return buckLabel;
	}


	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	

}
