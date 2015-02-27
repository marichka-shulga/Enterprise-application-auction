package auction.dao;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;

import auction.businesslogic.modelBL.UserLogic;
import auction.log.LogFactory;
import auction.model.User;

public class UserDAO extends GenericDAO<User> {
	
	private static final Logger LOGGRER = LogFactory.getLogger(UserLogic.class);
	
	@Override
	public Class<User> getPersistentClass() {
		return User.class;
	}
	
	public User getUser(String login, String password) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		User user = null;
		try {
			Query query = entityManager.createNamedQuery("User.getUser");
			query.setParameter("login", login);
			query.setParameter("password", password);
			user = (User)query.getSingleResult();
		} catch(Exception e){
			LOGGRER.error("Is not satisfied: getUser{}, reason={}", e, getPersistentClass(), e.getMessage());	
		    throw e;
		}
		finally {
				entityManager.close();
		}
		return user;
	}
	
	public boolean isUserLogginExist(String login){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean res = true;
		try {
			Query query = entityManager.createNamedQuery("User.getUserByLogin");
			query.setParameter("login", login);
			
			if( query.getResultList().isEmpty() )
				res = false;
		} finally {
			entityManager.close();
		}
		return res;
	}

}
