package auction.ui.lotdetails;

import java.util.Arrays;
import java.util.Calendar;
	
import org.vaadin.kim.countdownclock.CountdownClock;

import auction.ui.ClientAuctionSinglton;

import auction.ui.addlot.LotDelegate;
import auction.ui.lotsform.ClickedLotListenerForLotDetailsForm;
import auction.ui.lotsform.LotsForm;
import client.artefacts.BaseResponse;
import client.artefacts.Lot;
import client.artefacts.LotState;
import client.artefacts.StateResult;
import client.realization.ClientAuction;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

public class LotDetailsForm extends Form {

	private static final long serialVersionUID = 1L;
	private VerticalLayout fieldLayout;
	private HorizontalLayout butonLayout;
	
	private Form from; 
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	
	
	private LotDelegate infLot;
	private BeanItem<LotDelegate> lotItem;
	private LotsForm lotsForm;
	
	CountdownClock remainingTimeField = null;
	
	
	public LotDetailsForm(LotsForm lotsForm){
		this.lotsForm = lotsForm;		
		infLot = new LotDelegate(new Lot());
		this.lotsForm.setClickedLotListener(new ClickedLotListenerForLotDetailsForm(){
			@Override
			public void thisLotCliked(Lot lot) {
				setItemDataSourceForm(lot);
			}
		});
	}
	
	private Button cancelTradesButton;
	@Override
	public void attach() {
		super.attach();
		HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
		setCaption("Lot details");
		addStyleName("bordered"); 

		setLayout(mainHorizontalLayout);
		mainHorizontalLayout.setSizeFull();
		mainHorizontalLayout.addComponent(getVerticalLayoutWithFields());
		mainHorizontalLayout.addComponent(getHozizontalLayoutWithButton());
		
		mainHorizontalLayout.setExpandRatio(getVerticalLayoutWithFields(), 1);
		mainHorizontalLayout.setExpandRatio(getHozizontalLayoutWithButton(), 0);
		mainHorizontalLayout.setComponentAlignment(getHozizontalLayoutWithButton(), Alignment.BOTTOM_RIGHT);
		mainHorizontalLayout.setComponentAlignment(getVerticalLayoutWithFields(), Alignment.TOP_LEFT);		
		setSizeFull();
		buttonCancelTradesClick();
		
	}	
	
	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == fieldLayout){
			fieldLayout = new VerticalLayout();
			fieldLayout.setMargin(false, true, false, true);
		    getFrom().setFormFieldFactory(new LotDetailsFactory());
		    
		    setItemDataSourceForm(lotsForm.getCurrentLot());
		    //getFrom().getLayout().addComponent(getRemainingTimeField());
		    fieldLayout.addComponent(getFrom());
		    
			fieldLayout.setWidth(250, UNITS_PIXELS);
		}
		return fieldLayout;
	}
	
	public void setItemDataSourceForm(Lot lot){
		if( null != lot ){
			if( (lot.getUser().getIdUser() == lotsForm.getUser().getIdUser()) && 
					lot.getState().equals(LotState.ACTIVE) ){
				if( !getCancelTradesButton().isEnabled() )
					getCancelTradesButton().setEnabled(true);			
			}
			else{
				if( getCancelTradesButton().isEnabled() )
					getCancelTradesButton().setEnabled(false);
			}

		} else{	
			getCancelTradesButton().setEnabled(false);
			lot = new Lot();
		}
		infLot.setLot(lot);
	    lotItem = new BeanItem<LotDelegate>(infLot); 
	    getFrom().setItemDataSource(lotItem);
		getFrom().setVisibleItemProperties(Arrays.asList(new String[] {
	                "code", "name", "state", "finishDate", "user",
	                "remainingTime","descriptions","startPrice"}));			
	}
	
	private HorizontalLayout getHozizontalLayoutWithButton(){
		if( null == butonLayout ){
			butonLayout = new HorizontalLayout();
			butonLayout.setMargin(false, true, true, false);
			
			butonLayout.addComponent(getRemainingTimeField());
			
			butonLayout.addComponent(getCancelTradesButton());
			
			butonLayout.setSizeUndefined();			
		}
		return butonLayout;
	}
		
	@SuppressWarnings("serial")
	public void buttonCancelTradesClick(){
		cancelTradesButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					BaseResponse response = client.cancelLot((Lot)lotsForm.getCurrentLot());
					if( response.getStateResult().equals(StateResult.SUCCESS) ){
						
						(lotsForm.getCurrentLot()).setState(LotState.CANCELLED);
						////////////////////////
						setItemDataSourceForm(lotsForm.getCurrentLot());
						lotsForm.refreshTableValue(lotsForm.getCurrentLot());
					} else{
						getApplication().getMainWindow().showNotification(response.getErrorMessage(),
								Notification.TYPE_ERROR_MESSAGE);
					}						
					

				} catch (InvalidValueException e) {
				
				}
			}
		});
	}
	
	
	public Button getCancelTradesButton(){
		if( null == cancelTradesButton ){
			cancelTradesButton = new Button("Cancel trades");
			cancelTradesButton.setWidth(105, UNITS_PIXELS);
		}
		return cancelTradesButton;
	}
	
	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	
 	private CountdownClock getRemainingTimeField() {
 		if( null == remainingTimeField ){
 			remainingTimeField = new CountdownClock();
 			  Calendar c = Calendar.getInstance();
 		        c.set(2012, 12, 21, 0, 0, 0);
 		       remainingTimeField.setDate(c.getTime());

// 			remainingTimeField.setDate(new Date());
 			remainingTimeField.setFormat("%d days, %h hours, %m minutes and %s seconds");
 		}
        return remainingTimeField;
	}
 	
}
