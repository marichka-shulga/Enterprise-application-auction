package auction.ui;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class LotsForm extends Form {

	private static final long serialVersionUID = 1L;
	

	private Table lotsTable;
	private Button button;

	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		setCaption("Lots");
		addStyleName("bordered"); 
		setLayout(layout);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.addComponent(getLotsTable());
		

		layout.addComponent(getButton());
		layout.setExpandRatio(getLotsTable(), 1);

		setSizeFull();
		addLot(new Lot("code", "name", "finishDate", "state"));
	}
	
	public void addLot(Lot lot) {
		getLotsTable().addItem(lot);
		getLotsTable().getContainerProperty(lot, "code").setValue(lot.getCode());
		getLotsTable().getContainerProperty(lot, "name").setValue(lot.getName());
		getLotsTable().getContainerProperty(lot, "finishDate").setValue(lot.getFinishDate());
		getLotsTable().getContainerProperty(lot, "state").setValue(lot.getState());
	}

	private Table getLotsTable() {
		if (lotsTable == null) {
			lotsTable = new Table();
			lotsTable.setSizeFull();
			lotsTable.addContainerProperty("code",
					String.class, "");
			lotsTable.addContainerProperty("name",
					String.class, "");
			lotsTable.addContainerProperty(
					"finishDate", String.class, "");
			lotsTable.addContainerProperty("state",
					String.class, "");
			// lotsTable.setColumnHeader(propertyId, header);

			
			
		}
		return lotsTable;
	}

	@SuppressWarnings("serial")
	public Button getButton() {
		if (button == null) {
			button = new Button("Add lot");
			button.addListener(new ClickListener() {				
				@Override
				public void buttonClick(ClickEvent event) {
					//getApplication().getMainWindow().addWindow(new AddLotDialog());		UserRegistrationDialog		
					getApplication().getMainWindow().addWindow(new UserAuthenticationDialog());
				}
			});
		}
		return button;
	}
	
	

}
 