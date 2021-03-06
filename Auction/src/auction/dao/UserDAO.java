package auction.dao;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;

import auction.log.LogFactory;
import auction.model.User;

public class UserDAO extends GenericDAO<User> {
	
	private static final Logger LOGGRER = LogFactory.getLogger(UserDAO.class);
	
	@Override
	public Class<User> getPersistentClass() {
		return User.class;
	}
	
	public User getUser(String login, String password) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		User user = null;
		try {
			Query query = entityManager.createNamedQuery("User.getUser");
			query.setParameter("userLogin", login);
			query.setParameter("password", password);
			user = (User)query.getSingleResult();
		} catch(Exception e){
			LOGGRER.error("Is not satisfied: getUser={}, reason={}", e, e.getMessage());	
		    throw e;
		}
		finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
		return user;
	}
	
	public boolean isUserLoginExist(String login){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean res = true;
		try {
			Query query = entityManager.createNamedQuery("User.getUserByLogin");
			query.setParameter("userLogin", login);
			
			if( query.getResultList().isEmpty() )
				res = false;
		} finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
		return res;
	}

}
