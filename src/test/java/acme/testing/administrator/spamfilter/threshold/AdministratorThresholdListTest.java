package acme.testing.administrator.spamfilter.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdListTest extends AcmePlannerTest {

	
	/*
		En este test se comprueba que un administrador sea capaz de listar el threshold
		Para ello accedemos al listado de threshold comprobando que el valor de los campos del
		listado son correctos, así como, que el valor del formulario (show) es correcto tambien.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listThreshold.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String value) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkColumnHasValue(recordIndex, 0, value);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("value", value);
		
		this.signOut();
	}
	
	
	/*
		En este test se comprueba que un anonimo no sea capaz de listar el threshold
		Para ello accedemos al listado de threshold comprobando que nos devuelve un error de autorizacion
	 */
	@Test
	@Order(20)
	public void listNegativeAnonymous() {
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/threshold/list");
		super.checkErrorsExist();
	}
	
	/*
		En este test se comprueba que un manager no sea capaz de listar el threshold
		Para ello accedemos al listado de threshold comprobando que nos devuelve un error de autorizacion
	 */
	@Test
	@Order(30)
	public void listNegativeManager() {
		this.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/threshold/list");
		super.checkErrorsExist();
	}
}