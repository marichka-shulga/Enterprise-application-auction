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
import com.vaadin.ui.themes.BaseTheme;

public class UserAuthenticationDialog extends Window {

	private static final long serialVersionUID = 1L;
	private Form from;
	private TextField loginField;
	private PasswordField passwordField;
	
	private Button loginButton;
	private Button registerlButton;	
	
	private HorizontalLayout footer;
	private VerticalLayout body;
	
	public void attach() {
		this.setCaption("Authentication");
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

		buttonLoginClick();
		buttonRegisterClick();
	}
	
	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == body){
			body = new VerticalLayout();
			body.setMargin(false, true, false, true);
			getFrom().addField("login", getLoginField());
			getFrom().addField("password", getPasswordField());
			body.addComponent(getFrom());
			body.setWidth(250, UNITS_PIXELS);
		}
		return body;
	}
	
	private HorizontalLayout getHozizontalLayoutWithButtons(){
		if( null == footer ){
			footer = new HorizontalLayout();
			footer.setMargin(false, true, true, true);
			footer.setSpacing(true);			
			footer.addComponent(getLoginButton());
			footer.addComponent(getRegisterButton());					
			footer.setSizeUndefined();			
			footer.setComponentAlignment(getLoginButton(),Alignment.MIDDLE_RIGHT);
			footer.setComponentAlignment(getRegisterButton(),Alignment.BOTTOM_RIGHT);
		}
		return footer;
	}

	private UserAuthenticationDialog getThisWindow(){
		return this;
	}

	@SuppressWarnings("serial")
	private void buttonLoginClick() {
		loginButton.addListener(new ClickListener() {
		
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
	private void buttonRegisterClick() {
		registerlButton.addListener(new ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getApplication().getMainWindow().addWindow(new UserRegistrationDialog());
				getParent().removeWindow(getThisWindow());
			} catch (InvalidValueException e) {
				
			}
		}
	});
}
	
	public Button getLoginButton() {
		if (loginButton == null) {
			loginButton = new Button("Login");
			loginButton.setWidth(80, UNITS_PIXELS);

		}
		return loginButton;
	}
	
	public Button getRegisterButton() {
		if (registerlButton == null) {
			registerlButton = new Button("Register");
			registerlButton.setStyleName(BaseTheme.BUTTON_LINK);
		}
		return registerlButton;
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

	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}

}