package auction.service.response;

public class BaseResponse {
	private StateResult stateResult;
	private String errorMessage;
	private Integer idEntity;
	
	public void setStateResult(StateResult stateResult){
		this.stateResult = stateResult;
	}
	public StateResult getStateResult(){
		return this.stateResult;
	}
		
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public final Integer getIdEntity() {
		return idEntity;
	}
	public final void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

}
