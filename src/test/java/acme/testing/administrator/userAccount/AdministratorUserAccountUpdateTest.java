package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorUserAccountUpdateTest extends AcmePlannerTest{
	
	//En este test se actualiza el perfil de usuario del manager2.
	//Al clicar en el perfil del usuario, se muestran los detalles, pudiendo modificar algunos campos. Se espera que se actualice
	//el perfil del usuario correctamente.
	//Hay que haber iniciado sesión con un rol de administrador pero no se infringe ninguna restricción.
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles, final String status,final String newStatus) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("newStatus", newStatus);
		super.clickOnSubmitButton("Update");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email",email);
		super.checkInputBoxHasValue("roleList",roles);
		super.checkInputBoxHasValue("status",status);
		super.checkInputBoxHasValue("newStatus",newStatus);
		
		super.signOut();
	}
	
	
	/*
	En este test se comprueba que un manager no sea capaz de acceder al formulario de los perfiles de usuario
	Para ello accedemos a la url del formulario del perfil que queremos actualizar comprobando que nos 
	devuelve un error de autorizacion
	 */
	@Test
	@Order(20)
	public void updateNegativeManager() {
		this.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/user-account/update?id=6");
		super.checkErrorsExist();

	}
	
	/*
	En este test se comprueba que un anonimo no sea capaz de acceder al formulario de los perfiles de usuario
	Para ello accedemos a la url del formulario del perfil que queremos actualizar comprobando que nos 
	devuelve un error de autorizacion
	 */
	@Test
	@Order(30)
	public void updateNegativeAnonymous() {
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/user-account/update?id=6");
		super.checkErrorsExist();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/updatePositive2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void updatePositiveAdministrator(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles, final String status,final String newStatus) {
		super.signIn("administrator", "administrator");
		
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/user-account/update?id=9");
		
		super.fillInputBoxIn("newStatus", newStatus);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "User accounts");
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("newStatus", newStatus);
		
		super.signOut();	
	}


}


