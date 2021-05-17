package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanDeleteTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/delete-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String startDate, final String nextStartDate) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Works Plans");
		
		super.checkColumnHasValue(recordIndex,0, startDate);
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
		
		super.checkColumnHasValue(recordIndex,0, nextStartDate);
		
		super.signOut();
		
	}
	
	@Test
	@Order(20)
	public void deleteNegative() {
		super.signIn("manager2", "manager2");
		
		super.navigate("/manager/workplan/delete", "id=41");
		
		super.checkErrorsExist();
	}
}
