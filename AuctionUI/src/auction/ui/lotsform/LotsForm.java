package auction.ui.lotsform;

import auction.ui.ClientAuctionSinglton;
import auction.ui.addlot.AddLotDialog;
import auction.ui.addlot.AddLotListener;
import auction.ui.addlot.RandomInt;
import client.artefacts.GetLotsResponse;
import client.artefacts.Lot;
import client.artefacts.LotState;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.realization.ClientAuction;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;


public class LotsForm extends Form {

	private static final long serialVersionUID = 1L;
	

	private Table lotsTable;
	private Button newLotButton;
	
	private static final String[] HEADER_LOTS_TABLE = new String[] { "Code", "Name", "Finish date", "State"}; 
	
	private static final Object[] COLUMS_NAME = new Object[]{"code", "name","finishDate", "state"};	
	
	
	
	BeanItemContainer<Lot> beans = new BeanItemContainer<Lot>(Lot.class);
	//List<Lot> lots;
	
	User user;
	
	ClickedLotListener listener;
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	
	
	private HorizontalLayout footer;

	
	public LotsForm(User user){
		this.user = user;
	}

	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		setCaption("Lots");
		setLayout(layout);
		layout.setSizeFull();
		layout.setMargin(true);
		
		getAllLots();
		
		layout.addComponent(getLotsTable());
		layout.addComponent(getHozizontalLayoutWithButton());
		
		layout.setExpandRatio(getLotsTable(), 1);
		layout.setExpandRatio(getHozizontalLayoutWithButton(), 0);
		layout.setComponentAlignment(getHozizontalLayoutWithButton(), Alignment.MIDDLE_RIGHT);
		setSizeFull();
		//addLot(new Lot("code", "name", "finishDate", "state"));
		buttonNewLotClick();
		
	}
	
	
	private HorizontalLayout getHozizontalLayoutWithButton(){
		if( null == footer ){
			footer = new HorizontalLayout();
			footer.setMargin(true, false, false, false);
			footer.addComponent(getNewLotButton());
			footer.setSizeUndefined();			
			//footer.setComponentAlignment(getNewLotButton(),Alignment.MIDDLE_RIGHT);
		}
		return footer;
	}	

	
	public void getAllLots() {
		GetLotsResponse response = client.getAllLots();
		if( response.getStateResult().equals(StateResult.SUCCESS) ){
			beans.addAll(response.getListLots());
		} else {
			getApplication().getMainWindow().showNotification(response.getErrorMessage(),
					Notification.TYPE_ERROR_MESSAGE);
		}		

	}

	private Table getLotsTable() {
		if (lotsTable == null) {

			lotsTable = new Table("",beans);
			lotsTable.setSizeFull();

			lotsTable.setVisibleColumns(COLUMS_NAME);
			lotsTable.setColumnHeaders(HEADER_LOTS_TABLE);
			
			lotsTable.setSelectable(true);
			lotsTable.setImmediate(true);
			if( !beans.getItemIds().isEmpty() ){
				lotsTable.setValue(beans.getIdByIndex(0));
			}	

			lotsTable.addListener(new ItemClickEvent.ItemClickListener() {
				private static final long serialVersionUID = 1L;
				@Override
			    public void itemClick(ItemClickEvent itemClickEvent) {
					if( (null != listener) && (null != lotsTable.getValue()) ){
						listener.thisLotCliked((Lot)itemClickEvent.getItemId());
						lotsTable.setValue(itemClickEvent.getItemId());
						//System.out.println(((Lot)itemClickEvent.getItemId()).getName());
					}
			    }
			});
			

		}
		return lotsTable;
	}
	
	public Lot getCurrentLot(){
		return (Lot)lotsTable.getValue();
	}
	
	public void refreshTableValue(Lot lot){
		//lotsTable.setValue(lot);
		lotsTable.refreshRowCache();
	}

	
	public Button getNewLotButton() {
		if (newLotButton == null) {
			newLotButton = new Button("New lot");
			newLotButton.setWidth(80, UNITS_PIXELS);
		}
		return newLotButton;
	}
	
	
	@SuppressWarnings("serial")	
	public void buttonNewLotClick(){
		newLotButton.addListener(new ClickListener() {				
		@Override
		public void buttonClick(ClickEvent event) {

			Lot lot = new Lot();
			lot.setUser(user);
			lot.setState(LotState.ACTIVE);
			lot.setCode(RandomInt.randInt());
			AddLotDialog addLotDialog = new AddLotDialog(lot);
			getApplication().getMainWindow().addWindow(addLotDialog);
			
			addLotDialog.setAddLotListener(new AddLotListener(){
				@Override
				public void thisLotAdded(Lot Lot) {
					beans.addBean(lot);
					lotsTable.setValue(lot);
					if( null != listener ){
						listener.thisLotCliked((Lot)lotsTable.getValue());
					}
				}
				
				
			});
			

			
		}
		});		 
	 }

	public void setClickedLotListener(ClickedLotListener listener){
		this.listener = listener;
	}
	
	public User getUser(){
		return this.user;
	}

}
 