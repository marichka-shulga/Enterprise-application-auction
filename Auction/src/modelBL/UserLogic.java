package modelBL;

import org.apache.logging.log4j.Logger;

import dao.UserDAO;
import log.LogFactory;
import model.User;

public class UserLogic {
	private UserDAO userDAO;
	private static final Logger LOGGRER = LogFactory.getLogger(UserLogic.class);
	
	public UserLogic(){
		userDAO = new UserDAO();
	}
	
	public User authorization(String loggin, String password){
		User user = null;
		String message = "";
		try {
			user = userDAO.getUser(loggin, password);
			message = "Authorization user successful: logginUser={}";
		} catch (Exception e) {
			message = "Authorization user not successful: logginUser={}";	
			LOGGRER.info(message, loggin);
		}
			
		return user;
	}

	public boolean registration(User user) {
		boolean res = false;
		if( !(userDAO.isUserLogginExist(user.getLoggin())) ){
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

		LOGGRER.info(message, user.getLoggin());		
		
		return res;
	}
	
	
	

}
