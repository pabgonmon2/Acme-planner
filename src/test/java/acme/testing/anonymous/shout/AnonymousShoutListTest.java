package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String author, final String text, final String info, final String moment) {
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		
		
	}
	
	@Test
	@Order(20)
	public void listNegativeAdministrator() {
		
		super.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/shout/list");
		super.checkErrorsExist();
		
	}
	
	@Test
	@Order(30)
	public void listNegativeManager() {
		
		super.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/shout/list");
		super.checkErrorsExist();
		
	}
}
