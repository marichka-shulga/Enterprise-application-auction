package auction.ui.bidsform;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private LotsForm lotsForm;
	
	private static final String[] HEADER_LOTS_TABLE = new String[] { "Bid", "Date", "Bidder"}; 
	
	private static final Object[] COLUMS_NAME = new Object[]{"rate", "dateAdding","user"};	
	
	private BeanItemContainer<BidDelegate> beans = new BeanItemContainer<BidDelegate>(BidDelegate.class);
	
	private static final Logger LOGGRER = LogManager.getLogger(BidsForm.class);
	
	public BidsForm(LotsForm lotsForm){
		this.lotsForm = lotsForm;
		
		refreshBidsForm(lotsForm.getBidsForCurrentLot());
		
		this.lotsForm.setClickedLotListener(new ClickedLotListenerForBidsForm(){
			@Override
			public void bidsForClickedLot(List<BidDelegate> bids) {
				refreshBidsForm(bids);
				
			}});
		
		addNewBidButtonListener();
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
	}	
	
	private void checkEnableAddBidButton(){
		if(null != lotsForm.getCurrentLotDelegate() ){
			if( (!lotsForm.getCurrentLotDelegate().getLot().getUser().getIdUser().equals(lotsForm.getUser().getIdUser())) && 
					lotsForm.getCurrentLotDelegate().getState().equals(LotState.ACTIVE) ){
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
	
	private void initTable(List<BidDelegate> bids){
		if( !beans.getItemIds().isEmpty() ){
			beans.removeAllItems();
		}

		if( null == bids ){
			beans.addAll(new ArrayList<BidDelegate>());
		} else{
			beans.addAll(bids);
		} 
		
		getBidsTable().refreshRowCache();
		
		if( null != lotsForm.getCurrentLotDelegate() && lotsForm.getCurrentLotDelegate().getState().equals(LotState.SOLD) ){
			Iterator<BidDelegate> it = beans.getItemIds().iterator();
			BidDelegate bidDelegate;
			while(it.hasNext()){
				bidDelegate = it.next();
				if( bidDelegate.isIsWinningBid() ){
					getBidsTable().setValue(bidDelegate);
					break;
				}
			}
			getBidsTable().setSelectable(false);
		} else {
			getBidsTable().setSelectable(true);
			getBidsTable().setImmediate(true);
			if( !beans.getItemIds().isEmpty() ){
				getBidsTable().setValue(beans.getIdByIndex(0));
			}	
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
	public void addNewBidButtonListener(){
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
				LOGGRER.error("Is not satisfied addNewBidButtonListener={}, reason={}", e, e.getMessage());	

			}
			bid.setDateAdding(xmlCalendar);
			bid.setLot(lotsForm.getCurrentLotDelegate().getLot());
			bid.setUser(lotsForm.getUser());
			
			AddBidDialog addBidDialog = new AddBidDialog(bid);
			getApplication().getMainWindow().addWindow(addBidDialog);
			addBidDialog.setAddBidListener(new AddBidListener(){

				@Override
				public void thisLotAdded(Bid bid) {
					BidDelegate bidDelegate = new BidDelegate(bid);
					beans.addBean(bidDelegate);
					getBidsTable().setValue(bidDelegate);
					lotsForm.getBidsForCurrentLot().add(bidDelegate);
					
				}
			});
		}
		});		 
	 }	
	
	
	public void refreshBidsForm(List<BidDelegate> bids){
		initTable(bids);	
		checkEnableAddBidButton();
	}
}
