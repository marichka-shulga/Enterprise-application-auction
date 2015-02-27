package auction.businesslogic.modelBL;

import org.apache.logging.log4j.Logger;

import auction.dao.UserDAO;
import auction.log.LogFactory;
import auction.model.User;

public class UserLogic {
	private UserDAO userDAO;
	private static final Logger LOGGRER = LogFactory.getLogger(UserLogic.class);
	
	public UserLogic(){
		userDAO = new UserDAO();
	}
	
	public User authorization(String login, String password){
		User user = null;
		String message = "";
		try {
			user = userDAO.getUser(login, password);
			message = "Authorization user successful: logginUser={}";
		} catch (Exception e) {
			message = "Authorization user not successful: logginUser={}";	
			LOGGRER.info(message, login);
		}
			
		return user;
	}

	public boolean registration(User user) {
		boolean res = false;
		if( !(userDAO.isUserLogginExist(user.getLogin())) ){
			try {
				userDAO.save(user);
				res = true;
			} catch (Exception e) {
				LOGGRER.error("Is not satisfied: registration{} reason={}", e, e.getMessage());	
			}
		}
		
		String message = "";
		if( res )
			message = "Registration user successful: logginUser={}";
		else
			message = "Registration user not successful: logginUser={}";	

		LOGGRER.info(message, user.getLogin());		
		
		return res;
	}
	
	
	

}
