package auction.ui.lotsform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import auction.ui.ClientAuctionSinglton;
import auction.ui.addlot.AddLotDialog;
import auction.ui.addlot.AddLotListener;
import auction.ui.addlot.LotDelegate;
import auction.ui.addlot.RandomInt;
import auction.ui.bidsform.BidDelegate;
import auction.ui.quartz.FinishTradesJob;
import auction.ui.quartz.QuartzManager;
import auction.ui.quartz.QuartzManagerSingleton;
import client.artefacts.GetBidsByIdLotResponse;
import client.artefacts.GetLotsResponse;
import client.artefacts.Lot;
import client.artefacts.Bid;
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
	
	private static final Object[] COLUMS_NAME = new Object[]{"code", "name","finishDateInFormat", "state"};	
	
	private static final QuartzManager manager = QuartzManagerSingleton.getQuartzManager();
	
	
	private BeanItemContainer<LotDelegate> beans = new BeanItemContainer<LotDelegate>(LotDelegate.class);
	private Map<LotDelegate,List<BidDelegate>> lots;
	
	
	private User user;
	
	private ClickedLotListenerForLotDetailsForm lotClickedListenerForLotDetailsF;
	private ClickedLotListenerForBidsForm lotClickedListenerForBidsF;
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	

	
	private HorizontalLayout footer;
	
	public LotsForm(User user){
		this.user = user;
		getAllLots();
		getLotsTable();
		
		buttonNewLotClick();
	}

	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		setCaption("Lots");
		setLayout(layout);
		layout.setSizeFull();
		layout.setMargin(true);

		layout.addComponent(getLotsTable());
		
		layout.addComponent(getHozizontalLayoutWithButton());
		
		layout.setExpandRatio(getLotsTable(), 1);
		layout.setExpandRatio(getHozizontalLayoutWithButton(), 0);
		layout.setComponentAlignment(getHozizontalLayoutWithButton(), Alignment.MIDDLE_RIGHT);
		setSizeFull();

		
	}
	
//	public static LotsForm getLotsForm(){
//		return this;
//	}
	
	
	private HorizontalLayout getHozizontalLayoutWithButton(){
		if( null == footer ){
			footer = new HorizontalLayout();
			footer.setMargin(true, false, false, false);
			footer.addComponent(getNewLotButton());
			footer.setSizeUndefined();			
		}
		return footer;
	}	

	
	public void getAllLots() {
		GetLotsResponse response = client.getAllLots();
		if( response.getStateResult().equals(StateResult.SUCCESS) ){
			
			Iterator<Lot> itLots = response.getListLots().iterator();
			
			while( itLots.hasNext() ){
				Lot lot = itLots.next();
				LotDelegate lotDelegate = new LotDelegate(lot);
				beans.addBean(lotDelegate);
				if( lotDelegate.getState().equals(LotState.ACTIVE)){
					try {
						manager.addJob(String.valueOf(lotDelegate.getIdLot()),lotDelegate.getFinishDate(),FinishTradesJob.class);
					} catch (SchedulerException e) {

						///////////////////////
						/////////////////////
					
					
					}	
				}
			}
			
				
			lots = new HashMap<LotDelegate,List<BidDelegate>>();
			Iterator<LotDelegate> it = beans.getItemIds().iterator();
			GetBidsByIdLotResponse responseGetBids;
			LotDelegate lotDelegate;
			while(it.hasNext()){
				lotDelegate = it.next();
				responseGetBids = client.getBids(lotDelegate.getIdLot());
				if( responseGetBids.getStateResult().equals(StateResult.SUCCESS) ){

					List<Bid> bids = responseGetBids.getBids();
					List<BidDelegate> bidsDelegate = new ArrayList<BidDelegate>();
					
					Iterator<Bid> itBids = bids.iterator();
					while(itBids.hasNext()){
						bidsDelegate.add(new BidDelegate(itBids.next()));
					}
					lots.put(lotDelegate, bidsDelegate);
				} else {
					getApplication().getMainWindow().showNotification(responseGetBids.getErrorMessage(),
							Notification.TYPE_ERROR_MESSAGE);
				}
			}
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
					if( (null != lotClickedListenerForLotDetailsF) && (null != lotsTable.getValue()) ){
						lotsTable.setValue(itemClickEvent.getItemId());
//						System.out.println("lot "+((LotDelegate)lotsTable.getValue()).getName()+"  user "+((LotDelegate)lotsTable.getValue()).getLot().getUser().getUserLogin()+
//								"  idUser "+((LotDelegate)lotsTable.getValue()).getLot().getUser().getIdUser());


						lotClickedListenerForLotDetailsF.thisLotCliked((LotDelegate)itemClickEvent.getItemId());
					
						lotClickedListenerForBidsF.bidsForClickedLot(lots.get((LotDelegate)itemClickEvent.getItemId()));
					}
			    }
			});
			

		}
		return lotsTable;
	}
	
	public LotDelegate getCurrentLotDelegate(){
		return (LotDelegate)getLotsTable().getValue();
	}
	
	
	public List<BidDelegate> getBidsForCurrentLot(){
		return lots.get(getCurrentLotDelegate());
	}

	
	public LotDelegate getLotDelegateByIdLot(Integer idLot){
		Iterator<LotDelegate> it = beans.getItemIds().iterator();
		LotDelegate lotDelegate;
		while(it.hasNext()){
			lotDelegate = it.next();
			if( lotDelegate.getIdLot().equals(idLot) ){
				return lotDelegate;
			}

		}		

		return null;
	}
	
	
	public void refreshTableValue(){
		getLotsTable().setValue(getCurrentLotDelegate());
		getLotsTable().refreshRowCache();
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
		getNewLotButton().addListener(new ClickListener() {				
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
				public void thisLotAdded(LotDelegate lotDelegate) {
					beans.addBean(lotDelegate);
					getLotsTable().setValue(lotDelegate);
					try {
						manager.addJob(String.valueOf(lotDelegate.getIdLot()),lotDelegate.getFinishDate(),FinishTradesJob.class);
					} catch (SchedulerException e) {

						///////////////////////
						/////////////////////
						
						
					}
					if( null != lotClickedListenerForLotDetailsF ){
						lotClickedListenerForLotDetailsF.thisLotCliked((LotDelegate)getLotsTable().getValue());
					}

					lots.put((LotDelegate)getLotsTable().getValue(), new ArrayList<BidDelegate>());
					if( null != lotClickedListenerForBidsF ){
						lotClickedListenerForBidsF.bidsForClickedLot(lots.get((LotDelegate)getLotsTable().getValue()));
					}					
					
				}

			});
		}
		});		 
	 }

	public void setClickedLotListener(ClickedLotListenerForLotDetailsForm listener){
		this.lotClickedListenerForLotDetailsF = listener;
	}
	
	public void setClickedLotListener(ClickedLotListenerForBidsForm listener){
		this.lotClickedListenerForBidsF = listener;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
		
		if( !beans.getItemIds().isEmpty() ){
			getLotsTable().setValue(beans.getIdByIndex(0));
		}	
		
		if( null != lotClickedListenerForLotDetailsF ){
			lotClickedListenerForLotDetailsF.thisLotCliked(getCurrentLotDelegate());
		}

		if( null != lotClickedListenerForBidsF ){
			lotClickedListenerForBidsF.bidsForClickedLot(lots.get(getCurrentLotDelegate()));
		}	
	}
	
	public void setWinningBid(Bid bid, LotDelegate lotDelegate){
//		List<BidDelegate> bidsDelegate = lots.get(lotDelegate);
		Iterator<BidDelegate> it = lots.get(lotDelegate).iterator();
		BidDelegate bidDelegate;
		while( it.hasNext() ){
			bidDelegate = it.next();
			if( bidDelegate.getBid().getIdBid().equals(bid.getIdBid()) ){
				bidDelegate.getBid().setIsWinningBid(bid.isIsWinningBid());
				break;
			}

		}
		
		
		
	}
	
	
}
 