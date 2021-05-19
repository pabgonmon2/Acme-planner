package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListAllTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String title, final String startDate, final String endDate, final String workFlow, final String description, final String publicTask,final String url) {
		this.signIn("fervalnav", "Qwerty123");
		
		super.clickOnMenu("Authenticated", "Tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endDate);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 4, url);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workFlow",workFlow);
		super.checkInputBoxHasValue("description",description);
		super.checkInputBoxHasValue("publicTask",publicTask);
		
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void listNegativeAuthenticated() {
		super.driver.get("http://localhost:8080/Acme-Planner/authenticated/task/list");
		super.checkErrorsExist();
	}

}
