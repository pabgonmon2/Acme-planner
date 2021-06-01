package acme.testing.administrator.spamfilter.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest{

	
	/*
		En este test se comprueba que un administrador sea capaz de actualizar el threshold
		Para ello accedemos al formulario del threshold y lo actualizamos con un valor valido (NotNull&Double)
		comprobando que se ha actualizado posteriormente.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/updateThresholdPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void updatePositive(final int recordIndex, final String value) {        
        super.signIn("administrator", "administrator");
        
        super.clickOnMenu("Administrator", "Spam threshold");
        super.checkColumnHasValue(recordIndex, 0, "10.00");
        
        super.clickOnListingRecord(recordIndex);
        
        super.fillInputBoxIn("value", value);    

        super.clickOnSubmitButton("Update");
        
        super.checkSimplePath("/administrator/threshold/list");
                
        super.driver.get("http://localhost:8080/Acme-Planner/administrator/threshold/update?id=67");

        super.checkInputBoxHasValue("value", value);
        
        super.fillInputBoxIn("value", "10.00");    
        super.clickOnSubmitButton("Update");
        super.signOut();
    }
	
	/*
		En este test se comprueba que un administrador no sea capaz de actualizar el threshold debido a restricciones.
		Para ello accedemos al formulario del threshold y lo actualizamos con un valor no valido (Null || String)
		comprobando que se han producido errores y que no el valor sigue siendo el mismo.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/updateThresholdNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void updateNegative(final int recordIndex, final String value) {        
		super.signIn("administrator", "administrator");
        
        super.clickOnMenu("Administrator", "Spam threshold");
        super.checkColumnHasValue(recordIndex, 0, "10.00");
        super.clickOnListingRecord(recordIndex);
        super.fillInputBoxIn("value", value);    

        super.clickOnSubmitButton("Update");
        
        super.checkErrorsExist();
        
                
        super.driver.get("http://localhost:8080/Acme-Planner/administrator/threshold/update?id=67");

        super.checkInputBoxHasValue("value", "10.00");
        super.signOut();

    }
	/*
		En este test se comprueba que un manager no sea capaz de acceder al formulario del threshold
		Para ello accedemos a la url del formulario del threshold comprobando que nos devuelve un error de autorizacion
		
	 */
	@Test
	@Order(20)
	public void updateNegative() {
		this.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/update?id=67");
		super.checkErrorsExist();

	}

	/*
		En este test se comprueba que un anonimo no sea capaz de acceder al formulario del threshold
		Para ello accedemos a la url del formulario del threshold comprobando que nos devuelve un error de autorizacion
	 */
//	@Test
//	@Order(20)
//	public void updateNegativeAnonymous() {
//		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/update?id=67");
//		super.checkErrorsExist();
//
//	}
	

	
}
