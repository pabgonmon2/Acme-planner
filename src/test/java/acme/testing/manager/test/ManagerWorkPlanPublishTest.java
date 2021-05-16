package acme.testing.manager.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanPublishTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/publish-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void publishPositive(final int recordIndex) {
		super.signIn("manager3", "manager3");
		
		super.clickOnMenu("Manager", "Works Plans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Publish");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("publicPlan", "true");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/publish-negative.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(20)
	public void publishNegative(final int recordIndex) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Works Plans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Publish");
		
		super.checkErrorsExist();
	}
	
	

}
