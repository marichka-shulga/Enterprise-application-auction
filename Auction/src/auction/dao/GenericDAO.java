package auction.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class GenericDAO<T> {
	
	private static final Logger LOGGRER = LogManager.getLogger(GenericDAO.class);

	protected EntityManagerFactory entityManagerFactory = Resource.getEntityManagerFactory();

	public abstract Class<T> getPersistentClass();

	public T getEntityById(Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		T result= null;
		try {
			result = entityManager.find(getPersistentClass(), id);
		} finally {
			entityManager.close();
		}
		return result;
	}
	
	public void save(T entity) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied save={}, persistentClass={}, reason={}", e, getPersistentClass(), e.getMessage());
			if ( (null != entityTransaction) && (entityTransaction.isActive()) ) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
	}

	public void update(T entity) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied update={}, persistentClass={}, reason={}", e, getPersistentClass(), e.getMessage());			
			if ( (null != entityTransaction) && (entityTransaction.isActive()) ) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
	}
	
}
