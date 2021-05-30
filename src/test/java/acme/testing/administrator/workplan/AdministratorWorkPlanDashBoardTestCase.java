package acme.testing.administrator.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWorkPlanDashBoardTestCase extends AcmePlannerTest{
	
/*En el siguiente test se provara que los valores numericos devueltos por el servicio sean correctos*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/workplan/workplan-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
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
		super.clickOnMenu("Administrator", "Dashboard Work Plans");
		
		super.checkDashBoard(publicTasks, privateTasks, finishedTasks, nonFinishedTasks, averageWorkFlow, deviationWorkFlow, maxWorkFlow, minWorkFlow, averageExecutionPeriod, deviationExecutionPeriod, maxExecutionPeriod, minExecutionPeriod);
		
		this.signOut();
	}
	
	/*En el siguiente test se provara la no posibilidad de acceder al dashboard por parte de un manager*/
	@Test
	@Order(20)
	public void workPlanDashBoardNegativeTestCase2() {
			this.signIn("manager3", "manager3");
			super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboardwp/list");
			super.checkErrorsExist();
			this.signOut();
	}

	/*En el siguiente test se provara la no posibilidad de acceder al dashboard por parte de un anonimo*/
	
	@Test
	@Order(30)
	public void workPlanDashBoardNegativeTestCase3() {
			super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboardwp/list");
			super.checkErrorsExist();
	}
}
