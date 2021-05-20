package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListTest extends AcmePlannerTest {
	
	//Este método prueba las funcionalidades list y show de un workplan público y no finalizado (accediendo como un usuario anónimo).
	//Se pretende listar workplans y mostrar sus detalles.
	//No se infringe ninguna restricción
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/workplan/list.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void listAndShowPositive(final int recordIndex, final String startDate, final String endDate, final String workLoad, final String publicPlan) {
		super.clickOnMenu("Anonymous", "Workplans");
		
		super.checkColumnHasValue(recordIndex, 0, startDate);
		super.checkColumnHasValue(recordIndex, 1, endDate);
		super.checkColumnHasValue(recordIndex, 2, workLoad);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workLoad", workLoad);
		super.checkInputBoxHasValue("publicPlan", publicPlan);
	
	}
	
	//Este test prueba la funcionalidad list workplan de forma negativa. Se intenta acceder al listado
	//como un usuario con rol de manager cuando esta funcionalidad es para roles anónimos.
	//El resultado esperado es un error acceso denegado.
	@Test
	@Order(20)
	public void listManagerNegative() {
		super.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/workplan/list");
		super.checkPanicExists();
	}
	
	//Este test prueba la funcionalidad list workplan de forma negativa. Se intenta acceder al listado
	//como un usuario con rol de administrador cuando esta funcionalidad es para roles anónimos.
	//El resultado esperado es un error acceso denegado.
	@Test
	@Order(30)
	public void listAdministratorNegative() {
		super.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/workplan/list");
		super.checkPanicExists();
	}

}
