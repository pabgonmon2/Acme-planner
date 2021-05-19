package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerMyTasksListTest extends AcmePlannerTest {
	
	//En este test se prueba la funcionalidad list y show de las tasks propias de un manager. El resultado esperado es que se listen
	//las tasks y se muestren sus detalles.
	//No se infringe ninguna restricción.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String title, final String startDate, 
			final String endDate, final String workFlow, final String description, final String publicTask, final String url) {
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Own tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endDate);
		super.checkColumnHasValue(recordIndex, 3, description);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workFlow", workFlow);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("publicTask", publicTask);
		super.checkInputBoxHasValue("url", url);
		
		super.signOut();
	}
	
	//Aquí se prueba la funcionalidad list de tasks pero de forma negativa. El resultado esperado es
	//un error de acceso denegado ya que se está accediendo con un rol anónimo que no tiene acceso
	//a la funcionalidad.
	@Test
	@Order(20)
	public void listNegativeAnonymous() {
		super.driver.get("http://localhost:8080/Acme-Planner/manager/task/list");
		super.checkErrorsExist();
	}
	
	//Aquí se vuelve a probar la funcionalidad list de tasks pero de forma negativa. El resultado esperado es
	//un error de acceso denegado ya que se está accediendo con un rol de administrador que no tiene acceso
	//a la funcionalidad.
	@Test
	@Order(30)
	public void listNegativeAdministrator() {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/manager/task/list");
		super.checkErrorsExist();
	}

}
