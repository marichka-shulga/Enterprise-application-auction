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
	public Set<Lot> getLots(boolean getOnlyActiveLots){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Set<Lot> results = null;
		try {
			Query query = null;
			if( true == getOnlyActiveLots ){
				query = entityManager.createNamedQuery("Lot.getActiveLots");
				query.setParameter("state", LotState.ACTIVE);
			}else{
				query = entityManager.createNamedQuery("Lot.getAllLots");
			}

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
