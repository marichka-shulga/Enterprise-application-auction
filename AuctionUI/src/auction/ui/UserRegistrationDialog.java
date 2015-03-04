package auction.ui;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UserRegistrationDialog extends Window {

	private static final long serialVersionUID = 1L;
	private Form from;
	private TextField loginField;
	private PasswordField passwordField;
	private TextField firstNameField;
	private TextField lastNameField;
	
	private Button registerButton;
	private Button cancelButton;	
	
	private HorizontalLayout footer;
	private VerticalLayout body;	
	
	public void attach() {
		
		this.setCaption("Registration");
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

		buttonRegisterClick();
		buttonCancelClick();
	}
	
	private UserRegistrationDialog getThisWindow(){
		return this;
	}

	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == body){
			body = new VerticalLayout();
			body.setMargin(false, true, false, true);
			getFrom().addField("login", getLoginField());
			getFrom().addField("password", getPasswordField());
			getFrom().addField("firstName", getFirstNameField());
			getFrom().addField("lastName", getLastNameField());
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
			footer.addComponent(getRegisterButton());
			footer.addComponent(getCancelButton());					
			footer.setSizeUndefined();			
			footer.setComponentAlignment(getRegisterButton(),Alignment.MIDDLE_RIGHT);
			footer.setComponentAlignment(getCancelButton(),Alignment.MIDDLE_RIGHT);
		}
		return footer;
	}



	@SuppressWarnings("serial")
	private void buttonRegisterClick() {
		registerButton.addListener(new ClickListener() {
		
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
	
	public Button getRegisterButton() {
		if (registerButton == null) {
			registerButton = new Button("Register");
			registerButton.setWidth(80, UNITS_PIXELS);
		}
		return registerButton;
	}
	
	public Button getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new Button("Cancel");
			cancelButton.setWidth(80, UNITS_PIXELS);
		}
		return cancelButton;
	}	

	
	public TextField getLoginField() {
		if (loginField == null) {
			loginField = new TextField();
			loginField.setCaption("Login");
			loginField.setRequired(true);
			loginField.setRequiredError("Empty field login");
			loginField.setWidth(100, UNITS_PERCENTAGE);	
			loginField.addValidator(new StringLengthValidator("The login must be 6-15 letters",6, 15, true));
		}
		return loginField;
	}

	public PasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new PasswordField();
			passwordField.setCaption("Password");
			passwordField.setRequired(true);
			passwordField.setRequiredError("Empty field password");			
			passwordField.setWidth(100, UNITS_PERCENTAGE);
			passwordField.addValidator(new StringLengthValidator("The password must be 6-15 letters",6, 15, true));
		}
		return passwordField;
	}

	public TextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new TextField();
			firstNameField.setCaption("First Name");
			firstNameField.setRequired(true);
			firstNameField.setRequiredError("Empty field first name");
			firstNameField.setWidth(100, UNITS_PERCENTAGE);
		}
		return firstNameField;
	}


	public TextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new TextField();
			lastNameField.setCaption("Last name");
			lastNameField.setRequired(true);
			lastNameField.setRequiredError("Empty field last name");
			lastNameField.setWidth(100, UNITS_PERCENTAGE);
		}
		return lastNameField;
	}

	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
}