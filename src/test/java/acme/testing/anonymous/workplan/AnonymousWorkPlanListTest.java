package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListTest extends AcmePlannerTest {
	
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
	
	@Test
	@Order(20)
	public void listManagerNegative() {
		super.signIn("manager2", "manager2");
		super.navigate("/anonymous/workplan/list","");
		super.checkErrorsExist();
	}
	
	@Test
	@Order(30)
	public void listAdministratorNegative() {
		super.signIn("administrator", "administrator");
		super.navigate("/anonymous/workplan/list","");
		super.checkErrorsExist();
	}

}
