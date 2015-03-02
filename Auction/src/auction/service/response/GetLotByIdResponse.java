package auction.service.response;

import auction.model.Lot;

public class GetLotByIdResponse extends BaseResponse {
	
	private Lot lot;
	
	public void setLot(Lot lot){
		this.lot = lot;
	}
	public Lot getLot(){
		return this.lot;
	}
}
