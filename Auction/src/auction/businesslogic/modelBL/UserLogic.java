package auction.businesslogic.modelBL;

import javax.persistence.NoResultException;
import org.apache.logging.log4j.Logger;

import auction.dao.UserDAO;
import auction.log.LogFactory;
import auction.model.User;
import auction.service.response.BaseResponse;
import auction.service.response.StateResult;
import auction.service.response.UserAuthenticResponse;

public class UserLogic {
	private UserDAO userDAO;
	private static final Logger LOGGRER = LogFactory.getLogger(UserLogic.class);
	
	public UserLogic(){
		userDAO = new UserDAO();
	}
	
	public UserAuthenticResponse authentication(String login, String password){
		UserAuthenticResponse res = new UserAuthenticResponse();
		String message = null;
		try {
			res.setUser(userDAO.getUser(login, password));
			message = "Authorization user successful loginUser={}";
			LOGGRER.info(message, login);
			res.setStateResult(StateResult.SUCCESS);
		} catch (NoResultException e1) {
			message = "Authorization user not successful, not exist user with userLogin={}";	
			LOGGRER.info(message, login);
			res.setStateResult(StateResult.NOT_SUCCESS);
		} catch (Exception e) {
			message = "Authorization user not successful={}, reason={}, loginUser={}";	
			LOGGRER.error(message, e, e.getMessage(), login);
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());
		}

		return res;
	}

	public BaseResponse registration(User user) {
		BaseResponse res = new BaseResponse();
		res.setStateResult(StateResult.NOT_SUCCESS);
		try {
			if( !(userDAO.isUserLogginExist(user.getUserLogin())) ){
				userDAO.save(user);
				res.setStateResult(StateResult.SUCCESS);
				res.setIdEntity(user.getIdUser());
			}
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied registration={} reason={}", e, e.getMessage());	
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());
		}
		String message = "";
		if( res.getStateResult().equals(StateResult.SUCCESS) )
			message = "Registration user successful loginUser={}";
		else
			message = "Registration user not successful loginUser={}";	

		LOGGRER.info(message, user.getUserLogin());		
		
		return res;
	}
	
	
	

}
