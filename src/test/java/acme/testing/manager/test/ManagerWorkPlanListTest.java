package acme.testing.manager.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanListTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/list.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void listAndShow(final int recordIndex, final String startDate, final String endDate, final String workLoad, final String publicPlan) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Works Plans");
		
		super.checkColumnHasValue(recordIndex, 0, startDate);
		super.checkColumnHasValue(recordIndex, 1, endDate);
		super.checkColumnHasValue(recordIndex, 2, workLoad);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workLoad", workLoad);
		super.checkInputBoxHasValue("publicPlan", publicPlan);
	
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void listAnonymousNegative() {
		super.navigate("/manager/workplan/list","");
		super.checkErrorsExist();
	}
	
	@Test
	@Order(20)
	public void listAdministratorNegative() {
		super.signIn("administrator", "administrator");
		super.navigate("/manager/workplan/list","");
		super.checkErrorsExist();
	}
	
	
	
}
