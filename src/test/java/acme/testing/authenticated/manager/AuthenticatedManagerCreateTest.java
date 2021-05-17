package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest{

	@Test
	@Order(10)
	public void createPositive() {
		super.signIn("fervalnav", "Qwerty123");
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Register");
	
		super.checkLinkExists("Own tasks");
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void createNegative() {
		super.signIn("manager2", "manager2");
		super.navigate("/authenticated/manager/create", "");
		
		super.checkErrorsExist();
	}
}
