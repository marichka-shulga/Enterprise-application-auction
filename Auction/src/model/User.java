package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
    @NamedQuery(name="User.getUserByLoggin",
                query="SELECT u FROM User u WHERE u.loggin = :loggin"),
                
    @NamedQuery(name="User.getUser",
                query="SELECT u FROM User u WHERE u.loggin = :loggin AND u.password = :password"),
}) 
//@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_gen")
	@SequenceGenerator(name="user_id_gen", sequenceName="user_id_seq", allocationSize=100, initialValue=1)
	@Column(name="id_user")
	private Integer idUser;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String loggin;

	private String password;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Bid> bids;

	//bi-directional many-to-one association to Lot
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Lot> lots;

	public User() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoggin() {
		return this.loggin;
	}

	public void setLoggin(String loggin) {
		this.loggin = loggin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Bid> getBids() {
		return this.bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setUser(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setUser(null);

		return bid;
	}

	public Set<Lot> getLots() {
		return this.lots;
	}

	public void setLots(Set<Lot> lots) {
		this.lots = lots;
	}

	public Lot addLot(Lot lot) {
		getLots().add(lot);
		lot.setUser(this);

		return lot;
	}

	public Lot removeLot(Lot lot) {
		getLots().remove(lot);
		lot.setUser(null);

		return lot;
	}

}