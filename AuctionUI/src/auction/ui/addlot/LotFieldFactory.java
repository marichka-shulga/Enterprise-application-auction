package auction.ui.addlot;

import com.vaadin.data.Item;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

import static com.vaadin.terminal.Sizeable.UNITS_PERCENTAGE;

public class LotFieldFactory extends DefaultFieldFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static final int COMMON_FIELD_WIDTH = 100;
	
	
	 @Override
     public Field createField(Item item, Object propertyId, Component uiContext) {
         Field field;
         if ( "finashDate".equals(propertyId) ) {
        	 field = getFinishDateField(propertyId);
         } else {
        	 field = super.createField(item, propertyId, uiContext);
         }
         
         field.setWidth(COMMON_FIELD_WIDTH, UNITS_PERCENTAGE);	
         
         if ("name".equals(propertyId)) {
            TextField nameField = (TextField) field;
 			nameField.setRequired(true);
 			nameField.setRequiredError("Please enter a Lot name");
 			nameField.setNullRepresentation("");
//             tf.addValidator(new StringLengthValidator(
//                     "First Name must be 3-25 characters", 3, 25, false));
         } else if ("finashDate".equals(propertyId)) {
        	PopupDateField finishDateField = (PopupDateField) field;
        	finishDateField.setDateFormat("dd.MM.yyyy hh:mm:ss");
        	finishDateField.setRequired(true);
        	finishDateField.setRequiredError("Please enter a Finish date");
         } else if ("startPrice".equals(propertyId)) {
        	TextField startPriceField = (TextField) field;
        	startPriceField.setNullRepresentation("");
        	startPriceField.setRequired(true);
        	startPriceField.setRequiredError("Please enter a Start price");
        	startPriceField.addValidator(new DoubleValidator("Incorrectly format starting price"));		       	 
         } else if ("descriptions".equals(propertyId)) {
            TextField descriptionField = (TextField) field;
            descriptionField.setNullRepresentation("");
            descriptionField.setNullSettingAllowed(true);
 			descriptionField.setHeight(5, UNITS_PERCENTAGE);
         } 
         return field;
     }

	 
	private PopupDateField getFinishDateField(Object propertyId) {
		PopupDateField finishDate = new PopupDateField();
		finishDate.setCaption(DefaultFieldFactory.createCaptionByPropertyId(propertyId));
		return finishDate;
	}	 
	
}
