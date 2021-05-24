package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanListTest extends AcmePlannerTest {

	
	//En este test verificaremos que funciona correctamente el listado y el show,
	//Para ello verficaremos cada campo del listado y del show
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/list.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void listAndShow(final int recordIndex, final String startDate, final String endDate, final String workLoad, final String publicPlan) {
		//Iniciamos sesion
		super.signIn("manager2", "manager2");
		//Accedemos al listado de workplans
		super.clickOnMenu("Manager", "Works Plans");
		//Verificamos que los datos del listado estan bien
		super.checkColumnHasValue(recordIndex, 0, startDate);
		super.checkColumnHasValue(recordIndex, 1, endDate);
		super.checkColumnHasValue(recordIndex, 2, workLoad);
		
		//Accedemos a un workplan y verificamos sus atributos
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workLoad", workLoad);
		super.checkInputBoxHasValue("publicPlan", publicPlan);
	
		
		super.signOut();
	}
	
	//En este test verificaremos que no podemos acceder al listado siendo anonimo ya que saltaran errores
	@Test
	@Order(20)
	public void listAnonymousNegative() {
		//Accedemos al listado
		super.driver.get("http://localhost:8080/Acme-Planner/manageracc/workplan/list");
		//Verificamos que han saltado errores
		super.checkErrorsExist();
	}
	
	//En este test verificaremos que no podemos acceder al listado siendo administrador
	@Test
	@Order(30)
	public void listAdministratorNegative() {
		//Iniciamos sesion
		super.signIn("administrator", "administrator");
		//Accedemos al listado
		super.driver.get("http://localhost:8080/Acme-Planner/manageracc/workplan/list");
		//Veridicamos que han saltado errores
		super.checkErrorsExist();
	}
	
	
	
}
