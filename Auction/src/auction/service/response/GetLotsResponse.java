package auction.service.response;

import java.util.Set;

import auction.model.Lot;

public class GetLotsResponse extends BaseResponse {
	
	private Set<Lot> listLots;
	
	public void setListLots(Set<Lot> listLots){
		this.listLots = listLots;
	}
	public Set<Lot> getListLots(){
		return this.listLots;
	}

}
