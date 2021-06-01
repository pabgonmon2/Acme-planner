package acme.testing.authenticated.useraccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedUserAccountUpdateTest extends AcmePlannerTest{
	/*En este test verificaremos que es posible actualizar la información de 
	  un usuario registrado*/
	@ParameterizedTest
    @CsvFileSource(resources = "/authenticated/useraccount/positive.csv", encoding = "utf-8", numLinesToSkip = 1)    
	@Order(20)
    public void updatePositive(
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
        
        super.clickOnMenu("Account", "General data");
        super.checkInputBoxHasValue("username", "manager2");
        super.checkInputBoxHasValue("identity.name", name);
        super.checkInputBoxHasValue("identity.surname", surname);
        super.checkInputBoxHasValue("identity.email", email);

        super.signOut();
    }

	/*En este test verificaremos que no posible actualizar la 
	  información de un usuario registrado debido a diferentes restricciones*/
	@ParameterizedTest
    @CsvFileSource(resources = "/authenticated/useraccount/negative.csv", encoding = "utf-8", numLinesToSkip = 1)    
	@Order(30)
    public void updateNegative(
    		final String password,
    		final String confirmation, 
    		final String name,
    		final String surname,
    		final String email) {
        super.signIn("manager2", "carvilgar1");
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

//	/*En este test verificaremos que no posible actualizar la 
//	  información de un usuario registrado debido a que los campos están vacios*/
//	@ParameterizedTest
//    @CsvFileSource(resources = "/authenticated/useraccount/negative2.csv", encoding = "utf-8", numLinesToSkip = 1)    
//	@Order(40)
//    public void updateUserAccountNegativeCase2(
//    		final String password,
//    		final String confirmation, 
//    		final String name,
//    		final String surname,
//    		final String email) {
//        super.signIn("manager2", "carvilgar1");
//        super.clickOnMenu("Account", "General data");
//        
//        super.fillInputBoxIn("password", password);
//        super.fillInputBoxIn("confirmation", confirmation);
//        super.fillInputBoxIn("identity.name", name);
//        super.fillInputBoxIn("identity.surname", surname);
//        super.fillInputBoxIn("identity.email", email);
//        
//        super.clickOnSubmitButton("Update");
//        
//        super.checkErrorsExist("password");
//        super.checkErrorsExist("identity.name");
//        super.checkErrorsExist("identity.surname");
//        super.checkErrorsExist("identity.email");
//
//        super.signOut();
//    }
//	
//	/*En este test verificaremos que no posible actualizar la 
//	  información de un usuario registrado debido a que las contraseñas son muy cortas*/
//	@ParameterizedTest
//	@CsvFileSource(resources = "/authenticated/useraccount/negative3.csv", encoding = "utf-8", numLinesToSkip = 1)    
//	@Order(50)
//	public void updateUserAccountNegativeCase3(
//			final String password,
//			final String confirmation, 
//			final String name,
//			final String surname,
//			final String email) {
//	    super.signIn("manager2", "carvilgar1");
//	    super.clickOnMenu("Account", "General data");
//	    
//	    super.fillInputBoxIn("password", password);
//	    super.fillInputBoxIn("confirmation", confirmation);
//	    super.fillInputBoxIn("identity.name", name);
//	    super.fillInputBoxIn("identity.surname", surname);
//	    super.fillInputBoxIn("identity.email", email);
//	    
//	    super.clickOnSubmitButton("Update");
//	    super.checkErrorsExist("password");
//	
//	    super.signOut();
//	}
//	
//	/*En este test verificaremos que no posible actualizar la 
//	  información de un usuario registrado debido a que las contraseñas son muy largas*/
//	@ParameterizedTest
//	@CsvFileSource(resources = "/authenticated/useraccount/negative4.csv", encoding = "utf-8", numLinesToSkip = 1)    
//	@Order(60)
//	public void updateUserAccountNegativeCase4(
//			final String password,
//			final String confirmation, 
//			final String name,
//			final String surname,
//			final String email) {
//	  super.signIn("manager2", "carvilgar1");
//	  super.clickOnMenu("Account", "General data");
//	  
//	  super.fillInputBoxIn("password", password);
//	  super.fillInputBoxIn("confirmation", confirmation);
//	  super.fillInputBoxIn("identity.name", name);
//	  super.fillInputBoxIn("identity.surname", surname);
//	  super.fillInputBoxIn("identity.email", email);
//	  
//	  super.clickOnSubmitButton("Update");
//	  super.checkErrorsExist("password");
//	
//	  super.signOut();
//	}

}