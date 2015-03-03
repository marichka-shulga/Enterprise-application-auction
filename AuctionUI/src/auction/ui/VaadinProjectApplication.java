package auction.ui;

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
	private HorizontalSplitPanel mainSplitPanel;
	private Button logout;
	private LotsForm lotsForm;
	
	@Override
	public void init() {
		Window mainWindow = new Window("Application");
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.addComponent(getHeader());
		mainLayout.addComponent(getMainSplitPanel());
		
		mainLayout.setExpandRatio(getHeader(), 0);
		mainLayout.setExpandRatio(getMainSplitPanel(), 1);
		
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

	public HorizontalSplitPanel getMainSplitPanel() {
		if (mainSplitPanel == null) {
			mainSplitPanel = new HorizontalSplitPanel();
			mainSplitPanel.setSplitPosition(33, UNITS_PERCENTAGE);
			mainSplitPanel.setSizeFull();
			mainSplitPanel.setFirstComponent(getLotsForm());
		}
		return mainSplitPanel;
	}
	
	

}

