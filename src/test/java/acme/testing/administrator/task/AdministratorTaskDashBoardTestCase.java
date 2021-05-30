package acme.testing.administrator.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorTaskDashBoardTestCase extends AcmePlannerTest{
	
	/*En el siguiente test se provara que los valores numericos devueltos por el servicio sean correctos*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/task-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll( 
	final String publicTasks,
	final String privateTasks,
	final String finishedTasks,
	final String nonFinishedTasks,
	final String averageWorkFlow,
	final String deviationWorkFlow,
	final String maxWorkFlow,
	final String minWorkFlow,
	final String averageExecutionPeriod,
	final String deviationExecutionPeriod,
	final String maxExecutionPeriod,
	final String minExecutionPeriod) {
		
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkDashBoard(publicTasks, privateTasks, finishedTasks, nonFinishedTasks, averageWorkFlow, deviationWorkFlow, maxWorkFlow, minWorkFlow, averageExecutionPeriod, deviationExecutionPeriod, maxExecutionPeriod, minExecutionPeriod);
		
		this.signOut();
	}
	
	/*En el siguiente test se provara la no posibilidad de acceder al dashboard por parte de un manager*/
	@Test
	@Order(20)
	public void dashBoardNegativeTestCase2() {
			this.signIn("manager3", "manager3");
			super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/list");
			super.checkErrorsExist();
			this.signOut();
	}

	/*En el siguiente test se provara la no posibilidad de acceder al dashboard por parte de un anonimo*/
	
	@Test
	@Order(30)
	public void dashBoardNegativeTestCase3() {
			super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/list");
			super.checkErrorsExist();
	}
}
