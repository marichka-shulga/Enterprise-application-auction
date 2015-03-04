package auction.ui;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class AddLotDialog extends Window {

	private static final long serialVersionUID = 1L;
	private Form from;
	private TextField nameField;
	private TextField startPriceField;
	private TextField descriptionField;
	
	private PopupDateField finishDate;
	
	private Button createButton;
	private Button cancelButton;	
	
	private HorizontalLayout footer;
	private VerticalLayout body;	
	
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

		buttonCreateClick();
		buttonCancelClick();
	}
	
	private AddLotDialog getThisWindow(){
		return this;
	}

	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == body){
			body = new VerticalLayout();
			body.setMargin(false, true, false, true);
			getFrom().addField("name", getNameField());
			getFrom().addField("finishDate", getFinishDateField());
			getFrom().addField("startPrice", getStartPriceField());
			getFrom().addField("description", getDescriptionField());
			body.addComponent(getFrom());
			body.setWidth(350, UNITS_PIXELS);		
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
	private void buttonCreateClick() {
		createButton.addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
			} catch (InvalidValueException e) {
				
			}
		}
	});
}

	@SuppressWarnings("serial")
	private void buttonCancelClick() {
		cancelButton.addListener(new ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				
				getParent().removeWindow(getThisWindow());
				
			} catch (InvalidValueException e) {
				
			}
		}
	});
}
	

	public Button getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new Button("Cancel");
			cancelButton.setWidth(80, UNITS_PIXELS);
		}
		return cancelButton;
	}	
	public Button getCreateButton() {
		if (createButton == null) {
			createButton = new Button("Create");
			createButton.setWidth(80, UNITS_PIXELS);

		}
		return createButton;
	}
	
	public TextField getNameField() {
		if (nameField == null) {
			nameField = new TextField();
			nameField.setCaption("Lot name");
			nameField.setRequired(true);
			nameField.setRequiredError("Empty field name lot");
			nameField.setWidth(100, UNITS_PERCENTAGE);			
		}
		return nameField;
	}

	public TextField getDescriptionField() {
		if (descriptionField == null) {
			descriptionField = new TextField();
			descriptionField.setCaption("Description");
			descriptionField.setWidth(100, UNITS_PERCENTAGE);
			descriptionField.setHeight(5, UNITS_PERCENTAGE);
		}
		return descriptionField;
	}

	public TextField getStartPriceField() {
		if (startPriceField == null) {
			startPriceField = new TextField();
			startPriceField.setCaption("Start price");
			startPriceField.setRequired(true);
			startPriceField.setRequiredError("Empty field start prise");
			startPriceField.addValidator(new DoubleValidator("Incorrectly format starting price"));			
			startPriceField.setWidth(100, UNITS_PERCENTAGE);
		}
		return startPriceField;
	}


	public PopupDateField getFinishDateField() {
		if (finishDate == null) {
			finishDate = new PopupDateField();
			finishDate.setCaption("Finish date");
			finishDate.setDateFormat("dd.MM.yyyy hh:mm:ss");
			finishDate.setRequired(true);
			finishDate.setRequiredError("Empty field finish date trades");
			finishDate.setWidth(100, UNITS_PERCENTAGE);
		}
		return finishDate;
	}

	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	
	
	
}
