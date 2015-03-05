package testclient;

import junit.framework.Assert;

import org.junit.Test;

import client.artefacts.BaseResponse;
import client.artefacts.StateResult;
import client.artefacts.User;
import client.realization.ClientAuction;

public class TestUserRegistration {
	
	ClientAuction client = new ClientAuction();
	
	@Test
	public void regiatrationUser1() {
		User user = new User();
		user.setFirstName("user1");
		user.setLastName("user1");
		user.setUserLogin("marichka");
		user.setPassword("marichka");
		BaseResponse resp = client.userRegistration(user);
		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
	}
	
	@Test
	public void regiatrationUser2() {
		User user = new User();
		user.setFirstName("user2");
		user.setLastName("user2");
		user.setUserLogin("qwer");
		user.setPassword("qwer");
		BaseResponse resp = client.userRegistration(user);
		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
	}
	
	@Test
	public void regiatrationUser3() {
		User user = new User();
		user.setFirstName("user2");
		user.setLastName("user2");
		user.setUserLogin("qwer");
		user.setPassword("qwer");
		BaseResponse resp = client.userRegistration(user);
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
	}
	
	@Test
	public void regiatrationUser4() {
		User user = new User();
		user.setFirstName("user1");
		user.setLastName("user1");
		user.setUserLogin("marichka");
		user.setPassword("marichka");
		BaseResponse resp = client.userRegistration(user);
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
	}

}
