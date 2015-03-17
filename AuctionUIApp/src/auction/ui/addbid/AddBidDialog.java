package auction.ui.addbid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


public class AddBidDialog extends Window {

	private static final long serialVersionUID = 1L;
	private Form from;
	private Label buckLabel;
	
	private Button addBidButton;
	
	private static final String BID = "<H2>$</H2>";
	
	private HorizontalLayout mainHorizontalLayout;
	
	private static final int BUTTON_WIDTH = 80;
	
	private static final String[] FIELDS_NAME = new String[] {"rate"}; 
	
	private static BidFieldFactory bidFieldFactory = BidFieldFactorySinglton.getBidFieldFactory();
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	
	
	private AddBidListener listener;
	
	private Bid bid;
	
	private static final Logger LOGGRER = LogManager.getLogger(AddBidDialog.class);
	 
	public AddBidDialog(Bid bid){
		this.bid = bid;
		
	}
	
	private AddBidDialog getThisWindow(){
		return this;
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
		getFrom().setVisibleItemProperties(FIELDS_NAME);	
		
		mainHorizontalLayout.addComponent(getFrom());
		mainHorizontalLayout.addComponent(getBuckLabel());
		mainHorizontalLayout.addComponent(getAddBidButton());
		mainHorizontalLayout.setComponentAlignment(getAddBidButton(),Alignment.MIDDLE_RIGHT);
		mainHorizontalLayout.setSizeUndefined();

		addAddBidButtonListener();
	}


	@SuppressWarnings("serial")
	private void addAddBidButtonListener() {
		getAddBidButton().addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
				
				BaseResponse response = client.addBid(bid);
				if( response.getStateResult().equals(StateResult.SUCCESS) ){
					bid.setIdBid(response.getIdEntity());
					if( null != listener ){
						listener.thisLotAdded(bid);
					}
					closeThisDialog();
				} else if( response.getStateResult().equals(StateResult.NOT_SUCCESS) ){
					getApplication().getMainWindow().showNotification(response.getErrorMessage(),
											Notification.TYPE_WARNING_MESSAGE);
					clearBidField();
				} else{
					getApplication().getMainWindow().showNotification(response.getErrorMessage(),
							Notification.TYPE_ERROR_MESSAGE);
					closeThisDialog();
				}			
	
			} catch (InvalidValueException e) {
				 LOGGRER.info("An incorrect input data addAddBidButtonListener={}, reason={}", e, e.getMessage());
				
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
	
	public void setAddBidListener(AddBidListener listener){
		this.listener = listener;
	}
	
	private void clearBidField(){
		bid.setRate(null);
	}
	
	private void closeThisDialog(){
		getParent().removeWindow(getThisWindow());
	}
}
