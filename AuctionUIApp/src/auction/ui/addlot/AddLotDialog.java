package auction.ui.addlot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import auction.ui.ClientAuctionSinglton;
import client.artefacts.BaseResponse;
import client.artefacts.Lot;
import client.artefacts.StateResult;
import client.realization.ClientAuction;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class AddLotDialog extends Window {

	private static final long serialVersionUID = 1L;
	
	private static LotFieldFactory lotFieldFactory = LotFieldFactorySinglton.getLotFieldFactory();
	
	private Form from;

	private Button createButton;
	private Button cancelButton;	
	
	private HorizontalLayout footer;
	private VerticalLayout body;	
	
	private static final int COMMON_BUTTON_WIDTH = 80;
	private static final int WINDOW_WIDTH = 350;
	
	private static final String[] FIELDS_NAME = new String[] {"name", "finishDate", "startPrice", "descriptions"}; 
	
	private Lot lot;
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();
	
	private static final Logger LOGGRER = LogManager.getLogger(AddLotDialog.class);
	
	private AddLotListener listener;
	
	public AddLotDialog(Lot lot){
		this.lot = lot;
	}
	
	
	public void attach() {
		this.setCaption("New lot");
		this.center();
		this.setResizable(false);

		VerticalLayout mainVerticalLayout = new VerticalLayout();
		setContent(mainVerticalLayout);		
		mainVerticalLayout.setSizeFull();
		
		mainVerticalLayout.addComponent(getVerticalLayoutWithFields());
		mainVerticalLayout.addComponent(getHozizontalLayoutWithButtons());
		mainVerticalLayout.setComponentAlignment(getHozizontalLayoutWithButtons(),Alignment.MIDDLE_RIGHT);
		mainVerticalLayout.setExpandRatio(getVerticalLayoutWithFields(),1);		
		mainVerticalLayout.setExpandRatio(getHozizontalLayoutWithButtons(), 0);

		mainVerticalLayout.setSizeUndefined();

		addCreateButtonListener();
		addCancelButtonListener();
	}
	
	private AddLotDialog getThisWindow(){
		return this;
	}

	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == body){
			body = new VerticalLayout();
			body.setMargin(false, true, false, true);
			
			LotDelegate newLot = new LotDelegate(lot);
			
			BeanItem<LotDelegate> lotItem = new BeanItem<LotDelegate>(newLot); 
		    getFrom().setFormFieldFactory(lotFieldFactory);
		    getFrom().setItemDataSource(lotItem); 
			getFrom().setVisibleItemProperties(FIELDS_NAME);

			body.addComponent(getFrom());
			body.setWidth(WINDOW_WIDTH, UNITS_PIXELS);		
		}
		return body;
		
	}
	private HorizontalLayout getHozizontalLayoutWithButtons(){
		if( null == footer ){
			footer = new HorizontalLayout();
			footer.setMargin(false, true, true, true);
			footer.setSpacing(true);			
			footer.addComponent(getCreateButton());
			footer.addComponent(getCancelButton());					
			footer.setSizeUndefined();			

			footer.setComponentAlignment(getCreateButton(),Alignment.MIDDLE_RIGHT);
			footer.setComponentAlignment(getCancelButton(),Alignment.MIDDLE_RIGHT);
		}
		return footer;
	}

	@SuppressWarnings("serial")
	private void addCreateButtonListener() {
		createButton.addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
				BaseResponse response = client.addLot(lot);
				if( response.getStateResult().equals(StateResult.SUCCESS) ){
					lot.setIdLot(response.getIdEntity());
					if( null != listener ){
						listener.thisLotAdded(new LotDelegate(lot));
					}
					closeThisDialog();
				} else if( response.getStateResult().equals(StateResult.NOT_SUCCESS) ){
					getApplication().getMainWindow().showNotification("Finish date is incorrect",
											Notification.TYPE_WARNING_MESSAGE);
					clearBidField();
				} else{
					getApplication().getMainWindow().showNotification(response.getErrorMessage(),
							Notification.TYPE_ERROR_MESSAGE);
					closeThisDialog();
				}				
			} catch (InvalidValueException e) {
				 LOGGRER.info("An incorrect input data addCreateButtonListener={}, reason={}", e, e.getMessage());
			}
		}
	});
}

	@SuppressWarnings("serial")
	private void addCancelButtonListener() {
		cancelButton.addListener(new ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getParent().removeWindow(getThisWindow());
			} catch (InvalidValueException e) {
				 LOGGRER.error("Is not satisfied addCancelButtonListener={}, reason={}", e, e.getMessage());
			}
		}
	});
}

	public Button getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new Button("Cancel");
			cancelButton.setWidth(COMMON_BUTTON_WIDTH, UNITS_PIXELS);
		}
		return cancelButton;
	}	
	public Button getCreateButton() {
		if (createButton == null) {
			createButton = new Button("Create");
			createButton.setWidth(COMMON_BUTTON_WIDTH, UNITS_PIXELS);

		}
		return createButton;
	}

	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	public void setAddLotListener(AddLotListener listener){
		this.listener = listener;
	}
	
	public void setLot(Lot lot){
		this.lot = lot;
		System.out.println("Set lot"+lot.getName());
	}
	
	private void clearBidField(){
		lot.setFinishDate(null);
	}
	
	private void closeThisDialog(){
		getParent().removeWindow(getThisWindow());
	}
}
