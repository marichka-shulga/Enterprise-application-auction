package dao;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;

import log.LogFactory;
import model.Bid;


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
				entityManager.close();
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
				entityManager.close();
			}
		}
		
		return bid;	
	}
	
	@SuppressWarnings("unchecked")
	public Set<Bid> getAllBidsForLot(Integer idLot){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Set<Bid> results = null;
		try {
			Query query = entityManager.createNamedQuery("Bid.getAllBidsForLot");
			query.setParameter("idLot", idLot);
			List<Bid> resultsList = query.getResultList();
			if ( null != resultsList ) {
				results = new HashSet<Bid>(resultsList);
			}
		} finally {
			entityManager.close();
		}
		return results;
	}

	
	
}
