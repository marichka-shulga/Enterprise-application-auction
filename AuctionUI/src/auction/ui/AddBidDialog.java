package auction.ui;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
public class AddBidDialog extends Window {
	

	private static final long serialVersionUID = 1L;
	private Form from;
	private TextField bidRateField;
	private Label buckLabel;
	
	private Button addBidButton;
	
	private static final String BID = "<H2>$</H2>";
	
	private HorizontalLayout mainHorizontalLayout;
	
	public void attach() {
		
		this.setCaption("New bid");
		this.center();
		this.setResizable(false);

		mainHorizontalLayout = new HorizontalLayout();
		mainHorizontalLayout.setMargin(false, true, false, true);
		mainHorizontalLayout.setSpacing(true);
		setContent(mainHorizontalLayout);		
		mainHorizontalLayout.setSizeFull();
		getFrom().addField("bid", getBidRateField());
		mainHorizontalLayout.addComponent(getFrom());
		mainHorizontalLayout.addComponent(getBuckLabel());
		mainHorizontalLayout.addComponent(getAddBidButton());
		mainHorizontalLayout.setComponentAlignment(getAddBidButton(),Alignment.MIDDLE_RIGHT);
		mainHorizontalLayout.setSizeUndefined();

		buttonAddBidClick();
	}
	

	@SuppressWarnings("serial")
	private void buttonAddBidClick() {
		addBidButton.addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
			} catch (InvalidValueException e) {
				
			}
		}
	});
}

	public Button getAddBidButton() {
		if (addBidButton == null) {
			addBidButton = new Button("OK");
			addBidButton.setWidth(80, UNITS_PIXELS);
		}
		return addBidButton;
	}
	
	public TextField getBidRateField() {
		if (bidRateField == null) {
			bidRateField = new TextField();
			bidRateField.setCaption("Bid");
			bidRateField.setRequired(true);
			bidRateField.setRequiredError("Empty field login");
			bidRateField.addValidator(new DoubleValidator("Incorrectly format rate"));
		}
		return bidRateField;
	}

	public Label getBuckLabel() {
		if (buckLabel == null) {
			buckLabel = new Label(BID);
			buckLabel.setContentMode(Label.CONTENT_XHTML);
		}
		return buckLabel;
	}


	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	

}
