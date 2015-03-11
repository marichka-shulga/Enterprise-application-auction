package auction.ui.loaderForm;

import org.vaadin.artur.icepush.ICEPush;

import auction.ui.lotsform.LotsForm;

public final class LoaderForms {
	
	private static LotsForm lotsForm;

	private static ICEPush mainPusher;
	
	
	public static LotsForm getLotsForm() {
		return lotsForm;
	}

	public static void setLotsForm(LotsForm form) {
		lotsForm = form;
	}
	
	public static ICEPush getMainPusher() {
		return mainPusher;
	}

	public static void setMainPusher(ICEPush pusher) {
		mainPusher = pusher;
	}

}
