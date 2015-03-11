package auction.service.response;

import auction.model.LotState;

public class GetLotStateByIdLotResponse extends BaseResponse {
	LotState lotState;
	
	public LotState getLotState() {
		return lotState;
	}
	public void setLotState(LotState lotState) {
		this.lotState = lotState;
	}
}
