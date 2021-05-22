package acme.testing.authenticated.useraccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedUserAccountShowTest extends AcmePlannerTest{
	
	/*En este test verificaremos que al iniciar sesion la informacion mostrada sea la correcta*/
	@ParameterizedTest
    @CsvFileSource(resources = "/authenticated/useraccount/show.csv", encoding = "utf-8", numLinesToSkip = 1)    
	@Order(10)
    public void showUserAccount(
    		final String username, 
    		final String password,
    		final String confirmation, 
    		final String name,
    		final String surname,
    		final String email) {
        super.signIn("manager2", "manager2");
        super.clickOnMenu("Account", "General data");
        
        super.checkInputBoxHasValue("username", username);
        super.checkInputBoxHasValue("password", password);
        super.checkInputBoxHasValue("confirmation", confirmation);
        super.checkInputBoxHasValue("identity.name", name);
        super.checkInputBoxHasValue("identity.surname", surname);
        super.checkInputBoxHasValue("identity.email", email);

        super.signOut();
    }
	
	/*En este test verificaremos que es posible actualizar la información de un usuario registrado*/
	@ParameterizedTest
    @CsvFileSource(resources = "/authenticated/useraccount/positive.csv", encoding = "utf-8", numLinesToSkip = 1)    
	@Order(20)
    public void updateUserAccountPositiveCase(
    		final String password,
    		final String confirmation, 
    		final String name,
    		final String surname,
    		final String email) {
        super.signIn("manager2", "manager2");
        super.clickOnMenu("Account", "General data");
        
        super.fillInputBoxIn("password", password);
        super.fillInputBoxIn("confirmation", confirmation);
        super.fillInputBoxIn("identity.name", name);
        super.fillInputBoxIn("identity.surname", surname);
        super.fillInputBoxIn("identity.email", email);
        
        super.clickOnSubmitButton("Update");
        
        super.checkInputBoxHasValue("username", "manager2");
        super.checkInputBoxHasValue("password", password);
        super.checkInputBoxHasValue("confirmation", confirmation);
        super.checkInputBoxHasValue("identity.name", name);
        super.checkInputBoxHasValue("identity.surname", surname);
        super.checkInputBoxHasValue("identity.email", email);

        super.signOut();

        super.signOut();
    }

	/*En este test verificaremos que no posible actualizar la información de un usuario registrado debido a que las contraseñas no coinciden*/
	@ParameterizedTest
    @CsvFileSource(resources = "/authenticated/useraccount/negative.csv", encoding = "utf-8", numLinesToSkip = 1)    
	@Order(30)
    public void updateUserAccountNegativeCase(
    		final String password,
    		final String confirmation, 
    		final String name,
    		final String surname,
    		final String email) {
        super.signIn("manager2", "manager2");
        super.clickOnMenu("Account", "General data");
        
        super.fillInputBoxIn("password", password);
        super.fillInputBoxIn("confirmation", confirmation);
        super.fillInputBoxIn("identity.name", name);
        super.fillInputBoxIn("identity.surname", surname);
        super.fillInputBoxIn("identity.email", email);
        
        super.clickOnSubmitButton("Update");
        super.checkErrorsExist();

        super.signOut();
    }

	/*En este test verificaremos que no posible actualizar la información de un usuario registrado debido a que los campos están vacíos*/
	@ParameterizedTest
    @CsvFileSource(resources = "/authenticated/useraccount/negative2.csv", encoding = "utf-8", numLinesToSkip = 1)    
	@Order(40)
    public void updateUserAccountNegativeCase2(
    		final String password,
    		final String confirmation, 
    		final String name,
    		final String surname,
    		final String email) {
        super.signIn("manager2", "manager2");
        super.clickOnMenu("Account", "General data");
        
        super.fillInputBoxIn("password", password);
        super.fillInputBoxIn("confirmation", confirmation);
        super.fillInputBoxIn("identity.name", name);
        super.fillInputBoxIn("identity.surname", surname);
        super.fillInputBoxIn("identity.email", email);
        
        super.clickOnSubmitButton("Update");
        super.checkErrorsExist();

        super.signOut();
    }

}
