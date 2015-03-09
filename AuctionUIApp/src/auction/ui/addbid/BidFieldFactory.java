package auction.ui.addbid;


import com.vaadin.data.Item;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class BidFieldFactory extends DefaultFieldFactory {
	
	private static final long serialVersionUID = 1L;
	
	 @Override
     public Field createField(Item item, Object propertyId, Component uiContext) {
         Field field = super.createField(item, propertyId, uiContext);
         
         if ("rate".equals(propertyId)) {
            TextField bidRateField = (TextField) field;
			bidRateField.setCaption("Bid");
			bidRateField.setRequired(true);
			bidRateField.setRequiredError("Please enter a Bid");
			bidRateField.addValidator(new DoubleValidator("Incorrectly format rate"));
			bidRateField.setNullRepresentation("");
         } 
         return field;
     }

}