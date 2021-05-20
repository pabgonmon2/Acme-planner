package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest{

	@Test
	@Order(10)
	public void createPositive() {
		//Iniciamos sesison como usuario que no es manager
		super.signIn("fervalnav", "Qwerty123");
		//Accedemos al formulario para convertirnos en manager
		super.clickOnMenu("Account", "Become a manager");
		//Nos convertimos en manager
		super.clickOnSubmitButton("Register");
		//Comprobamos que tenemos los links para acceder que debe tener un manager
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
