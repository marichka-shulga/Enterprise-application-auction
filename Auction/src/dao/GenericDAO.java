package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import log.LogFactory;

import org.apache.logging.log4j.Logger;

public abstract class GenericDAO<T> {
	
	private static final Logger LOGGRER = LogFactory.getLogger(GenericDAO.class);

	protected EntityManagerFactory entityManagerFactory = Resource.getEntityManagerFactory();

	public abstract Class<T> getPersistentClass();

	public T getObjectById(Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		T res = null;
		try {
			res = entityManager.find(getPersistentClass(), id);
		} finally {
			entityManager.close();
		}
		return res;
	}
	
//	@SuppressWarnings("unchecked")
//	public Set<T> getAll() {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		Set<T> results = null;
//		try {
//			String qr = "SELECT x FROM " + getPersistentClass().getSimpleName()	+ " x ";
//			Query query = entityManager.createQuery(qr);
//
//			List<T> resultsList = query.getResultList();
//			if ( null != resultsList ) {
//				results = new HashSet<T>(resultsList);
//			}
//		} finally {
//			entityManager.close();
//		}
//		return results;
//	}

	public void save(T object) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(object);
			entityTransaction.commit();
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied: save {}, reason={}", e, getPersistentClass(), e.getMessage());
			if ( null != entityTransaction && entityTransaction.isActive() ) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if ( null != entityManager && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	public void update(T object) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(object);
			entityTransaction.commit();
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied: update {}, reason={}", e, getPersistentClass(), e.getMessage());			
			if ( null != entityTransaction && entityTransaction.isActive() ) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if ( null != entityManager && entityManager.isOpen() ) {
				entityManager.close();
			}
		}
	}
}
