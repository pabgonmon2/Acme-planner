package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTest extends AcmePlannerTest {
	
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/create-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void createPositive(final int recordIndex, final String startDate, final String endDate) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Create Work Plan");
		
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "Works Plans");
		
		super.checkColumnHasValue(recordIndex, 0, startDate);
		super.checkColumnHasValue(recordIndex, 1, endDate);
		super.checkColumnHasValue(recordIndex, 2, "0.00");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workLoad", "0.00");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/create-negative.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(20)
	public void createNegative(final String startDate, final String endDate) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Create Work Plan");
		
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
}
