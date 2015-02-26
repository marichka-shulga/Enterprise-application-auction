package testUser;

import java.util.HashSet;

import model.Bid;
import model.Lot;
import model.User;
import modelBL.UserLogic;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertNotNull(userLogic.authorization("marichka", "marichka"));
	}
	
	@Test
	public void authorizationUser2() {
		UserLogic userLogic = new UserLogic();
		Assert.assertNull(userLogic.authorization("qwer", "qwer"));
	}

}
