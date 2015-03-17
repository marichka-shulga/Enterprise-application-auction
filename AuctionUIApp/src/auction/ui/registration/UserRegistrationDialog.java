package auction.ui.registration;

import org.apache.logging.log4j.Logger;

import auction.ui.ClientAuctionSinglton;
import auction.ui.authentication.UserIdentifiedListener;
import auction.ui.log.LogFactory;
import auction.ui.supportingCapabilities.MD5;
import client.artefacts.BaseResponse;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.realization.ClientAuction;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UserRegistrationDialog extends Window {

	private static final long serialVersionUID = 1L;
	private Form from;
	
	private Button registerButton;
	private Button cancelButton;	
	
	private HorizontalLayout footer;
	private VerticalLayout body;	
	
	private static final int COMMON_BUTTON_WIDTH = 80;
	private static final int WINDOW_WIDTH = 350;

	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();
	
	private static final Logger LOGGRER = LogFactory.getLogger(UserRegistrationDialog.class);
	
	private static UserFieldFactory userFieldFactory = UserFieldFactorySinglton.getUserFieldFactory();
	
	private static final String[] FIELDS_NAME = new String[] {"userLogin", "password", "firstName", "lastName"}; 
	
	private UserIdentifiedListener listener;
	
	User user;

	public UserRegistrationDialog(){
		this.user = new User();
	}	
	
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

		addRegisterButtonListener();
		addCancelButtonListener();
	}
	
	private UserRegistrationDialog getThisWindow(){
		return this;
	}

	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == body){
			body = new VerticalLayout();
			body.setMargin(false, true, false, true);
			
		    BeanItem<User> userItem = new BeanItem<User>(user); 
		    getFrom().setFormFieldFactory(userFieldFactory);
		    getFrom().setItemDataSource(userItem);
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
			footer.addComponent(getRegisterButton());
			footer.addComponent(getCancelButton());					
			footer.setSizeUndefined();			
			footer.setComponentAlignment(getRegisterButton(),Alignment.MIDDLE_RIGHT);
			footer.setComponentAlignment(getCancelButton(),Alignment.MIDDLE_RIGHT);
		}
		return footer;
	}

	@SuppressWarnings("serial")
	private void addRegisterButtonListener() {
		registerButton.addListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				getFrom().commit();
				user.setPassword(MD5.encryptPassword(user.getPassword()));
				BaseResponse response = client.userRegistration(user);
				if( response.getStateResult().equals(StateResult.SUCCESS) ){
					user.setIdUser(response.getIdEntity());
					if( null != listener ){
						listener.heIdentified(user);
					}		
				} else if( response.getStateResult().equals(StateResult.NOT_SUCCESS) ){
					getApplication().getMainWindow().showNotification("This login is already used",
											Notification.TYPE_WARNING_MESSAGE);
					clearUserField();
				} else{
					getApplication().getMainWindow().showNotification(response.getErrorMessage(),
							Notification.TYPE_ERROR_MESSAGE);
					clearUserField();
				}
				getParent().removeWindow(getThisWindow());
			} catch (InvalidValueException e) {
				 LOGGRER.info("An incorrect input data addRegisterButtonListener={}, reason={}", e, e.getMessage());
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
				if( null != getParent() )
					getParent().removeWindow(getThisWindow());
			} catch (InvalidValueException e) {
				LOGGRER.error("Is not satisfied addCancelButtonListener={}, reason={}", e, e.getMessage());
			}
		}
	});
}
	
	public Button getRegisterButton() {
		if (registerButton == null) {
			registerButton = new Button("Register");
			registerButton.setWidth(COMMON_BUTTON_WIDTH, UNITS_PIXELS);
		}
		return registerButton;
	}
	
	public Button getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new Button("Cancel");
			cancelButton.setWidth(COMMON_BUTTON_WIDTH, UNITS_PIXELS);
		}
		return cancelButton;
	}	

	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}
	
	private void clearUserField(){
		user.setUserLogin("");
		user.setPassword("");
		user.setFirstName("");
		user.setLastName("");
	}
	
	public void setUserIdentifiedListener(UserIdentifiedListener listener){
		this.listener = listener;
	}
	
}