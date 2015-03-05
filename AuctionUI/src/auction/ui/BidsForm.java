package auction.ui;

import auction.ui.addbid.AddBidDialog;

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
			getApplication().getMainWindow().addWindow(new AddBidDialog());
		}
		});		 
	 }	

}
