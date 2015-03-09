package auction.ui.lotdetails;

import static com.vaadin.terminal.Sizeable.UNITS_PERCENTAGE;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class LotDetailsFactory extends DefaultFieldFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static final int COMMON_FIELD_WIDTH = 200;

     public Field createField(Item item, Object propertyId, Component uiContext) {
         Field field = null;
         if ( "finishDate".equals(propertyId) ) {
        	 field = getFinishDateField(propertyId);
         }else {
        	 field = super.createField(item, propertyId, uiContext);
         }
         
        field.setWidth(COMMON_FIELD_WIDTH, UNITS_PERCENTAGE);	
        
        if ( "finishDate".equals(propertyId) ) {
        	PopupDateField dateField = (PopupDateField) field;
        	dateField.setDateFormat("dd.MM.yyyy hh:mm:ss");
         	dateField.setReadOnly(true);
        }else {
         	TextField lotField = (TextField) field;
         	lotField.setReadOnly(true);
         	lotField.setNullRepresentation("");
         } 
         return field;
     }
     
 	private PopupDateField getFinishDateField(Object propertyId) {
		PopupDateField finishDate = new PopupDateField();
		finishDate.setCaption(DefaultFieldFactory.createCaptionByPropertyId(propertyId));
		return finishDate;
	}	 

}
