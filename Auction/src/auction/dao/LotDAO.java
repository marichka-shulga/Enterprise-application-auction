package auction.dao;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;

import auction.log.LogFactory;
import auction.model.Lot;
import auction.model.LotState;
import auction.service.response.GetLotsResponse;
import auction.service.response.StateResult;

public class LotDAO extends GenericDAO<Lot>{
	
	private static final Logger LOGGRER = LogFactory.getLogger(LotDAO.class);
	
	@Override
	public Class<Lot> getPersistentClass() {
		return Lot.class;
	}
	
	@SuppressWarnings("unchecked")
	public GetLotsResponse getLots(boolean getOnlyActiveLots){
		GetLotsResponse res = new GetLotsResponse();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
				res.setListLots(new HashSet<Lot>(resultsList));
				res.setStateResult(StateResult.SUCCESS);
			}
		} catch (Exception e) {
			LOGGRER.info("Is not satisfied: getLots{} reason={}", e, e.getMessage());
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());			
		} finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
		return res;
	}	
	
}
