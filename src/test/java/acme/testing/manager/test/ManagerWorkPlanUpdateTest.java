package acme.testing.manager.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanUpdateTest  extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/update-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String startDate, final String endDate) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Works Plans");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0, startDate);
		super.checkColumnHasValue(recordIndex, 1, endDate);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/update-negative.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void updateNegative(final int recordIndex, final String startDate, final String endDate) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Works Plans");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
	}
}
