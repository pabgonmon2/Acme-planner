package acme.testing.administrator.spamfilter.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordDeleteTest extends AcmePlannerTest{

	public static Integer i=0;
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listSpamword.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String spamword) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex-AdministratorSpamwordDeleteTest.i, 0, spamword);
		
		super.clickOnListingRecord(recordIndex-AdministratorSpamwordDeleteTest.i);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("spamword", spamword);
		
		
		super.clickOnSubmitButton("Delete");
		AdministratorSpamwordDeleteTest.i++;
		this.signOut();
	}
	
	@Test
	@Order(10)
	public void deletePositive() {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/delete?id=66");
	
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void deleteNegativeManager() {	
		this.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/delete?id=66");
		super.checkErrorsExist();
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void deleteNegativeAnonymous() {	
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/delete?id=66");
		super.checkErrorsExist();
	}
	
	
}
