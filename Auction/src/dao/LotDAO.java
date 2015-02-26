package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Lot;
import model.LotState;

public class LotDAO extends GenericDAO<Lot>{

	@Override
	public Class<Lot> getPersistentClass() {
		return Lot.class;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Lot> getAllActiveLots(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Set<Lot> results = null;
		try {
			Query query = entityManager.createNamedQuery("Lot.getActiveLots");
			query.setParameter("state", LotState.ACTIVE);

			List<Lot> resultsList = query.getResultList();
			if ( null != resultsList ) {
				results = new HashSet<Lot>(resultsList);
			}
		} finally {
			entityManager.close();
		}
		return results;
	}		
	

}
