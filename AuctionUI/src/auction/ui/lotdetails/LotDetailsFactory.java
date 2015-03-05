package auction.ui.lotdetails;

import static com.vaadin.terminal.Sizeable.UNITS_PERCENTAGE;

import com.vaadin.data.Item;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class LotDetailsFactory extends DefaultFieldFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static final int COMMON_FIELD_WIDTH = 100;
	
	

     public Field createField(Item item, Object propertyId, Component uiContext) {
		 
    	Field field = super.createField(item, propertyId, uiContext);
         
        field.setWidth(COMMON_FIELD_WIDTH, UNITS_PERCENTAGE);	
        
        if ("code".equals(propertyId)) {
        	TextField nameField = (TextField) field;
 			nameField.setEnabled(false);
         } else if ("name".equals(propertyId)) {
         	TextField nameField = (TextField) field;
  			nameField.setEnabled(false);
         } else if ("finashDate".equals(propertyId)) {
         	TextField nameField = (TextField) field;
  			nameField.setEnabled(false);       	 
         } else if ("descriptions".equals(propertyId)) {
         	TextField nameField = (TextField) field;
  			nameField.setEnabled(false);
         }     

         return field;
     }
	 
	 
//		private Label getLabelField(Object propertyId) {
//			Label label = new Label();
//			label.setCaption(DefaultFieldFactory.createCaptionByPropertyId(propertyId));
//			return label;
//		}		 

	 
}
