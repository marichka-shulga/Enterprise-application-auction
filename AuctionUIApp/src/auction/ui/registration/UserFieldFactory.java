package auction.ui.registration;

import static com.vaadin.terminal.Sizeable.UNITS_PERCENTAGE;

import com.vaadin.data.Item;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class UserFieldFactory extends DefaultFieldFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static final int COMMON_FIELD_WIDTH = 100;
	
	
	 @Override
     public Field createField(Item item, Object propertyId, Component uiContext) {
         Field field;
         
         if ( "password".equals(propertyId) ) {
        	 field = getPasswordField(propertyId);
         } else {
        	 field = super.createField(item, propertyId, uiContext);
         }
         
         field.setWidth(COMMON_FIELD_WIDTH, UNITS_PERCENTAGE);	
         
         if ("userLogin".equals(propertyId)) {
            TextField loginField = (TextField) field;
            loginField.setCaption("Login");
			loginField.setRequired(true);
			loginField.setRequiredError("Please enter a Login");
			loginField.setNullRepresentation("");
			loginField.addValidator(new StringLengthValidator("The login must be 6-15 characters",6, 15, false));
         } else if ("password".equals(propertyId)) {
        	PasswordField passwordField = (PasswordField) field;
			passwordField.setRequired(true);
 			passwordField.setRequiredError("Please enter a Password");			
 			passwordField.addValidator(new StringLengthValidator("The password must be 6-15 characters",6, 15, true));
 			passwordField.setNullRepresentation("");
         } else if ("firstName".equals(propertyId)) {
        	TextField firstNameField = (TextField) field;
        	firstNameField.setRequired(true);
			firstNameField.setRequiredError("Please enter a First name");	
			firstNameField.setNullRepresentation("");
         } else if ("lastName".equals(propertyId)) {
            TextField lastNameField = (TextField) field;
			lastNameField.setRequired(true);
			lastNameField.setRequiredError("Please enter a Last name");
			lastNameField.setNullRepresentation("");
         } 
         return field;
     }

	 
	public PasswordField getPasswordField(Object propertyId) {
		PasswordField passwordField = new PasswordField();
		passwordField.setCaption(DefaultFieldFactory.createCaptionByPropertyId(propertyId));
		return passwordField;
	}	
}
