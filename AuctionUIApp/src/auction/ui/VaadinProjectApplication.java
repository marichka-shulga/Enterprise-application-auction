package auction.ui;

import auction.ui.authentication.UserAuthenticationDialog;
import auction.ui.authentication.UserIdentifiedListener;
import auction.ui.bidsform.BidsForm;
import auction.ui.lotdetails.LotDetailsForm;
import auction.ui.lotsform.LotsForm;
import client.artefacts.User;

import com.vaadin.Application;

import static com.vaadin.terminal.Sizeable.UNITS_PIXELS;
import static com.vaadin.terminal.Sizeable.UNITS_PERCENTAGE;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Main application class.
 */
public class VaadinProjectApplication extends Application {

	private static final long serialVersionUID = 1L;
	
	private static final String AUCTION = "<H1>Auction</H1>";
	private HorizontalLayout header;
	private Label userName;
	private HorizontalSplitPanel horizontalSplitPanel;
	private VerticalSplitPanel verticalSplitPanel;
	private Button logout;
	private LotsForm lotsForm;
	private LotDetailsForm lotDatailsForm;
	private BidsForm bidsForm;
	
	private VerticalLayout mainLayout;
	private VerticalLayout userDialogLayout;	
	
	private UserAuthenticationDialog userdialog;
	
	private User user;
	private Window mainWindow; 
	
	@Override
	public void init() {
		openUserAuhtenticationPanel();
	}
	
	private void openUserAuhtenticationPanel(){
		getUserAuthenticationDialog().initDialog();
		//VerticalLayout userDialogLayout = new VerticalLayout();
		getMainWindow().setContent(getUserDialogLayout());
		//userdialog = new UserAuthenticationDialog();
		getUserDialogLayout().addComponent(getUserAuthenticationDialog());
		getUserDialogLayout().setComponentAlignment(getUserAuthenticationDialog(),Alignment.MIDDLE_CENTER);
		getUserDialogLayout().setSizeFull();
		
		getUserAuthenticationDialog().setUserIdentifiedListener(new UserIdentifiedListener(){
			@Override
			public void heIdentified(User user) {
				setUser(user);
				getLotsForm().setUser(user);
				getMainLayout().addComponent(getHeader());
				getMainLayout().addComponent(getHorisontalSplitPanel());	
				getMainLayout().setExpandRatio(getHeader(), 0);
				getMainLayout().setExpandRatio(getHorisontalSplitPanel(), 1);
				getMainLayout().setSizeFull();
				getUserName().setCaption("User: " + getUser().getUserLogin());
				getMainWindow().setContent(getMainLayout());	

			}
		});
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
	
	public VerticalLayout getMainLayout(){
		if( null == mainLayout){
			mainLayout = new VerticalLayout();
		}
		return mainLayout;
	}	

	public VerticalLayout getUserDialogLayout(){
		if( null == userDialogLayout){
			userDialogLayout = new VerticalLayout();
		}
		return userDialogLayout;
	}	
	
	public UserAuthenticationDialog getUserAuthenticationDialog(){
		if( null == userdialog){
			userdialog = new UserAuthenticationDialog();
		}
		return userdialog;
	}	

	public Window getMainWindow(){
		if( null == mainWindow){
			mainWindow = new Window("Application");
			setMainWindow(mainWindow);
			mainWindow.setSizeFull();
		}
		return mainWindow;
	}	
	
	
	
	public Label getUserName() {
		if (userName == null) {
			userName = new Label();
			userName.setSizeUndefined();
		}
		return userName;
	}
	
	public LotsForm getLotsForm() {
		if (lotsForm == null) {
			lotsForm = new LotsForm(getUser());
		}
		return lotsForm;
	}
	
	public LotDetailsForm getLotDetailsForm() {
		if (lotDatailsForm == null) {
			lotDatailsForm = new LotDetailsForm(getLotsForm());
		}
		return lotDatailsForm;
	}
	
	public BidsForm getBidsForm() {
		if (bidsForm == null) {
			bidsForm = new BidsForm(getLotsForm());
		}
		return bidsForm;
	}	

	public Button getLogout() {
		if (logout == null) {
			logout = new Button("logout");
			logout.setStyleName(BaseTheme.BUTTON_LINK);
			logout.addListener(new ClickListener() {
			
				private static final long serialVersionUID = 1L;
				@Override
				public void buttonClick(ClickEvent event) {
					openUserAuhtenticationPanel();
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
			verticalSplitPanel.setSplitPosition(50, UNITS_PERCENTAGE);
			verticalSplitPanel.setSizeFull();
			verticalSplitPanel.setFirstComponent(getLotDetailsForm());
			verticalSplitPanel.setSecondComponent(getBidsForm());
		}
		return verticalSplitPanel;
	}	
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}	

}

