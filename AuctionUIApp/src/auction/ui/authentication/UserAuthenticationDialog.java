package auction.ui.authentication;

import org.apache.logging.log4j.Logger;

import auction.ui.ClientAuctionSinglton;
import auction.ui.log.LogFactory;
import auction.ui.registration.MD5;
import auction.ui.registration.UserFieldFactory;
import auction.ui.registration.UserFieldFactorySinglton;
import auction.ui.registration.UserRegistrationDialog;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.artefacts.UserAuthenticResponse;
import client.realization.ClientAuction;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.BaseTheme;

public class UserAuthenticationDialog extends Panel {

	private static final long serialVersionUID = 1L;
	private Form from;
	
	private Button loginButton;
	private Button registerlButton;	
	
	private HorizontalLayout footer;
	private VerticalLayout body;
	
	private static final UserFieldFactory userFieldFactory = UserFieldFactorySinglton.getUserFieldFactory();
	
	private static final Logger LOGGRER = LogFactory.getLogger(UserAuthenticationDialog.class);
	
	private static final int COMMON_BUTTON_WIDTH = 80;
	private static final int WINDOW_WIDTH = 250;
	private static final int WINDOW_WIDTH_PLUS_BORDERS = 253;	
	private static final String[] FIELDS_NAME = new String[] {"userLogin", "password"}; 
	
	private UserIdentifiedListener listener;
	
	private User user;
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();
	
	public UserAuthenticationDialog(){
	}
	
	public void initDialog(){
		initFormFields();
	}
	public void attach() {
		this.setCaption("Authentication");

		this.setWidth(WINDOW_WIDTH_PLUS_BORDERS, UNITS_PIXELS);
		VerticalLayout mainVerticalLayout = new VerticalLayout();
		setContent(mainVerticalLayout);		
		mainVerticalLayout.setSizeFull();

		mainVerticalLayout.addComponent(getVerticalLayoutWithFields());
		mainVerticalLayout.addComponent(getHozizontalLayoutWithButtons());
		mainVerticalLayout.setComponentAlignment(getHozizontalLayoutWithButtons(),Alignment.MIDDLE_RIGHT);
		mainVerticalLayout.setExpandRatio(getVerticalLayoutWithFields(),1);		
		mainVerticalLayout.setExpandRatio(getHozizontalLayoutWithButtons(), 0);

		mainVerticalLayout.setSizeUndefined();
		
		addLoginButtonListener();
		addRegisterButtonListener();

	}
	
	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == body){
			body = new VerticalLayout();
			body.setMargin(false, true, false, true);
			initFormFields();
			body.addComponent(getFrom());
			body.setWidth(WINDOW_WIDTH, UNITS_PIXELS);
		}
		return body;
	}
	
	private void initFormFields(){
		if ( null == user)
			user = new User();
	    BeanItem<User> userItem = new BeanItem<User>(user); 
	    getFrom().setFormFieldFactory(userFieldFactory);
	    getFrom().setItemDataSource(userItem);
		getFrom().setVisibleItemProperties(FIELDS_NAME);			
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

	@SuppressWarnings("serial")
	private void addLoginButtonListener() {
		getLoginButton().addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
				UserAuthenticResponse responce = client.userAuthentication(user.getUserLogin(), MD5.encryptPassword(user.getPassword()));
				if( responce.getStateResult().equals(StateResult.SUCCESS) ){
					user = responce.getUser();
					if( null != listener ){
						listener.heIdentified(user);
					}
				} else if( responce.getStateResult().equals(StateResult.NOT_SUCCESS) ){
					getApplication().getMainWindow().showNotification("The login or password you entered is incorrect",
											Notification.TYPE_WARNING_MESSAGE);
					clearUserField();
				} else{
					getApplication().getMainWindow().showNotification(responce.getErrorMessage(),
							Notification.TYPE_ERROR_MESSAGE);
					clearUserField();
				}
				
			} catch (InvalidValueException e) {
				 LOGGRER.info("An incorrect input data addLoginButtonListener={}, reason={}", e, e.getMessage());
			}
			
			
		}
		});
	}

	@SuppressWarnings("serial")
	private void addRegisterButtonListener() {
		getRegisterButton().addListener(new ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				clearUserField();
				UserRegistrationDialog userRegistrationDialog = new UserRegistrationDialog();
				getApplication().getMainWindow().addWindow(userRegistrationDialog);
				userRegistrationDialog.setUserIdentifiedListener(listener);
			} catch (InvalidValueException e) {
				 LOGGRER.error("Is not satisfied addRegisterButtonListener={}, reason={}", e, e.getMessage());
			}
		}
		});
	}		
	
	public Button getLoginButton() {
		if (loginButton == null) {
			loginButton = new Button("Login");
			loginButton.setWidth(COMMON_BUTTON_WIDTH, UNITS_PIXELS);

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

	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	private void clearUserField(){
		user = null;
		initFormFields();
	}
	
	public void setUserIdentifiedListener(UserIdentifiedListener listener){
		this.listener = listener;
	}
}