package auction.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the lot database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Lot.getActiveLots",
                query="SELECT l FROM Lot l WHERE l.state = :state"),
    @NamedQuery(name="Lot.getAllLots",
                query="SELECT l FROM Lot l")                
}) 
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Lot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lot_id_gen")
	@SequenceGenerator(name="lot_id_gen", sequenceName="lot_id_seq", initialValue=1)
	@Column(name="id_lot")
	private Integer idLot;
	
	@Column(name="code")
	private Integer code;

	@Column(name="descriptions")	
	private String descriptions;
	
	@Column(name="finish_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@Column(name="name")
	private String name;

	@Column(name="start_price")
	private BigDecimal startPrice;
	
	@Column(name="state")
	@Enumerated(EnumType.STRING)
	private LotState state;

	//bi-directional many-to-one association to Bid
	@XmlTransient
	@OneToMany(mappedBy="lot", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Bid> bids;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Lot() {
		bids = new HashSet<Bid>();
	}

	public Integer getIdLot() {
		return this.idLot;
	}

	public void setIdLot(Integer idLot) {
		this.idLot = idLot;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Date getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getStartPrice() {
		return this.startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public LotState getState() {
		return this.state;
	}

	public void setState(LotState state) {
		this.state = state;
	}

	public Set<Bid> getBids() {
		return this.bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setLot(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setLot(null);

		return bid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}