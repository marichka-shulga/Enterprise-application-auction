package auction.ui.quartz;

import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
//
//import auction.businesslogic.modelBL.LotLogic;
//import auction.log.LogFactory;










import org.vaadin.artur.icepush.ICEPush;

import auction.ui.ClientAuctionSinglton;
import auction.ui.addlot.LotDelegate;
import auction.ui.loaderForm.LoaderForms;
import auction.ui.lotsform.LotsForm;
import client.artefacts.GetFinishTradesResponse;
import client.artefacts.GetLotStateByIdLotResponse;
import client.artefacts.GetWinningBidByIdResponseResponse;
import client.artefacts.LotState;
import client.artefacts.StateResult;
import client.realization.ClientAuction;


public class FinishTradesJob implements Job {
	
	//private static final Logger LOGGRER = LogFactory.getLogger(FinishTradesJob.class);
	private LotsForm lotsForm = LoaderForms.getLotsForm();
	private ICEPush pusher = LoaderForms.getMainPusher();
	
	private static ClientAuction client = ClientAuctionSinglton.getClientAuction();	
	
	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {

		Object someId = null;
		try {
			someId = context.getScheduler().getContext().get(context.getTrigger().getKey().toString());
			Integer idLot = Integer.parseInt(someId.toString());
			
			GetWinningBidByIdResponseResponse winningBidResponse = null;
			GetLotStateByIdLotResponse lotStateByIdLotResponse = client.getLotState(idLot);
			if( lotStateByIdLotResponse.getStateResult().equals(StateResult.SUCCESS)){
				LotDelegate lotDelegate = lotsForm.getLotDelegateByIdLot(idLot);
				if( lotStateByIdLotResponse.getLotState().equals(LotState.SOLD) ){
					winningBidResponse = client.getWinningBid(idLot);
					if( lotStateByIdLotResponse.getStateResult().equals(StateResult.SUCCESS)){
						lotsForm.setWinningBid(winningBidResponse.getWinnindBid(),lotDelegate);
						System.out.println(winningBidResponse.getWinnindBid().getRate());
					}
					
					
				}
				
				

				
				lotDelegate.setState(lotStateByIdLotResponse.getLotState());
				lotsForm.refreshTableValue();
				
				pusher.push();
			
			}
			
			
			
			
		} catch (Exception e) {
			//LOGGRER.error("Is not satisfied job execute={}, idLot={}, reason={}", e, e.getMessage(), (String)someId);	
		}

	}

}