package auction.ui.loader;

import org.vaadin.artur.icepush.ICEPush;

import auction.ui.bidsform.BidsForm;
import auction.ui.lotdetails.LotDetailsForm;
import auction.ui.lotsform.LotsForm;

public final class LoaderForms {
	
	private static LotsForm lotsForm;
	
	private static LotDetailsForm lotDetailsForm;
	
	private static BidsForm bidsForm;

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
	
	public static LotDetailsForm getLotDetailsForm() {
		return lotDetailsForm;
	}

	public static void setLotDetailsForm(LotDetailsForm lotDetailsForm) {
		LoaderForms.lotDetailsForm = lotDetailsForm;
	}

	public static BidsForm getBidsForm() {
		return bidsForm;
	}

	public static void setBidsForm(BidsForm bidsForm) {
		LoaderForms.bidsForm = bidsForm;
	}


}
