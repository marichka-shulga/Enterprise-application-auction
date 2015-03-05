package testclient;

import junit.framework.Assert;

import org.junit.Test;

import client.artefacts.StateResult;
import client.artefacts.UserAuthenticResponse;
import client.realization.ClientAuction;

public class TestUserAuthentication {
	ClientAuction client = new ClientAuction();
	
	@Test
	public void authenticationUser1() {
		UserAuthenticResponse resp = client.userAuthentication("marichka", "marichka");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
		Assert.assertTrue(resp.getUser().getUserLogin().equals("marichka"));
		Assert.assertTrue(resp.getUser().getPassword().equals("marichka"));
	}
	
	@Test
	public void authenticationUser2() {
		UserAuthenticResponse resp = client.userAuthentication("qwer", "qwer");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.SUCCESS));
		Assert.assertTrue(resp.getUser().getUserLogin().equals("qwer"));
		Assert.assertTrue(resp.getUser().getPassword().equals("qwer"));
	}
	
	@Test
	public void authenticationUser3() {
		UserAuthenticResponse resp = client.userAuthentication("qwerty", "marichka");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
		Assert.assertNull(resp.getUser());
	}
	
	@Test
	public void authenticationUser4() {
		UserAuthenticResponse resp = client.userAuthentication("marichka", "qwerty");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
		Assert.assertNull(resp.getUser());
	}
	
	@Test
	public void authenticationUser5() {
		UserAuthenticResponse resp = client.userAuthentication("qwerty", "qwerty");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
		Assert.assertNull(resp.getUser());
	}
	
	@Test
	public void authenticationUser6() {
		UserAuthenticResponse resp = client.userAuthentication("vbcvb", "qwer");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
		Assert.assertNull(resp.getUser());
	}
	
	@Test
	public void authenticationUser7() {
		UserAuthenticResponse resp = client.userAuthentication("qwer", "gdfhgfj");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
		Assert.assertNull(resp.getUser());
	}
	
	@Test
	public void authenticationUser8() {
		UserAuthenticResponse resp = client.userAuthentication("spring", "window");
		Assert.assertTrue(resp.getStateResult().equals(StateResult.NOT_SUCCESS));
		Assert.assertNull(resp.getUser());
	}
}
