package auction.ui.bidsform;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import auction.ui.addbid.AddBidDialog;
import auction.ui.addbid.AddBidListener;
import auction.ui.lotsform.ClickedLotListenerForBidsForm;
import auction.ui.lotsform.LotsForm;
import client.artefacts.Bid;
import client.artefacts.LotState;





import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class BidsForm extends Form {

	private static final long serialVersionUID = 1L;
	private Table bidsTable;
	private Button newBidButton;
	
	private HorizontalLayout footer;	
	
	//private static ClientAuction client = ClientAuctionSinglton.getClientAuction();		
	private LotsForm lotsForm;
	
	private static final String[] HEADER_LOTS_TABLE = new String[] { "Bid", "Date", "Bidder"}; 
	
	private static final Object[] COLUMS_NAME = new Object[]{"rate", "dateAdding","user"};	
	
	private BeanItemContainer<BidDelegate> beans = new BeanItemContainer<BidDelegate>(BidDelegate.class);
	
	public BidsForm(LotsForm lotsForm){
		this.lotsForm = lotsForm;
		
		if( null == lotsForm.getBidsForCurrentLot() ){
			beans.addAll(new ArrayList<BidDelegate>());
		} else{
			beans.addAll(lotsForm.getBidsForCurrentLot());
		}	
		checkEnableAddBidButton();
		this.lotsForm.setClickedLotListener(new ClickedLotListenerForBidsForm(){
			@Override
			public void bidsForClickedLot(List<BidDelegate> bids) {
				beans.removeAllItems();
				beans.addAll(bids);
				if( !beans.getItemIds().isEmpty()){
					getBidsTable().setValue(beans.getIdByIndex(0));
				}
				getBidsTable().refreshRowCache();
				
				checkEnableAddBidButton();
				
			}});
	}
	
	
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
		buttonNewBidClick();
		
	}	
	
	private void checkEnableAddBidButton(){
		if( null != lotsForm.getCurrentLot() ){
			if( (lotsForm.getCurrentLot().getUser().getIdUser() != lotsForm.getUser().getIdUser()) && 
					lotsForm.getCurrentLot().getState().equals(LotState.ACTIVE) ){
				if( !getNewBidButton().isEnabled() )
					getNewBidButton().setEnabled(true);			
			}
			else{
				if( getNewBidButton().isEnabled() )
					getNewBidButton().setEnabled(false);
			}

		} else{	
			getNewBidButton().setEnabled(false);
		}		
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
			bidsTable = new Table("",beans);
			bidsTable.setSizeFull();

			bidsTable.setVisibleColumns(COLUMS_NAME);
			bidsTable.setColumnHeaders(HEADER_LOTS_TABLE);
			
			bidsTable.setSelectable(true);
			bidsTable.setImmediate(true);
			if( !beans.getItemIds().isEmpty() ){
				bidsTable.setValue(beans.getIdByIndex(0));
			}	
		
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
			
			Bid bid = new Bid();
			bid.setIsWinningBid(false);
			GregorianCalendar gCalendar = new GregorianCalendar();
			gCalendar.setTime(new Date());
			XMLGregorianCalendar xmlCalendar = null;
			try {
				xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
			} catch (DatatypeConfigurationException e) {
				

			}
			bid.setDateAdding(xmlCalendar);
			bid.setLot(lotsForm.getCurrentLot());
			bid.setUser(lotsForm.getUser());
//			System.out.println(lotsForm.getUser().getUserLogin());
//			System.out.println(lotsForm.getCurrentLot().getName());		
			
			AddBidDialog addBidDialog = new AddBidDialog(bid);
			getApplication().getMainWindow().addWindow(addBidDialog);
			addBidDialog.setAddBidListener(new AddBidListener(){

				@Override
				public void thisLotAdded(Bid bid) {
					BidDelegate bidDelegate = new BidDelegate(bid);
					beans.addBean(bidDelegate);
					getBidsTable().setValue(bidDelegate);
					
				}
			});
		}
		});		 
	 }	

}
