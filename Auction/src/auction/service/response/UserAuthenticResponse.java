package auction.service.response;

import auction.model.User;

public class UserAuthenticResponse extends BaseResponse {
	
	private User user;
	
	public void setUser(User user){
		this.user = user;
	}
	public User getuser(){
		return this.user;
	}	
}
