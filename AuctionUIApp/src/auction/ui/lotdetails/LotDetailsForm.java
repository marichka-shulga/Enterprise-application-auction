package auction.ui.lotdetails;

import auction.ui.ClientAuctionSinglton;
import auction.ui.addlot.LotDelegate;
import auction.ui.lotsform.ClickedLotListenerForLotDetailsForm;
import auction.ui.lotsform.LotsForm;
import client.artefacts.BaseResponse;
import client.artefacts.Lot;
import client.artefacts.LotState;
import client.artefacts.StateResult;
import client.realization.ClientAuction;

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
	private static final int VERTICAL_LAYOUT_WIDTH = 70;
	private Form from; 
	
	private static LotDetailsFactory lotDetailsFactory = LotDetailsFactorySingleton.getLotDetailsFactory();
	
	private static final String[] FIELDS_NAME = new String[] {"code", "name", "state", "finishDate", "user",
        "remainingTime","descriptions","startPraceInString"}; 
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	
	
	private LotDelegate infLot;
	private BeanItem<LotDelegate> lotItem;
	private LotsForm lotsForm;
	
	public LotDetailsForm(LotsForm lotsForm){
		this.lotsForm = lotsForm;		
		this.lotsForm.setClickedLotListener(new ClickedLotListenerForLotDetailsForm(){
			@Override
			public void thisLotCliked(LotDelegate lotDelegate) {
				refreshLotDetailsForm(lotDelegate);
			}
		});
		
		addCancelTradesButtonListener();
	}
	
	private Button cancelTradesButton;
	@Override
	public void attach() {
		super.attach();
		HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
		setCaption("Lot details");

		setLayout(mainHorizontalLayout);
		mainHorizontalLayout.setSizeFull();
		mainHorizontalLayout.addComponent(getVerticalLayoutWithFields());
		mainHorizontalLayout.addComponent(getHozizontalLayoutWithButton());
		
		mainHorizontalLayout.setExpandRatio(getVerticalLayoutWithFields(), 1);
		mainHorizontalLayout.setExpandRatio(getHozizontalLayoutWithButton(), 0);
		mainHorizontalLayout.setComponentAlignment(getHozizontalLayoutWithButton(), Alignment.BOTTOM_RIGHT);
		mainHorizontalLayout.setComponentAlignment(getVerticalLayoutWithFields(), Alignment.TOP_LEFT);		
		setSizeFull();

	}	
	
	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == fieldLayout){
			fieldLayout = new VerticalLayout();
			fieldLayout.setMargin(false, true, false, true);
		    getFrom().setFormFieldFactory(lotDetailsFactory);
			refreshLotDetailsForm(lotsForm.getCurrentLotDelegate());
		    fieldLayout.addComponent(getFrom());
		    fieldLayout.setWidth(VERTICAL_LAYOUT_WIDTH, UNITS_PERCENTAGE);
		}
		return fieldLayout;
	}
	
	public void setItemDataSourceForm(){
		if( null != infLot ){
			if( (infLot.getLot().getUser().getIdUser().equals(lotsForm.getUser().getIdUser())) && 
					infLot.getState().equals(LotState.ACTIVE) ){
				if( !getCancelTradesButton().isVisible() )
					getCancelTradesButton().setVisible(true);			
			}
			else{
				if( getCancelTradesButton().isVisible() )
					getCancelTradesButton().setVisible(false);
			}

		} else{	
			getCancelTradesButton().setVisible(false);
			infLot = new LotDelegate(new Lot());
		}
	    lotItem = new BeanItem<LotDelegate>(infLot); 
	    getFrom().setItemDataSource(lotItem);
		getFrom().setVisibleItemProperties(FIELDS_NAME);		
	}
	
	private HorizontalLayout getHozizontalLayoutWithButton(){
		if( null == butonLayout ){
			butonLayout = new HorizontalLayout();
			butonLayout.setMargin(false, true, true, false);
			butonLayout.addComponent(getCancelTradesButton());
			
			butonLayout.setSizeUndefined();			
		}
		return butonLayout;
	}
		
	@SuppressWarnings("serial")
	public void addCancelTradesButtonListener(){
		getCancelTradesButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				BaseResponse response = null;
				try{
					response = client.cancelLot(lotsForm.getCurrentLotDelegate().getLot());
					if( response.getStateResult().equals(StateResult.SUCCESS) ){
						(lotsForm.getCurrentLotDelegate()).setState(LotState.CANCELLED);
						infLot = lotsForm.getCurrentLotDelegate();
						setItemDataSourceForm();
						lotsForm.refreshTableValue();
					} else if( response.getStateResult().equals(StateResult.NOT_SUCCESS) ){
						getApplication().getMainWindow().showNotification("Lot can not be canceled because the trades have already finished",
								Notification.TYPE_WARNING_MESSAGE);
					} else{
						getApplication().getMainWindow().showNotification(response.getErrorMessage(),
								Notification.TYPE_ERROR_MESSAGE);
					}	

				} catch(Exception e){
					getApplication().getMainWindow().showNotification("Lot can not be canceled",
							Notification.TYPE_WARNING_MESSAGE);
					
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

	public void refreshLotDetailsForm(LotDelegate lotDelegate){
		infLot = lotDelegate;
		setItemDataSourceForm();
	}
 	
}
