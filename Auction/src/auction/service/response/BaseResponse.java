package auction.service.response;

public class BaseResponse {
	private StateResult stateResult;
	private String errorMessage;
	
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

}
