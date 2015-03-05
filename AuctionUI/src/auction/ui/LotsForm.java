package auction.ui;

import auction.ui.authentication.UserAuthenticationDialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;


public class LotsForm extends Form {

	private static final long serialVersionUID = 1L;
	

	private Table lotsTable;
	private Button newLotButton;
	
	private HorizontalLayout footer;

	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		setCaption("Lots");
		//getLayout().setMargin(true);
		addStyleName("bordered"); 
		//layout.addStyleName(getStyle("bordered"));
		setLayout(layout);
		layout.setSizeFull();
		layout.setMargin(true);
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
	
	
//	public void addLot(Lot lot) {
//		getLotsTable().addItem(lot);
//		getLotsTable().getContainerProperty(lot, "code").setValue(lot.getCode());
//		getLotsTable().getContainerProperty(lot, "name").setValue(lot.getName());
//		getLotsTable().getContainerProperty(lot, "finishDate").setValue(lot.getFinishDate());
//		getLotsTable().getContainerProperty(lot, "state").setValue(lot.getState());
//	}

	private Table getLotsTable() {
		if (lotsTable == null) {
			lotsTable = new Table();
			lotsTable.setSizeFull();
			lotsTable.addContainerProperty("code", String.class, "");
			lotsTable.addContainerProperty("name", String.class, "");
			lotsTable.addContainerProperty("finishDate", String.class, "");
			lotsTable.addContainerProperty("state",	String.class, "");
			// lotsTable.setColumnHeader(propertyId, header);
			
		}
		return lotsTable;
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
			getApplication().getMainWindow().addWindow(new UserAuthenticationDialog());
		}
		});		 
	 }
	

}
 