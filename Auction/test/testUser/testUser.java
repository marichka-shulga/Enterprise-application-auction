package testUser;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import auction.businesslogic.modelBL.UserLogic;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.User;

public class testUser {

	@Test
	public void registrationUser1() {

		User user = new User();
		user.setFirstName("Shulga");
		user.setLastName("Maria");
		user.setLoggin("qwerty");
		user.setPassword("qwerty");
		user.setLots(new HashSet<Lot>());
		user.setBids(new HashSet<Bid>());

		UserLogic userLogic = new UserLogic();
		Assert.assertTrue(userLogic.registration(user));

	}

	@Test
	public void registrationUser2() {
		User user = new User();
		user.setFirstName("Shulga");
		user.setLastName("Maria");
		user.setLoggin("marichka");
		user.setPassword("marichka");
		user.setLots(new HashSet<Lot>());
		user.setBids(new HashSet<Bid>());

		UserLogic userLogic = new UserLogic();
		Assert.assertTrue(userLogic.registration(user));
	}

	@Test
	public void registrationUser3() {
		User user = new User();
		user.setFirstName("Shulga");
		user.setLastName("Maria");
		user.setLoggin("marichka");
		user.setPassword("marichka");
		user.setLots(new HashSet<Lot>());
		user.setBids(new HashSet<Bid>());

		UserLogic userLogic = new UserLogic();
		Assert.assertFalse(userLogic.registration(user));
	}

	@Test
	public void authorizationUser1() {
		UserLogic userLogic = new UserLogic();
		Assert.assertNotNull(userLogic.authentication("marichka", "marichka"));
	}
	
	@Test
	public void authorizationUser2() {
		UserLogic userLogic = new UserLogic();
		Assert.assertNull(userLogic.authentication("qwer", "qwer"));
	}

}
