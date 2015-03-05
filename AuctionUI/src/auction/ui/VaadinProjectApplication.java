package auction.ui;

import auction.ui.lotdetails.LotDetailsForm;

import com.vaadin.Application;

import static com.vaadin.terminal.Sizeable.UNITS_PIXELS;
import static com.vaadin.terminal.Sizeable.UNITS_PERCENTAGE;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Main application class.
 */
public class VaadinProjectApplication extends Application {

	private static final String AUCTION = "<H1>Auction</H1>";
	private HorizontalLayout header;
	private Label userName;
	private HorizontalSplitPanel horizontalSplitPanel;
	private VerticalSplitPanel verticalSplitPanel;
	private Button logout;
	private LotsForm lotsForm;
	private LotDetailsForm lotDatailsForm;
	private BidsForm bidsForm;
	
	@Override
	public void init() {
		Window mainWindow = new Window("Application");
		VerticalLayout mainLayout = new VerticalLayout();
		//mainLayout.setMargin(true);
		
		mainLayout.addComponent(getHeader());
		mainLayout.addComponent(getHorisontalSplitPanel());
		
		mainLayout.setExpandRatio(getHeader(), 0);
		mainLayout.setExpandRatio(getHorisontalSplitPanel(), 1);
		
		mainLayout.setSizeFull();
		mainWindow.setContent(mainLayout);		
		setMainWindow(mainWindow);
		mainWindow.setSizeFull();
	
	}

	private HorizontalLayout getHeader() {
		if (header == null) {
			header = new HorizontalLayout();
			Label auctionLable = new Label(AUCTION);
			auctionLable.setContentMode(Label.CONTENT_XHTML);
			
			header.setMargin(false, false, false, true);
			header.addComponent(auctionLable);
			
			HorizontalLayout userHorizontalLayout = new HorizontalLayout();
			userHorizontalLayout.setMargin(true);
			userHorizontalLayout.setSizeUndefined();
			userHorizontalLayout.addComponent(getUserName());
			userHorizontalLayout.setSpacing(true);
			userHorizontalLayout.addComponent(getLogout());	
			header.addComponent(userHorizontalLayout);
			header.setComponentAlignment(auctionLable, Alignment.MIDDLE_LEFT);
			header.setComponentAlignment(userHorizontalLayout, Alignment.TOP_RIGHT);
			header.setHeight(70, UNITS_PIXELS);
			header.setWidth(100, UNITS_PERCENTAGE);
		}
		return header;
	}
	
	public Label getUserName() {
		if (userName == null) {
			userName = new Label("User");
			userName.setSizeUndefined();
			
		}
		return userName;
	}
	
	public LotsForm getLotsForm() {
		if (lotsForm == null) {
			lotsForm = new LotsForm();
			
		}
		return lotsForm;
	}
	
	public LotDetailsForm getLotDetailsForm() {
		if (lotDatailsForm == null) {
			lotDatailsForm = new LotDetailsForm();
		}
		return lotDatailsForm;
	}
	
	public BidsForm getBidsForm() {
		if (bidsForm == null) {
			bidsForm = new BidsForm();
		}
		return bidsForm;
	}	

	public Button getLogout() {
		if (logout == null) {
			logout = new Button("logout");
			logout.setStyleName(BaseTheme.BUTTON_LINK);
			logout.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					getMainWindow().showNotification("Logouted", Notification.TYPE_WARNING_MESSAGE);
				}
			});
		}
		return logout;
	}

	public HorizontalSplitPanel getHorisontalSplitPanel() {
		if (horizontalSplitPanel == null) {
			horizontalSplitPanel = new HorizontalSplitPanel();
			horizontalSplitPanel.setSplitPosition(33, UNITS_PERCENTAGE);
			horizontalSplitPanel.setSizeFull();
			horizontalSplitPanel.setFirstComponent(getLotsForm());
			horizontalSplitPanel.setSecondComponent(getVerticalSplitPanel());
		}
		return horizontalSplitPanel;
	}
	
	public VerticalSplitPanel getVerticalSplitPanel() {
		if (verticalSplitPanel == null) {
			verticalSplitPanel = new VerticalSplitPanel();
			verticalSplitPanel.setSplitPosition(45, UNITS_PERCENTAGE);
			verticalSplitPanel.setSizeFull();
			verticalSplitPanel.setFirstComponent(getLotDetailsForm());
			verticalSplitPanel.setSecondComponent(getBidsForm());
		}
		return verticalSplitPanel;
	}	
	

}

