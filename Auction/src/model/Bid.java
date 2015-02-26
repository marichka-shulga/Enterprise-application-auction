package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the bid database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Bid.getMaxRate",
                query="SELECT MAX(b.rate) FROM Bid b WHERE b.lot.idLot = :idLot"),
    @NamedQuery(name="Bid.getWinningBid",
                query="SELECT b FROM Bid b WHERE b.lot.idLot = :idLot AND b.rate = :rate"),
    @NamedQuery(name="Bid.getAllBidsForLot",
                query="SELECT b FROM Bid b WHERE b.lot.idLot = :idLot"), 
}) 

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "bid_id_gen")
	@SequenceGenerator(name="bid_id_gen", sequenceName="bid_id_seq", allocationSize=100, initialValue=1)
	@Column(name="id_bid")
	private Integer idBid;

	@Column(name="is_winning_bid")
	private Boolean isWinningBid;

	private BigDecimal rate;

	//bi-directional many-to-one association to Lot
	@ManyToOne
	@JoinColumn(name="id_lot")
	private Lot lot;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Bid() {
	}

	public Integer getIdBid() {
		return this.idBid;
	}

	public void setIdBid(Integer idBid) {
		this.idBid = idBid;
	}


	public Boolean getIsWinningBid() {
		return this.isWinningBid;
	}

	public void setIsWinningBid(Boolean isWinningBid) {
		this.isWinningBid = isWinningBid;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}