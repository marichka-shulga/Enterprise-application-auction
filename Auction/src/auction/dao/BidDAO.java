package auction.dao;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;

import auction.log.LogFactory;
import auction.model.Bid;
import auction.service.response.GetBidsByIdLotResponse;
import auction.service.response.StateResult;


public class BidDAO extends GenericDAO<Bid>  {
	
	private static final Logger LOGGRER = LogFactory.getLogger(BidDAO.class);
	
	@Override
	public Class<Bid> getPersistentClass() {
		return Bid.class;
	}
	
	public BigDecimal getMaxRate(Integer idLot){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		BigDecimal maxRate = new BigDecimal(0);
		BigDecimal tmpRate;
		try {
			Query query = entityManager.createNamedQuery("Bid.getMaxRate");
			query.setParameter("idLot", idLot);
			tmpRate = (BigDecimal) query.getSingleResult();
			if( null != tmpRate)
				maxRate = tmpRate;
		} catch(Exception e){
			//to do nothing
		}
		finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
		
		return maxRate;	
	}
	
	public Bid getWinningBid(Integer idLot){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Bid bid = null;
		BigDecimal maxRate = getMaxRate(idLot);
		if( 0 != maxRate.compareTo(new BigDecimal(0)) ){
			try {
				Query query = entityManager.createNamedQuery("Bid.getWinningBid");
				query.setParameter("idLot", idLot);
				query.setParameter("rate", maxRate);
				bid = (Bid) query.getSingleResult();
			} catch ( Exception e ) {
				LOGGRER.error("Something wrong: getWinningBid idLot={}, reason={}", 
						idLot, e.getMessage());				

			} finally {
				if ( (null != entityManager) && (entityManager.isOpen()) ) {
					entityManager.close();
				}
			}
		}
		
		return bid;	
	}
	

	@SuppressWarnings("unchecked")
	public GetBidsByIdLotResponse getBids(Integer idLot){
		GetBidsByIdLotResponse response = new GetBidsByIdLotResponse();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			Query query = entityManager.createNamedQuery("Bid.getBidsForLot");
			query.setParameter("idLot", idLot);
			
			response.setBids(new ArrayList<Bid>(query.getResultList()));
			response.setStateResult(StateResult.SUCCESS);

		} catch(Exception e){
			LOGGRER.info("Is not satisfied: getBidsByIdLot{} reason={}, idLot{}", e, e.getMessage(), idLot);
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());	
		} finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
		
		return response;
	}

	public int getCountBidsForLot(Integer idLot){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		int res = 0;
		try {
			Query query = entityManager.createNamedQuery("Bid.getCountBidsForLot");
			query.setParameter("idLot", idLot);
			res = Integer.parseInt(query.getSingleResult().toString());
		} catch(Exception e){
			LOGGRER.info("Is not satisfied: getCountBidsForLot{} reason={}, idLot{}", e, e.getMessage(), idLot);
		} finally {
			if ( (null != entityManager) && (entityManager.isOpen()) ) {
				entityManager.close();
			}
		}
		
		return res;	
	}	
	
}
