package auction.ui.lotdetails;

import java.util.Arrays;

import auction.ui.addlot.Lot;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LotDetailsForm extends Form {

	private static final long serialVersionUID = 1L;
	private VerticalLayout fieldLayout;
	private HorizontalLayout butonLayout;
	
	private Form from; 
	
	private Button cancelTradesButton;
	@Override
	public void attach() {
		super.attach();
		HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
		setCaption("Lot details");
		//getLayout().setMargin(true);
		addStyleName("bordered"); 

		setLayout(mainHorizontalLayout);
		mainHorizontalLayout.setSizeFull();
		mainHorizontalLayout.setMargin(true);
		mainHorizontalLayout.addComponent(getVerticalLayoutWithFields());
		mainHorizontalLayout.addComponent(getHozizontalLayoutWithButton());
		
		mainHorizontalLayout.setExpandRatio(getVerticalLayoutWithFields(), 1);
		mainHorizontalLayout.setExpandRatio(getHozizontalLayoutWithButton(), 0);
		mainHorizontalLayout.setComponentAlignment(getHozizontalLayoutWithButton(), Alignment.BOTTOM_RIGHT);
		setSizeFull();
//		addLot(new Lot("code", "name", "finishDate", "state"));
		buttonCancelTradesClick();
		
	}	
	
	private VerticalLayout getVerticalLayoutWithFields(){
		if( null == fieldLayout){
			fieldLayout = new VerticalLayout();
			fieldLayout.setMargin(false, true, false, true);
			Lot lot = new Lot();
			
			
			
			
		    BeanItem<Lot> lotItem = new BeanItem<Lot>(lot); 
            
		    getFrom().setFormFieldFactory(new LotDetailsFactory());
		    getFrom().setItemDataSource(lotItem); // bind to POJO via BeanItem

		        // Determines which properties are shown, and in which order:
			getFrom().setVisibleItemProperties(Arrays.asList(new String[] {
		                "code", "name", "finashDate","descriptions"}));			
			
//			getFrom().addField("login", getLoginField());
//			getFrom().addField("password", getPasswordField());
//			body.addComponent(getFrom());
//			body.setWidth(250, UNITS_PIXELS);
		}
		return fieldLayout;
	}
	
	
	private HorizontalLayout getHozizontalLayoutWithButton(){
		if( null == butonLayout ){
			butonLayout = new HorizontalLayout();
			butonLayout.setMargin(true, false, false, false);
			butonLayout.addComponent(getCancelTradesButton());
			butonLayout.setSizeUndefined();			
			//footer.setComponentAlignment(getNewLotButton(),Alignment.MIDDLE_RIGHT);
		}
		return butonLayout;
	}
		
	@SuppressWarnings("serial")
	public void buttonCancelTradesClick(){
		cancelTradesButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {

				} catch (InvalidValueException e) {
				
				}
			}
		});
	}
	
	
	public Button getCancelTradesButton(){
		if( null == cancelTradesButton ){
			cancelTradesButton = new Button("Cancel trades");
			cancelTradesButton.setWidth(105, UNITS_PIXELS);
		}
		return cancelTradesButton;
	}
	
	public Form getFrom() {
		if (from == null) {
			from = new Form();
		}
		return from;
	}

}
