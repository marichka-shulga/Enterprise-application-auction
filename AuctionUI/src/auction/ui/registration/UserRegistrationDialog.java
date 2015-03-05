package auction.ui.registration;

import java.util.Arrays;


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
	
	private static UserFieldFactory userFieldFactory = UserFieldFactorySinglton.getUserFieldFactory();
	
	User user;

	public UserRegistrationDialog(){
		user = new User();
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
			
		    BeanItem<User> userItem = new BeanItem<User>(user); 
		    getFrom().setFormFieldFactory(userFieldFactory);
		    getFrom().setItemDataSource(userItem);
			getFrom().setVisibleItemProperties(Arrays.asList(new String[] {
		                "userLogin", "password", "firstName", "lastName"}));			

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
	
}