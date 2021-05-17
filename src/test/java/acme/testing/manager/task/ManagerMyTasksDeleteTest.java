package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerMyTasksDeleteTest extends AcmePlannerTest {
	
	public Integer i = 0;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String startDate, 
			final String endDate, final String workFlow, final String description, final String publicTask, final String url) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Own tasks");
		
		super.checkColumnHasValue(recordIndex-this.i, 0, title);
		super.checkColumnHasValue(recordIndex-this.i, 1, startDate);
		super.checkColumnHasValue(recordIndex-this.i, 2, endDate);
		super.checkColumnHasValue(recordIndex-this.i, 3, description);
		
		super.clickOnListingRecord(recordIndex-this.i);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workFlow", workFlow);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("publicTask", publicTask);
		super.checkInputBoxHasValue("url", url);
		
		super.clickOnSubmitButton("Delete");
		
		this.i++;
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void deleteNegativeAdministrator() {	
		this.signIn("administrator", "administrator");
		super.navigate("/manager/task/delete", "id=23"); ;
		super.checkErrorsExist();
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void deleteNegativeAnonymous() {	
		super.navigate("/manager/task/delete", "id=23"); ;
		super.checkErrorsExist();
	}

}
