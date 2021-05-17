package acme.testing.administrator.spamfilter.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordListTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listSpamword.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String spamword) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex, 0, spamword);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("spamword", spamword);
		
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void listNegativeAnonymous() {
		super.navigate("/administrator/spamword/list", ""); ;
		super.checkErrorsExist();
	}
	
	@Test
	@Order(30)
	public void listNegativeManager() {
		this.signIn("manager2", "manager2");
		super.navigate("/administrator/spamword/list", ""); ;
		super.checkErrorsExist();
	}
	
	@Test
	@Order(35)
	public void filtrosShoutPositive() {
		
		super.clickOnMenu("Anonymous", "Shouts!");
		
		super.checkInputBoxHasValue("author", "John Doe");
		super.checkInputBoxHasValue("text", "Lorem ipsum!");
		super.checkInputBoxHasValue("info", "http://example.org");
		
		super.fillInputBoxIn("author", "Pablo");
		super.fillInputBoxIn("text", "Hola como estais?");
		super.fillInputBoxIn("info", "");
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shout");
		
		super.checkColumnHasValue(0, 1, "Pablo");
		super.checkColumnHasValue(0, 2, "Hola como estais?");
		super.checkColumnHasValue(0, 3, "");
		
	}
	
	@Test
	@Order(40)
	public void filtrosShoutNegative() {
		
		super.clickOnMenu("Anonymous", "Shouts!");
		
		super.checkInputBoxHasValue("author", "John Doe");
		super.checkInputBoxHasValue("text", "Lorem ipsum!");
		super.checkInputBoxHasValue("info", "http://example.org");
		
		super.fillInputBoxIn("author", "Sex");
		super.fillInputBoxIn("text", "Hola como estais?");
		super.fillInputBoxIn("info", "");
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void createPositive(final int recordIndex, final String title, final String startDate, 
		final String endDate, final String workFlow, final String description, final String publicTask, final String url) {
		super.signIn("manager3", "manager3");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmitButton("Create task!");
		
		super.clickOnMenu("Manager", "Own tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endDate);
		super.checkColumnHasValue(recordIndex, 3, description);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workFlow", workFlow);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("publicTask", publicTask);
		super.checkInputBoxHasValue("url", url);
		
		super.signOut();
	}
}
