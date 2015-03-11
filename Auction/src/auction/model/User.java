package auction.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
    @NamedQuery(name="User.getUserByLogin",
                query="SELECT u FROM User u WHERE u.userLogin = :userLogin"),
                
    @NamedQuery(name="User.getUser",
                query="SELECT u FROM User u WHERE u.userLogin = :userLogin AND u.password = :password")
}) 
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
	@SequenceGenerator(name="user_id_gen", sequenceName="user_id_seq", allocationSize = 500)
	@Column(name="id_user")
	private Integer idUser;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;

	@Column(name="user_login")
	private String userLogin;

	//bi-directional many-to-one association to Bid
	@XmlTransient
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Bid> bids;

	//bi-directional many-to-one association to Lot
	@XmlTransient
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Lot> lots;

	public User() {
		bids = new HashSet<Bid>();
		lots = new HashSet<Lot>();
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
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